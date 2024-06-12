package com.pragya.oimspro.nodemcu.service.impl;

import com.pragya.oimspro.mqtt.ConfigUpdate;
import com.pragya.oimspro.nodemcu.entity.NodeMcu;
import com.pragya.oimspro.nodemcu.entity.Status;
import com.pragya.oimspro.nodemcu.entity.WiFiDetails;
import com.pragya.oimspro.nodemcu.repository.NodeMcuRepository;
import com.pragya.oimspro.nodemcu.service.WiFiUpdationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WiFiUpdationServiceImpl implements WiFiUpdationService {
    private static final Logger logger = LoggerFactory.getLogger(WiFiUpdationServiceImpl.class);
    @Autowired
    ConfigUpdate configUpdate;

    @Autowired
    NodeMcuRepository nodeMcuRepository;
    @Override
    public void updateWiFiDetails(WiFiDetails wiFiDetails) {
        logger.info("Updating WiFi details for devices");
        if(wiFiDetails.getDeviceIdList() != null){
            if(wiFiDetails.getSsid() == null || wiFiDetails.getPassword() == null || wiFiDetails.getSsid().isEmpty() || wiFiDetails.getPassword().isEmpty()){
                return;
            }
            for (String deviceId : wiFiDetails.getDeviceIdList()) {
                NodeMcu nodeMcu = nodeMcuRepository.findByDeviceId(deviceId);
                if(nodeMcu.getStatus() != null && nodeMcu.getStatus()== Status.ACTIVE){
                    configUpdate.updateEsp32Config(nodeMcu.getDeviceId(), wiFiDetails.getSsid(), wiFiDetails.getPassword());
                }
            }
        }
    }
}
