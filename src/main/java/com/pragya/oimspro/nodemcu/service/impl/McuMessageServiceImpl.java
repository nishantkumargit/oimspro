package com.pragya.oimspro.nodemcu.service.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.pragya.oimspro.nodemcu.entity.McuMessage;
import com.pragya.oimspro.nodemcu.entity.NodeMcu;
import com.pragya.oimspro.nodemcu.entity.Status;
import com.pragya.oimspro.nodemcu.repository.McuMessageRepository;
import com.pragya.oimspro.nodemcu.service.McuMessageService;
import com.pragya.oimspro.nodemcu.service.NodeMcuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class McuMessageServiceImpl implements McuMessageService{

    @Autowired
    McuMessageRepository mcuMessageRepository;

    @Autowired
    NodeMcuService nodeMcuService;
    Logger logger = LoggerFactory.getLogger(McuMessageServiceImpl.class);
    ObjectMapper mapper = new ObjectMapper();

    // Helper method to convert Date to LocalDateTime
    private LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    // Helper method to convert LocalDateTime to Date
    private Date convertToDate(LocalDateTime dateToConvert) {
        return Date.from(dateToConvert
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }


    @Override
    public McuMessage addMcuMessage(McuMessage mcuMessage) {
        try{
            LocalDateTime publishedDateTime = convertToLocalDateTime(mcuMessage.getPublishedTime());

            // Truncate LocalDateTime to the nearest hour
            LocalDateTime truncatedDateTime = publishedDateTime.truncatedTo(ChronoUnit.HOURS);

            // Convert LocalDateTime back to Date
            Date hourlyBucket = convertToDate(truncatedDateTime);

            logger.debug("Upserting message with deviceId: {}, siteId: {}, count: {}, publishedTime: {}, nodeMcuCode: {}, hourlyBucket: {}",
                    mcuMessage.getDeviceId(), mcuMessage.getSiteId(), mcuMessage.getCount(), mcuMessage.getPublishedTime(), mcuMessage.getNodeMcuCode(), hourlyBucket);
//            mcuMessageRepository.save(mcuMessage);
            mcuMessageRepository.upsertMcuMessage(mcuMessage.getDeviceId(), mcuMessage.getSiteId(), mcuMessage.getCount(),
                    mcuMessage.getPublishedTime(), mcuMessage.getNodeMcuCode(), hourlyBucket);
            logger.info("mcu message added");}
        catch(Exception e){
            logger.error("error adding mcu message to database"+e);
        }
        return mcuMessage;
    }

    // Create a custom deserializer
    class EpochDeserializer extends JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonParser parser, DeserializationContext context) throws IOException {
            String epoch = parser.getText();
            logger.debug("Epoch value is {}", epoch);
            return new Date(Long.parseLong(epoch)*1000);
        }
    }


        @Override
    public void sendMcuMessage(String message) throws JsonProcessingException {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Date.class, new EpochDeserializer());
        mapper.registerModule(module);
        McuMessage mcuMessage = mapper.readValue(message, McuMessage.class);
        logger.info("received mcu message from mqtt "+mcuMessage.toString());
        String deviceId = mcuMessage.getDeviceId();
        NodeMcu nodeMcu = nodeMcuService.getNodeMcuFromDeviceId(deviceId);
        if(nodeMcu == null){
            nodeMcu = new NodeMcu();
            nodeMcu.setDeviceId(mcuMessage.getDeviceId());
            nodeMcu.setName(mcuMessage.getNodeMcuCode());
            nodeMcu.setReceivedTime(new Date());
            nodeMcu.setStatus(Status.ACTIVE);
            nodeMcu.setInstallationDate(new Date());
            logger.info("NodeMcu not found in database, adding new nodeMcu {}",nodeMcu.toString());
            nodeMcuService.addNodeMcu(nodeMcu);
        }

        addMcuMessage(mcuMessage);

    }
}
