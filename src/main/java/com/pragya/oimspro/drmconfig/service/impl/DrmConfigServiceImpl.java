package com.pragya.oimspro.drmconfig.service.impl;

import com.pragya.oimspro.drmconfig.entity.DrmConfig;
import com.pragya.oimspro.drmconfig.repository.DrmConfigRepository;
import com.pragya.oimspro.drmconfig.service.DrmConfigService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrmConfigServiceImpl implements DrmConfigService {
    private final DrmConfigRepository drmConfigRepository;

    public DrmConfigServiceImpl(DrmConfigRepository drmConfigRepository) {
        this.drmConfigRepository = drmConfigRepository;
    }

    @Override
    public void saveDrmConfig(DrmConfig drmConfig) {
        drmConfigRepository.save(drmConfig);
    }

    @Override
    public List<DrmConfig> getAllDrmConfigs() {
        return drmConfigRepository.findAll();
    }

    @Override
    public void deleteDrmConfig(long id) {
        drmConfigRepository.deleteById(id);
    }


    @Override
    public String getConfig(String drmName) {
        return drmConfigRepository.findByDrmName(drmName).map(DrmConfig::getDrmConfig).orElse(null);
    }
}
