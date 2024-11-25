package com.pragya.oimspro.machine.service;

import com.pragya.oimspro.machine.entity.Machine;
import com.pragya.oimspro.machine.entity.MachineConfigurationHistory;
import com.pragya.oimspro.machine.repository.MachineConfigurationHistoryRepository;
import com.pragya.oimspro.machine.repository.MachineRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class MachineConfigScheduler {

    @Autowired
    private MachineRepository machineRepository;

    @Autowired
    private MachineConfigurationHistoryRepository machineConfigHistoryRepository;

    @Transactional
    @Scheduled(cron = "0 0 0 * * ?") // Runs daily at midnight
    public void addDailyMachineConfigurations() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfYesterday = startOfDay.minusSeconds(1);

        // Fetch all machines
        List<Machine> machines = machineRepository.findAll();

        for (Machine machine : machines) {
            // Check if the last configuration was created for the previous day
            Optional<MachineConfigurationHistory> lastConfigOpt = machineConfigHistoryRepository.findLastConfiguration(machine.getId());

            if (lastConfigOpt.isPresent()) {
                MachineConfigurationHistory lastConfig = lastConfigOpt.get();

                // If the last configuration's timestamp is before the current day, add a new entry
                if (lastConfig.getStartTimestamp().isBefore(startOfDay)) {
                    MachineConfigurationHistory newConfig = new MachineConfigurationHistory();

                    // Copy details from the last configuration
                    newConfig.setMachineId(lastConfig.getMachineId());
                    newConfig.setPartId(lastConfig.getPartId());
                    newConfig.setRawMaterialId(lastConfig.getRawMaterialId());
                    newConfig.setOperatorId(lastConfig.getOperatorId());
                    newConfig.setNodeMcuId(lastConfig.getNodeMcuId());
                    newConfig.setEntityChanged("SCHEDULER"); // Indicates the entry was created by the scheduler
                    newConfig.setStartTimestamp(startOfDay);

                    // Save the new configuration
                    machineConfigHistoryRepository.save(newConfig);
                }
            }
//            else {
//                // If no configuration exists, add a default one
//                MachineConfigurationHistory newConfig = new MachineConfigurationHistory();
//                newConfig.setMachineId(machine.getId());
//                newConfig.setEntityChanged("SCHEDULER_DEFAULT"); // Indicates a default entry
//                newConfig.setStartTimestamp(startOfDay);
//
//                // Save the new configuration
//                machineConfigHistoryRepository.save(newConfig);
//            }
        }
    }
}