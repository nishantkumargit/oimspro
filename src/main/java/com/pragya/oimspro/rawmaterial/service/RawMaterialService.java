package com.pragya.oimspro.rawmaterial.service;

import com.pragya.oimspro.part.entity.Part;
import com.pragya.oimspro.rawmaterial.entity.RawMaterial;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RawMaterialService {
    void saveMaterial(RawMaterial rawMaterial);

    List<RawMaterial> getAllMaterial();

    void deleteMaterial(long id);

    RawMaterial getRawMaterialById(long rawMaterialId);
}
