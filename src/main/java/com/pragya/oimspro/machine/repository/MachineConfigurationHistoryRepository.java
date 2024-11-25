package com.pragya.oimspro.machine.repository;

import com.pragya.oimspro.machine.entity.MachineConfigurationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MachineConfigurationHistoryRepository extends JpaRepository<MachineConfigurationHistory, Long> {

    @Query("SELECT m FROM MachineConfigurationHistory m WHERE m.machineId = :machineId AND m.startTimestamp >= :startOfDay AND m.startTimestamp < :endOfDay ORDER BY m.startTimestamp")
    List<MachineConfigurationHistory> findConfigurationsForDayForMachine(@Param("machineId") Long machineId, @Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);

    @Query("SELECT m FROM MachineConfigurationHistory m WHERE  m.startTimestamp >= :startOfDay AND m.startTimestamp < :endOfDay ORDER BY m.startTimestamp")
    List<MachineConfigurationHistory> findConfigurationsForDay(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);

    @Query("SELECT m FROM MachineConfigurationHistory m WHERE m.machineId = :machineId and m.endTimestamp is not NULL ORDER BY m.startTimestamp DESC")
    Optional<MachineConfigurationHistory> findLastConfiguration(@Param("machineId") Long machineId);

    MachineConfigurationHistory findByMachineId(long id);
}
