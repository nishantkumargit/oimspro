package com.pragya.oimspro.drmconfig.controller;

import com.pragya.oimspro.drmconfig.entity.DrmConfig;
import com.pragya.oimspro.drmconfig.service.DrmConfigService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/api/drmconfig")
public class DrmConfigController {
    private final DrmConfigService drmConfigService;

    public DrmConfigController(DrmConfigService drmConfigService) {
        this.drmConfigService = drmConfigService;
    }

    @PostMapping("/save")
    public void saveDrmConfig(@RequestBody DrmConfig drmConfig) {
        drmConfigService.saveDrmConfig(drmConfig);
    }

    @GetMapping("/all")
    public List<DrmConfig> getAllDrmConfigs() {
        return drmConfigService.getAllDrmConfigs();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDrmConfig(@PathVariable long id) {
        drmConfigService.deleteDrmConfig(id);
    }
}
