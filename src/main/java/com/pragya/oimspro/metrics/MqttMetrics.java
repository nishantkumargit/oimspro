package com.pragya.oimspro.metrics;

import com.pragya.oimspro.mqtt.Subscriber;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MqttMetrics  extends Metrics{

    @Autowired
    private Subscriber subscriber;

    @Autowired
    MeterRegistry meterRegistry;

    public void bindTo() {
        List<String> topics =  subscriber.fetchSubscribedTopics();
        if (topics != null) {
            meterRegistry.gauge("mqtt.subscribed.topics", Tags.empty(), topics, List::size);
            System.out.println("Registered gauge: mqtt.subscribed.topics with size: " + topics.size());
        } else {
            System.out.println("Subscribed topics are null");
        }
    }

}