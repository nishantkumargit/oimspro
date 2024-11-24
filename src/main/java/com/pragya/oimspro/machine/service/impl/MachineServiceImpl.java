package com.pragya.oimspro.machine.service.impl;

import com.pragya.oimspro.machine.entity.Machine;
import com.pragya.oimspro.machine.repository.MachineRepository;
import com.pragya.oimspro.machine.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MachineServiceImpl implements MachineService {
    @Autowired
    MachineRepository machineRepository;
    @Transactional
    public Machine saveMachine(Machine machine) {
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
}
