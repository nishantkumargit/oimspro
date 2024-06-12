package com.pragya.oimspro.nodemcu.service;

import com.pragya.oimspro.nodemcu.entity.WiFiDetails;
import org.springframework.stereotype.Component;


@Component
public interface WiFiUpdationService {
    void updateWiFiDetails(WiFiDetails wiFiDetails);
}
