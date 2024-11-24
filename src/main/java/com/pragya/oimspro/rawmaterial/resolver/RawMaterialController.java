package com.pragya.oimspro.rawmaterial.resolver;

import com.pragya.oimspro.part.entity.Part;
import com.pragya.oimspro.part.service.PartService;
import com.pragya.oimspro.rawmaterial.entity.RawMaterial;
import com.pragya.oimspro.rawmaterial.service.RawMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rawmaterial/")
@RequiredArgsConstructor
public class RawMaterialController {
    @Autowired
    private RawMaterialService rawMaterialService;


    @PostMapping
    public void saveRawMaterial(@RequestBody RawMaterial rawMaterial) {
        rawMaterialService.saveMaterial(rawMaterial);
    }

    @GetMapping
    public List<RawMaterial> getParts() {
        return rawMaterialService.getAllMaterial();
    }

    @DeleteMapping("{id}")
    public void deleteMachine(@PathVariable long id) {
        rawMaterialService.deleteMaterial(id);
    }

}
