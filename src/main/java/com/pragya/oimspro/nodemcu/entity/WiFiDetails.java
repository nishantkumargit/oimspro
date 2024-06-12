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
public class WiFiDetails {
    private List<String> deviceIdList;
    private String ssid;
    private String password;
}
