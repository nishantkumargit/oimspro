package com.pragya.oimspro.machine.repository;

import com.pragya.oimspro.machine.entity.Machine;
import com.pragya.oimspro.machine.entity.MachineConfigurationHistory;
import com.pragya.oimspro.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {
    Optional<Machine> findByCurrentNodeMcuId(Long deviceId);
}
