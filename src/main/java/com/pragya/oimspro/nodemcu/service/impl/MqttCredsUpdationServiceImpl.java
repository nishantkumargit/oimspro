package com.pragya.oimspro.nodemcu.service.impl;

import com.pragya.oimspro.mqtt.ConfigUpdate;
import com.pragya.oimspro.nodemcu.entity.MqttDetails;
import com.pragya.oimspro.nodemcu.entity.NodeMcu;
import com.pragya.oimspro.nodemcu.entity.Status;
import com.pragya.oimspro.nodemcu.repository.NodeMcuRepository;
import com.pragya.oimspro.nodemcu.service.MqttCredsUpdationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqttCredsUpdationServiceImpl implements MqttCredsUpdationService {

    @Autowired
    NodeMcuRepository nodeMcuRepository;

    @Autowired
    ConfigUpdate configUpdate;



    private static final Logger logger = LoggerFactory.getLogger(MqttCredsUpdationServiceImpl.class);




    public void updateMqttDetails(MqttDetails mqttDetails) {
        logger.info("Updating Mqtt details for devices");
        if(mqttDetails.getDeviceIdList() != null){
            if(mqttDetails.getMqttBroker() == null || mqttDetails.getMqttUsername() == null || mqttDetails.getMqttUsername().isEmpty() || mqttDetails.getMqttPassword()==null || mqttDetails.getMqttPassword().isEmpty()){
                return;
            }
            for (String deviceId : mqttDetails.getDeviceIdList()) {
                NodeMcu nodeMcu = nodeMcuRepository.findByDeviceId(deviceId);
                if(nodeMcu!=null && nodeMcu.getStatus() != null && nodeMcu.getStatus()== Status.ACTIVE){
                    configUpdate.updateEsp32MqttConfig(nodeMcu.getDeviceId(), mqttDetails.getMqttBroker(), mqttDetails.getMqttUsername(),mqttDetails.getMqttPassword());
                }
            }
        }
    }

    public void resetCounter(String deviceId) {
        logger.info("Resetting counter for devices");
        configUpdate.resetCounter(deviceId);
    }

}
