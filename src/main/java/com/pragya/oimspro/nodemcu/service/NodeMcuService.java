package com.pragya.oimspro.nodemcu.service;

import com.pragya.oimspro.nodemcu.entity.NodeMcu;

import java.util.List;

public interface NodeMcuService {
    void saveNodemcu(NodeMcu nodeMcu);

    NodeMcu getNodeMcuFromDeviceId(String deviceId);

    List<NodeMcu> getAllNodeMcu();

    void deleteNodeMcu(long id);
}
