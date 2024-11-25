package com.pragya.oimspro.mqtt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Publisher {

    private static Logger logger = LoggerFactory.getLogger(Publisher.class);

    private String clientId = "MQTT_FX_Client";

    @Autowired
    private DefaultMqttPahoClientFactory mqttClientFactory;

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MqttPahoMessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(clientId + "_outbound", mqttClientFactory);
        messageHandler.setAsync(true);
        messageHandler.setDefaultRetained(true);
        messageHandler.setConverter(new DefaultPahoMessageConverter());
        messageHandler.setDefaultQos(1);
        return messageHandler;
    }

    public  void sendMessage(String topic, String payload) {
        try {
            MqttPahoMessageHandler messageHandler = mqttOutbound();
            Message<String> message = MessageBuilder.withPayload(payload)
                    .setHeader("mqtt_topic", topic)
                    .build();
            logger.info("Sending message: " + message.getPayload() + " to topic: " + topic);
            messageHandler.handleMessage(message);
        } catch (Exception e) {
            logger.error("Failed to send message: " + payload + " to topic: " + topic, e);
            // Consider retry logic here
        }
    }

}