package com.pragya.oimspro.mqtt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ConfigUpdate {

    private static final Logger logger = Logger.getLogger(ConfigUpdate.class.getName());
    @Autowired
    Publisher publisher;

    private static final String WIFI_CONFIG_UPDATE_TOPIC_PRECEDE = "config/update/wifi/";

    private static final String MQTT_CONFIG_UPDATE_TOPIC_PRECEDE = "config/update/mqtt/";


    public void updateEsp32Config(String esp32Id, String newSsid, String newPassword) {
        logger.info("Updating ESP32 WIFI config");
        String topic = WIFI_CONFIG_UPDATE_TOPIC_PRECEDE + esp32Id;
        String payload = "{\"ssid\":\"" + newSsid + "\", \"password\":\"" + newPassword + "\"}";
        publisher.sendMessage(topic, payload);
    }
    public void updateEsp32MqttConfig(String esp32Id,String mqttServer, String mqttUsername, String mqttPassword) {
        String topic = MQTT_CONFIG_UPDATE_TOPIC_PRECEDE + esp32Id;
        String payload = "{\"mqttServer\":\"" + mqttServer + "\", \"mqttUsername\":\"" + mqttUsername + "\", \"mqttPassword\":\"" + mqttPassword + "\"}";
        publisher.sendMessage(topic, payload);
    }

    public void resetCounter(String deviceId) {
        String topic = "config/update/counter/" + deviceId;
        String payload = "{\"resetCounter\":\"true\"}";
        publisher.sendMessage(topic, payload);
    }
}
