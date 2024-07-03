package com.pragya.oimspro.nodemcu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MqttDetails {
    private List<String> deviceIdList;
    private String mqttBroker;
    private String mqttUsername;
    private String mqttPassword;
}
