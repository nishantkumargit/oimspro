package com.pragya.oimspro.nodemcu.service;

import com.pragya.oimspro.nodemcu.entity.MqttDetails;

public interface MqttCredsUpdationService {
    void updateMqttDetails(MqttDetails mqttDetails);

    void resetCounter(String deviceId);
}
