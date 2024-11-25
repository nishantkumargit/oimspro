package com.pragya.oimspro.mqtt;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


import java.util.UUID;


@Configuration
@EnableScheduling
public class MqttConfig {
    Logger logger = LoggerFactory.getLogger(MqttConfig.class);
    @Value("${mqtt.broker.url}")
    private String brokerUrl;
    @Value("${mqtt.client.id}")
    private String clientId;
    @Value("${mqtt.username}")
    private String userName;
    @Value("${mqtt.password}")
    private String password;

    IMqttClient mqttClient;

    String mqttClientID;

    private final Counter connectionSuccessCounter;
    private final Counter connectionFailureCounter;

    public MqttConfig(MeterRegistry meterRegistry) {
        this.connectionSuccessCounter = meterRegistry.counter("mqtt_connection_success_total");
        this.connectionFailureCounter = meterRegistry.counter("mqtt_connection_failure_total");
    }

    @Bean
    public DefaultMqttPahoClientFactory mqttClientFactory() throws MqttException {
        getClientId();
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getConnectionInfo());
        factory.setPersistence(new MemoryPersistence());
        this.mqttClient = factory.getClientInstance(this.brokerUrl, this.mqttClientID);
        this.mqttClient.connect(getConnectionInfo());
        logger.info("Factory created and client connected");
        return factory;
    }
    public MqttConnectOptions getConnectionInfo() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(userName);
        options.setPassword(password.toCharArray());
        options.setAutomaticReconnect(true);
        options.setKeepAliveInterval(30);
        options.setServerURIs(new String[] {brokerUrl});
        return options;
    }

    @Scheduled(fixedDelay = 5000)
    public void monitorConnection() {
        try {
            if (mqttClient.isConnected()) {
                connectionSuccessCounter.increment();
                logger.info("Connection successful - Counter incremented {}",this.mqttClient.getClientId());
            } else {
                connectionFailureCounter.increment();
                logger.info("Connection failed - Failure counter incremented {}",this.mqttClient.getClientId());
            }
        } catch (Exception e) {
            logger.error("Error checking connection status", e);
            connectionFailureCounter.increment();
        }
    }

    public void getClientId() {
        this.mqttClientID =  clientId+ UUID.randomUUID().toString()+"_inbound";
    }
}
