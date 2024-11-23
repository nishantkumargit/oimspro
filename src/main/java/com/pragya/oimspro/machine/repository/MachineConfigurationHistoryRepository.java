package com.pragya.oimspro.machine.repository;

import com.pragya.oimspro.machine.entity.MachineConfigurationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineConfigurationHistoryRepository extends JpaRepository<MachineConfigurationHistory, Long> {
    MachineConfigurationHistory findByMachineId(long id);
}
