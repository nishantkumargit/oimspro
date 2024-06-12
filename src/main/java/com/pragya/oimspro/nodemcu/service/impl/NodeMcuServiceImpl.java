package com.pragya.oimspro.nodemcu.service.impl;

import com.pragya.oimspro.nodemcu.entity.NodeMcu;
import com.pragya.oimspro.nodemcu.repository.NodeMcuRepository;
import com.pragya.oimspro.nodemcu.service.NodeMcuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NodeMcuServiceImpl implements NodeMcuService {

    @Autowired
    NodeMcuRepository nodeMcuRepository;

    public void addNodeMcu(NodeMcu nodeMcu) {
        nodeMcuRepository.save(nodeMcu);
    }

    @Override
    public NodeMcu getNodeMcuFromDeviceId(String deviceId) {
        return nodeMcuRepository.findByDeviceId(deviceId);
    }
}
