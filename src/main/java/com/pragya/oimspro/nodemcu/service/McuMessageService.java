package com.pragya.oimspro.nodemcu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pragya.oimspro.nodemcu.entity.McuMessage;

import java.time.LocalDateTime;

public interface McuMessageService {

    McuMessage addMcuMessage(McuMessage mcuMessage);

    void sendMcuMessage(String message) throws JsonProcessingException;

    Long getProductionCountForDevice(String deviceId, LocalDateTime start, LocalDateTime end);

    Long getTotalProductionCount(LocalDateTime start, LocalDateTime end);


}

