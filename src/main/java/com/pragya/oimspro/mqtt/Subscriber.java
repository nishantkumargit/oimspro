package com.pragya.oimspro.mqtt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pragya.oimspro.drmconfig.service.DrmConfigService;
import com.pragya.oimspro.nodemcu.service.McuMessageService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;




import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
@Component
public class Subscriber {
    @Autowired
    McuMessageService mcuMessageService;

    @Autowired
    MqttConfig mqttConfig;

    Logger logger = LoggerFactory.getLogger(Subscriber.class);

    @Value("${mqtt.subscribe.topic}")
    private String defaultTopic;
    private MqttPahoMessageDrivenChannelAdapter adapter;
    @Autowired
    private Channel channel;
    @Autowired
    DrmConfigService drmConfigService;

    @Autowired
    private DefaultMqttPahoClientFactory mqttClientFactory;

    private final List<String> subscribedTopics = new CopyOnWriteArrayList<>();
    private final Counter messageReceivedCounter;
    @Autowired
    public Subscriber(MeterRegistry meterRegistry) {
        this.messageReceivedCounter = meterRegistry.counter("mqtt_messages_received_total");
    }
    public List<String> fetchSubscribedTopics() {
        return Collections.singletonList(drmConfigService.getConfig(Constants.MQTT_SUBSCRIBED_TOPICS));
    }
    public List<String> getAllSubscribedTopics() {
        if(subscribedTopics.isEmpty()) {
            return List.of(defaultTopic.split(","));
        }
        return subscribedTopics;
    }

    public void removeTopic(String topic) throws MqttException {
        adapter.removeTopic(topic);
        logger.info("Removed topic: {}", topic);
    }
    @Bean
    public MqttPahoMessageDrivenChannelAdapter mqttInbound() {
        String[] topics = getAllSubscribedTopics().toArray(new String[0]);
        subscribedTopics.addAll(Arrays.asList(topics));
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(mqttConfig.mqttClientID , mqttClientFactory, topics);
        adapter.setCompletionTimeout(5000); // Set timeout (milliseconds) for message processing
        adapter.setConverter(new DefaultPahoMessageConverter()); // Optional: Customize message converter
        adapter.setQos(1); // Set Quality of Service (QoS)
        adapter.setOutputChannel(channel.mqttInputChannel());
        logger.info("Adapter created {} {}",adapter.getConnectionInfo(),mqttConfig.mqttClient.getClientId());
        return adapter;
    }
    public void addTopic(String topic) throws MqttException {
        adapter.addTopic(topic, 1);
        logger.info("Added new topic: {}", topic);
    }

    @ServiceActivator(inputChannel = "mqttInputChannel")
    @Retryable(value = {IllegalArgumentException.class}, maxAttempts = 2)
    public void handleMessage(String message) throws JsonProcessingException {
        logger.info("Received message: " + message);
        messageReceivedCounter.increment();
        mcuMessageService.sendMcuMessage(message);
        // Process the received message as needed
    }
    @Recover
    public void recover(IllegalArgumentException e, String message) {
        logger.error("Failed to process message: " + message, e);
    }
}
@Configuration
class Channel{
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }
}