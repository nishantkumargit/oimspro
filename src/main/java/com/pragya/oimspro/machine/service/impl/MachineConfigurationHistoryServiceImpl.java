package com.pragya.oimspro.machine.service.impl;

import com.pragya.oimspro.machine.entity.Machine;
import com.pragya.oimspro.machine.entity.MachineConfigurationHistory;
import com.pragya.oimspro.machine.repository.MachineConfigurationHistoryRepository;
import com.pragya.oimspro.machine.service.MachineConfigurationHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
@Service
@RequiredArgsConstructor
public class MachineConfigurationHistoryServiceImpl implements MachineConfigurationHistoryService {

    @Autowired
    MachineConfigurationHistoryRepository machineConfigurationHistoryRepository;
    @Override
    public void saveMachineConfigurationHistory(MachineConfigurationHistory machineConfigurationHistory) {
        machineConfigurationHistoryRepository.save(machineConfigurationHistory);
    }

    @Override
    public void addNewMachineConfiguration(Machine newMachine,String entitiesUpdated) {
        MachineConfigurationHistory machineConfigurationHistory = new MachineConfigurationHistory();
        machineConfigurationHistory.setMachineId(newMachine.getId());
        machineConfigurationHistory.setNodeMcuId(newMachine.getCurrentNodeMcuId());
        machineConfigurationHistory.setPartId(newMachine.getCurrentPartId());
        machineConfigurationHistory.setRawMaterialId(newMachine.getCurrentRawMaterialId());
        machineConfigurationHistory.setEntityChanged(entitiesUpdated);
//        machineConfigurationHistory.setOperatorId(newMachine.getMachineOperator().getId());
        machineConfigurationHistory.setStartTimestamp(LocalDateTime.now());
        machineConfigurationHistoryRepository.save(machineConfigurationHistory);
    }


    @Override
    public void updateLastMachineConfiguration(Machine oldMachine, Machine newMachine) {
        MachineConfigurationHistory lastMachineConfigurationHistory = getMachineConfigurationHistoryByMachineId(oldMachine);
        if(lastMachineConfigurationHistory!=null){
            lastMachineConfigurationHistory.setEndTimestamp(LocalDateTime.now());
            saveMachineConfigurationHistory(lastMachineConfigurationHistory);
        }
    }

    @Override
    public MachineConfigurationHistory getMachineConfigurationHistoryByMachineId(Machine newMachine) {
        return machineConfigurationHistoryRepository.findByMachineId(newMachine.getId());
    }
}
