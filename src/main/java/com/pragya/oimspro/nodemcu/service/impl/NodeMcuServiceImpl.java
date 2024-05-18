package com.pragya.oimspro.nodemcu.service.impl;

import com.pragya.oimspro.nodemcu.entity.NodeMcu;
import com.pragya.oimspro.nodemcu.service.NodeMcuService;
import org.springframework.stereotype.Service;

@Service
public class NodeMcuServiceImpl implements NodeMcuService {
    public void addNodeMcu(NodeMcu nodeMcu) {
        System.out.println("NodeMcuServiceImpl.addNodeMcu");
    }
}
