package com.pragya.oimspro.nodemcu.service.impl;

import com.pragya.oimspro.nodemcu.entity.NodeMcu;
import com.pragya.oimspro.nodemcu.repository.NodeMcuRepository;
import com.pragya.oimspro.nodemcu.service.NodeMcuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeMcuServiceImpl implements NodeMcuService {

    @Autowired
    NodeMcuRepository nodeMcuRepository;

    public void saveNodemcu(NodeMcu nodeMcu) {
        nodeMcuRepository.save(nodeMcu);
    }

    @Override
    public NodeMcu getNodeMcuFromDeviceId(String deviceId) {
        return nodeMcuRepository.findByDeviceId(deviceId);
    }

    @Override
    public List<NodeMcu> getAllNodeMcu() {
        return nodeMcuRepository.findAll();
    }

    @Override
    public void deleteNodeMcu(long id) {
        nodeMcuRepository.deleteById(id);

    }
}
