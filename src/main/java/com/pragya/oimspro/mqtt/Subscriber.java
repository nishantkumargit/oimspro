package com.pragya.oimspro.mqtt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pragya.oimspro.nodemcu.service.McuMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;

@Component
public class Subscriber {
    @Autowired
    McuMessageService mcuMessageService;

    @Autowired
    MqttConfig mqttConfig;

    Logger logger = LoggerFactory.getLogger(Subscriber.class);

    private String defaultTopic = "esp8266_data_BF02,esp8266_data_BF06,esp8266_data_HDR-02,PMP_data_HDR-01,PMP_data_test_HDR-01";

    @Autowired
    private Channel channel;

    @Autowired
    private DefaultMqttPahoClientFactory mqttClientFactory;

    @Bean
    public MqttPahoMessageDrivenChannelAdapter mqttInbound() {
        String[] topics = defaultTopic.split(",");
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(mqttConfig.getClientId() + "_inbound", mqttClientFactory, topics);
        adapter.setCompletionTimeout(5000); // Set timeout (milliseconds) for message processing
        adapter.setConverter(new DefaultPahoMessageConverter()); // Optional: Customize message converter
        adapter.setQos(1); // Set Quality of Service (QoS)
        adapter.setOutputChannel(channel.mqttInputChannel());
        logger.info("Adapter created {} {}",adapter.getConnectionInfo());
        return adapter;
    }

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handleMessage(String message) throws JsonProcessingException {
        logger.info("Received message: " + message);
        mcuMessageService.sendMcuMessage(message);
        // Process the received message as needed
    }
}
@Configuration
class Channel{
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }
}