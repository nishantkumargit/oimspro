package com.pragya.oimspro.machine.service.impl;

import com.pragya.oimspro.machine.entity.Machine;
import com.pragya.oimspro.machine.repository.MachineRepository;
import com.pragya.oimspro.machine.service.MachineConfigurationHistoryService;
import com.pragya.oimspro.machine.service.MachineService;
import com.pragya.oimspro.nodemcu.service.McuMessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MachineServiceImpl implements MachineService {

    private final MachineRepository machineRepository;
    private final McuMessageService mcuMessageService;
    private final MachineConfigurationHistoryService machineConfigurationHistoryService;

    public MachineServiceImpl(MachineRepository machineRepository, MachineConfigurationHistoryService machineConfigurationHistoryService,McuMessageService mcuMessageService) {
        this.machineRepository = machineRepository;
        this.machineConfigurationHistoryService = machineConfigurationHistoryService;
        this.mcuMessageService = mcuMessageService;
    }

    @Transactional
    public Machine saveMachine(Machine machine) {
        machineConfigurationHistoryService.saveNewMachineConfiguration(machine);
        return machineRepository.save(machine);
    }

    public List<Machine> getAllMachines() {
        return machineRepository.findAll();
    }

    public void deleteMachine(long id) {
        machineRepository.deleteById(id);
    }

    @Override
    public Machine getMachineById(long machineId) {
        return machineRepository.findById(machineId).orElse(null);
    }

    @Override
    public long fetchMachineCount(Machine newMachine, LocalDateTime currTime) {
        long machineId = newMachine.getId();
        if(getMachineById(machineId)==null || machineId==0 ) {
            return 0;
        }
        return mcuMessageService.getCountAtTime(machineId, currTime);
    }

}
