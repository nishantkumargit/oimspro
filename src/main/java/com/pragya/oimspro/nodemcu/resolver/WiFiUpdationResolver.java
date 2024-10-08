package com.pragya.oimspro.nodemcu.resolver;

import com.pragya.oimspro.nodemcu.entity.WiFiDetails;
import com.pragya.oimspro.nodemcu.service.WiFiUpdationService;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;


@RestController
@RequestMapping("/api/update/")
public class WiFiUpdationResolver {
    @Autowired
    WiFiUpdationService wiFiUpdationService;
    @PostMapping("deviceWiFiCreds")
    public void configUpdate(@RequestBody WiFiDetails wiFiDetails){

        wiFiUpdationService.updateWiFiDetails(wiFiDetails);
        return;
    }
}
