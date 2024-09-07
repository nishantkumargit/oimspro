package com.pragya.oimspro.machine.service;

import com.pragya.oimspro.machine.entity.Machine;
import com.pragya.oimspro.machine.repository.MachineRepository;
import com.pragya.oimspro.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MachineService {
    private final MachineRepository machineRepository;

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
}
