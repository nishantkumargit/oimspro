package com.pragya.oimspro.mqtt;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mqtt")
public class MqttController {
    @Autowired
    private Subscriber subscriber;

    @GetMapping("/subscribedTopics")
    public List<String> getSubscribedTopics() throws MqttException {
        return subscriber.getAllSubscribedTopics();
    }
    @PostMapping("/addTopic")
    public void addTopic(@RequestParam String topic) throws MqttException {
        subscriber.addTopic(topic);
    }

    @PostMapping("/removeTopic")
    public void removeTopic(@RequestParam String topic) throws MqttException {
        subscriber.removeTopic(topic);
    }
}
