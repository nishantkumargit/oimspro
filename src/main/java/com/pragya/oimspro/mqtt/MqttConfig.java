package com.pragya.oimspro.mqtt;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;

@Configuration
public class MqttConfig {
    Logger logger = LoggerFactory.getLogger(MqttConfig.class);

    private String brokerUrl = "ssl://d08900cfdef5463098201f44a1532917.s2.eu.hivemq.cloud:8883";
    private String clientId = "MQTT_FX_Client";
    private String userName = "ankit";
    private String password = "Ankit@123";

    @Bean
    public DefaultMqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getConnectionInfo());
        logger.info("Factory created");
        return factory;
    }

    public MqttConnectOptions getConnectionInfo() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(userName);
        options.setPassword(password.toCharArray());
        options.setServerURIs(new String[] {brokerUrl});
        return options;
    }

    public String getClientId() {
        return clientId;
    }
}
