package com.pragya.oimspro.rawmaterial.service.impl;

import com.pragya.oimspro.part.entity.Part;
import com.pragya.oimspro.rawmaterial.entity.RawMaterial;
import com.pragya.oimspro.rawmaterial.repository.RawMaterialRepository;
import com.pragya.oimspro.rawmaterial.service.RawMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RawMaterialServiceImpl implements RawMaterialService {

    @Autowired
    RawMaterialRepository rawMaterialRepository;
    public void saveMaterial(RawMaterial rawMaterial) {
        rawMaterialRepository.save(rawMaterial);
    }

    @Override
    public List<RawMaterial> getAllMaterial() {
        return rawMaterialRepository.findAll();
    }

    @Override
    public void deleteMaterial(long id) {
        rawMaterialRepository.deleteById(id);
    }

    @Override
    public RawMaterial getRawMaterialById(long rawMaterialId) {
        return rawMaterialRepository.findById(rawMaterialId).orElse(null);
    }
}
