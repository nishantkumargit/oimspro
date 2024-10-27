package com.pragya.oimspro.drmconfig.service;

import com.pragya.oimspro.drmconfig.entity.DrmConfig;

import java.util.List;

public interface DrmConfigService {
    void saveDrmConfig(DrmConfig drmConfig);
    List<DrmConfig> getAllDrmConfigs();
    void deleteDrmConfig(long id);
    String getConfig(String drmName);

}
