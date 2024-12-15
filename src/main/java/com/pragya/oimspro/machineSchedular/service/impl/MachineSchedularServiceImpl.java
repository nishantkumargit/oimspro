package com.pragya.oimspro.machineSchedular.service.impl;

import com.pragya.oimspro.machine.entity.Entities;
import com.pragya.oimspro.machine.entity.Machine;
import com.pragya.oimspro.machine.entity.MachineConfigurationHistory;
import com.pragya.oimspro.machine.service.MachineConfigurationHistoryService;
import com.pragya.oimspro.machine.service.MachineService;
import com.pragya.oimspro.machineSchedular.service.MachineSchedularService;
import com.pragya.oimspro.part.entity.Part;
import com.pragya.oimspro.part.service.PartService;
import com.pragya.oimspro.rawmaterial.entity.RawMaterial;
import com.pragya.oimspro.rawmaterial.service.RawMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MachineSchedularServiceImpl implements MachineSchedularService {

    @Autowired
    PartService partService;
    @Autowired
    RawMaterialService rawMaterialService;
    @Autowired
    MachineService machineService;

    @Autowired
    MachineConfigurationHistoryService machineConfigurationHistoryService;

    public void scheduleMachine(Long machineId, Long partId, Long rawMaterialId) {
        Machine machine = machineService.getMachineById(machineId);
        Part part = partService.getPartById(partId);
        RawMaterial rawMaterial = rawMaterialService.getRawMaterialById(rawMaterialId);
        if(partId.equals(machine.getCurrentPartId()) && rawMaterialId.equals(machine.getCurrentRawMaterialId())) {
            return;
        }
        updateMachineConfig(machine, part, rawMaterial);
    }

    @Override
    public void updateMachineConfig(Machine oldMachine, Part part, RawMaterial rawMaterial) {
        if(oldMachine == null || part == null || rawMaterial == null) {
            throw new RuntimeException("Invalid machine, part or raw material");
        }

        Machine newMachine = new Machine();
        newMachine.setCode(oldMachine.getCode());
        newMachine.setName(oldMachine.getName());
        newMachine.setType(oldMachine.getType());
        newMachine.setStatus(oldMachine.getStatus());
        newMachine.setMachineOperator(oldMachine.getMachineOperator());
        newMachine.setCurrentNodeMcuId(oldMachine.getCurrentNodeMcuId());
        newMachine.setCurrentPartId(part.getId());
        newMachine.setCurrentRawMaterialId(rawMaterial.getId());
        String entitiesUpdated = entityUpdated(oldMachine, part, rawMaterial);
        machineService.saveMachine(newMachine);
        machineConfigurationHistoryService.updateLastMachineConfiguration(oldMachine,newMachine);
        machineConfigurationHistoryService.addNewMachineConfiguration(newMachine,entitiesUpdated);
    }

    private String entityUpdated(Machine oldMachine, Part part, RawMaterial rawMaterial) {
        List<Entities> entityChanges = new ArrayList<>();
        if(oldMachine.getCurrentPartId() != part.getId()) {
            entityChanges.add(Entities.PART);
        }
        if (oldMachine.getCurrentRawMaterialId() != rawMaterial.getId()) {
            entityChanges.add(Entities.RAW_MATERIAL);
        }
        StringBuilder resultEntitiesChanges = new StringBuilder();
        for(Entities entity : entityChanges) {
            resultEntitiesChanges.append(entity.toString()).append(", ");
        }
        return resultEntitiesChanges.substring(0,resultEntitiesChanges.length()-2);

    }

}
