package com.pragya.oimspro.cronJobs;

import com.pragya.oimspro.machine.entity.Machine;
import com.pragya.oimspro.machine.entity.MachineConfigurationHistory;
import com.pragya.oimspro.machine.repository.MachineConfigurationHistoryRepository;
import com.pragya.oimspro.machine.service.MachineService;
import com.pragya.oimspro.nodemcu.service.McuMessageService;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Log4j2
public class MachineConfigurationHistoryCronJob {

    private final MachineConfigurationHistoryRepository machineConfigurationHistoryRepository;
    private final MachineService machineService;
    private final McuMessageService mcuMessageService;

    public MachineConfigurationHistoryCronJob(MachineConfigurationHistoryRepository machineConfigurationHistoryRepository, MachineService machineService, McuMessageService mcuMessageService) {
        this.machineConfigurationHistoryRepository = machineConfigurationHistoryRepository;
        this.machineService = machineService;
        this.mcuMessageService = mcuMessageService;
    }
    @Transactional
    @Scheduled(cron = "0 0 0 * * ?") // Run every day at 12:00
    public void addNewConfigurationEntries() {
        // Step 1: Fetch all active configurations (where end_time is NULL)

        List<MachineConfigurationHistory> activeConfigurations = machineConfigurationHistoryRepository.findActiveConfigurationsForPreviousDay(LocalDateTime.now().minusDays(1).toLocalDate().atStartOfDay());
        log.info("Active configurations: {}", activeConfigurations);
        // Step 2: Update end_time for current configurations to 12:00 today
        LocalDateTime endTime = LocalDate.now().atTime(12, 0);
//        LocalDateTime endTime = LocalDate.now("asia/kolkata").;
        for (MachineConfigurationHistory config : activeConfigurations) {
            Machine machine = machineService.getMachineById(config.getMachineId());
            config.setEndTimestamp(endTime);
            config.setEndCount(mcuMessageService.getCountAtTime(machine.getId(), endTime));
            machineConfigurationHistoryRepository.save(config);
        }

        // Step 3: Create new configuration entries for the next day
        LocalDateTime startTime = endTime.plusSeconds(1); // Start immediately after the previous end_time
        for (MachineConfigurationHistory config : activeConfigurations) {
            MachineConfigurationHistory newConfig = new MachineConfigurationHistory();
            newConfig.setMachineId(config.getMachineId());
            newConfig.setPartId(config.getPartId());
            newConfig.setRawMaterialId(config.getRawMaterialId());
            newConfig.setStartTimestamp(startTime);
            newConfig.setStartCount(config.getEndCount()); // Use the last count as the start count
            machineConfigurationHistoryRepository.save(newConfig);
        }
    }
}