package com.pragya.oimspro.mqtt;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Publisher {

    private String clientId = "MQTT_FX_Client";

    @Autowired
    private DefaultMqttPahoClientFactory mqttClientFactory;

    @Bean
    public MqttPahoMessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(clientId + "_outbound", mqttClientFactory);
        messageHandler.setAsync(true);
        messageHandler.setDefaultRetained(true);
        messageHandler.setConverter(new DefaultPahoMessageConverter());
        messageHandler.setDefaultQos(1);
        return messageHandler;
    }

    public  void sendMessage(String topic, String payload) {
        MqttPahoMessageHandler messageHandler = mqttOutbound();
        Message<String> message = MessageBuilder.withPayload(payload)
                .setHeader("mqtt_topic", topic)
                .build();
        LoggerFactory.getLogger(Publisher.class).info("Sending message: " + message.getPayload() + " to topic: " + topic);
        messageHandler.handleMessage(message);
    }

}