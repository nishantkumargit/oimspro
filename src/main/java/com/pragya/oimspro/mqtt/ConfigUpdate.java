package com.pragya.oimspro.mqtt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfigUpdate {
    @Autowired
    Publisher publisher;

    public void updateEsp32Config(String esp32Id, String newSsid, String newPassword) {
        String topic = "config/update/" + esp32Id;
        String payload = "{\"ssid\":\"" + newSsid + "\", \"password\":\"" + newPassword + "\"}";
        publisher.sendMessage(topic, payload);
    }
}
