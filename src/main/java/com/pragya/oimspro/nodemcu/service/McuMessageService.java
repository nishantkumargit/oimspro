package com.pragya.oimspro.nodemcu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pragya.oimspro.nodemcu.entity.McuMessage;

public interface McuMessageService {

    McuMessage addMcuMessage(McuMessage mcuMessage);

    void sendMcuMessage(String message) throws JsonProcessingException;

}

