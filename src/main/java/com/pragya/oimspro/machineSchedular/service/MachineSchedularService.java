package com.pragya.oimspro.machineSchedular.service;

import com.pragya.oimspro.machine.entity.Machine;
import com.pragya.oimspro.part.entity.Part;
import com.pragya.oimspro.rawmaterial.entity.RawMaterial;

public interface MachineSchedularService {

    void scheduleMachine(Long machineId, Long partId, Long rawMaterialId);

    void updateMachineConfig(Machine machine, Part part, RawMaterial rawMaterial);
}
