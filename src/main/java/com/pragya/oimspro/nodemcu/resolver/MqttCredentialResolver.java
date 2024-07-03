package com.pragya.oimspro.nodemcu.resolver;

import com.pragya.oimspro.nodemcu.entity.MqttDetails;
import com.pragya.oimspro.nodemcu.service.MqttCredsUpdationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import java.util.concurrent.ForkJoinPool;

@RestController
public class MqttCredentialResolver {

    @Autowired
    MqttCredsUpdationService mqttCredsUpdationService;

    @PostMapping("/updateDeviceMqttCreds")
    public void configUpdate(@RequestBody MqttDetails mqttDetails){
        mqttCredsUpdationService.updateMqttDetails(mqttDetails);
    }
    @PostMapping("/resetCounter")
    public void resetCounter(@RequestParam String deviceId){
        System.out.println("Resetting counter for devices "+ deviceId);
        mqttCredsUpdationService.resetCounter(deviceId);
    }
    @PostMapping("/test/deferred")
    public @ResponseBody DeferredResult<ResponseEntity<String>> testDeferred() {
//        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        ShallowEtagHeaderFilter.disableContentCaching(httpServletRequest);

        DeferredResult<ResponseEntity<String>> deferredResult = new DeferredResult<>(60000L); // 60 seconds timeout

        ForkJoinPool.commonPool().submit(() -> {
            try {
                Thread.sleep(5000); // Simulate some processing
                deferredResult.setResult(ResponseEntity.ok("Processed successfully"));
            } catch (Exception e) {
                deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing request"));
            }
        });

        return deferredResult;
    }
}
