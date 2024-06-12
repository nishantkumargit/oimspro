package com.pragya.oimspro.nodemcu.service;

import com.pragya.oimspro.nodemcu.entity.NodeMcu;

public interface NodeMcuService {
    void addNodeMcu(NodeMcu nodeMcu);

    NodeMcu getNodeMcuFromDeviceId(String deviceId);
}
