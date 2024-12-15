package com.pragya.oimspro.machine.service;

import com.pragya.oimspro.machine.entity.Machine;
import com.pragya.oimspro.machine.repository.MachineRepository;
import com.pragya.oimspro.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


public interface MachineService {
    public Machine saveMachine(Machine machine);

    public List<Machine> getAllMachines();

    public void deleteMachine(long id);

    Machine getMachineById(long machineId);

    long fetchMachineCount(Machine newMachine, LocalDateTime currTime);
}
