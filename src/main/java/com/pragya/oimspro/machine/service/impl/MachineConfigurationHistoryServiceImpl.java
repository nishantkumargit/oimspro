package com.pragya.oimspro.machine.service.impl;

import com.pragya.oimspro.machine.entity.Machine;
import com.pragya.oimspro.machine.entity.MachineConfigurationHistory;
import com.pragya.oimspro.machine.repository.MachineConfigurationHistoryRepository;
import com.pragya.oimspro.machine.service.MachineConfigurationHistoryService;
import com.pragya.oimspro.nodemcu.service.McuMessageService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MachineConfigurationHistoryServiceImpl implements MachineConfigurationHistoryService {


    private MachineConfigurationHistoryRepository machineConfigurationHistoryRepository;

    private McuMessageService mcuMessageService;


    public MachineConfigurationHistoryServiceImpl(MachineConfigurationHistoryRepository machineConfigurationHistoryRepository,McuMessageService mcuMessageService) {
        this.machineConfigurationHistoryRepository = machineConfigurationHistoryRepository;
        this.mcuMessageService = mcuMessageService;
    }

    @Override
    public void saveMachineConfigurationHistory(MachineConfigurationHistory machineConfigurationHistory) {
        machineConfigurationHistoryRepository.save(machineConfigurationHistory);
    }

    @Override
    public void addNewMachineConfiguration(Machine newMachine,String entitiesUpdated) {
        LocalDateTime currTime = LocalDateTime.now();
        MachineConfigurationHistory machineConfigurationHistory = new MachineConfigurationHistory();
        machineConfigurationHistory.setMachineId(newMachine.getId());
        machineConfigurationHistory.setNodeMcuId(newMachine.getCurrentNodeMcuId());
        machineConfigurationHistory.setPartId(newMachine.getCurrentPartId());
        machineConfigurationHistory.setRawMaterialId(newMachine.getCurrentRawMaterialId());
        machineConfigurationHistory.setEntityChanged(entitiesUpdated);
        machineConfigurationHistory.setEndCount(mcuMessageService.getCountAtTime(newMachine.getId(), currTime));
//        machineConfigurationHistory.setOperatorId(newMachine.getMachineOperator().getId());
        machineConfigurationHistory.setStartTimestamp(currTime);
        machineConfigurationHistoryRepository.save(machineConfigurationHistory);
    }


    @Override
    public void updateLastMachineConfiguration(Machine oldMachine, Machine newMachine) {
        LocalDateTime currTime = LocalDateTime.now();
        MachineConfigurationHistory lastMachineConfigurationHistory = getMachineConfigurationHistoryByMachineId(oldMachine);
        if(lastMachineConfigurationHistory!=null){
            lastMachineConfigurationHistory.setEndTimestamp(LocalDateTime.now());
            lastMachineConfigurationHistory.setEndCount(mcuMessageService.getCountAtTime(newMachine.getId(), currTime));
            saveMachineConfigurationHistory(lastMachineConfigurationHistory);
        }
    }

    @Override
    public void saveNewMachineConfiguration(Machine machine) {
        addNewMachineConfiguration(machine,"New Machine");
    }
    @Override
    public MachineConfigurationHistory getMachineConfigurationHistoryByMachineId(Machine newMachine) {
        return machineConfigurationHistoryRepository.findByMachineId(newMachine.getId());
    }
}
