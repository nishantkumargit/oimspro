package com.pragya.oimspro.mqtt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MqttController {
    @Autowired
    ConfigUpdate configUpdate;
    @PostMapping("/testMqtt")
    public String configUpdate(@RequestHeader("ssid") String ssid, @RequestHeader("password") String password){
        configUpdate.updateEsp32Config("esp32_1", ssid, password);
        return "ok";
    }
}
