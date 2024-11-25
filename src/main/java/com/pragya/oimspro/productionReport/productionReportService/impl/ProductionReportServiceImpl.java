package com.pragya.oimspro.productionReport.productionReportService.impl;

import com.pragya.oimspro.machine.entity.Machine;
import com.pragya.oimspro.machine.entity.MachineConfigurationHistory;
import com.pragya.oimspro.machine.repository.MachineConfigurationHistoryRepository;
import com.pragya.oimspro.machine.repository.MachineRepository;
import com.pragya.oimspro.nodemcu.entity.NodeMcu;
import com.pragya.oimspro.nodemcu.repository.NodeMcuRepository;
import com.pragya.oimspro.part.entity.Part;
import com.pragya.oimspro.part.repository.PartRepository;
import com.pragya.oimspro.nodemcu.repository.McuMessageRepository;
import com.pragya.oimspro.productionReport.entity.ProductionReportEntry;
import com.pragya.oimspro.productionReport.productionReportService.ProductionReportService;
import com.pragya.oimspro.rawmaterial.entity.RawMaterial;
import com.pragya.oimspro.rawmaterial.repository.RawMaterialRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductionReportServiceImpl implements ProductionReportService {
    @Autowired
    private McuMessageRepository mcuMessageRepository;

    @Autowired
    private MachineConfigurationHistoryRepository machineConfigurationHistoryRepository;
    @Autowired
    private MachineRepository machineRepository;

    @Autowired
    private PartRepository partRepository;

    @Autowired
    NodeMcuRepository nodeMcuRepository;

    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    Logger logger = LoggerFactory.getLogger(ProductionReportServiceImpl.class);

    public List<ProductionReportEntry> generateDailyMachineReport(Long machineId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();

        // Fetch machine configurations for the day
        List<MachineConfigurationHistory> configurations =
                machineConfigurationHistoryRepository.findConfigurationsForDayForMachine(machineId, startOfDay, endOfDay);


        List<ProductionReportEntry> reports = new ArrayList<>();

        for (int i = 0; i < configurations.size(); i++) {
            MachineConfigurationHistory config = configurations.get(i);
            LocalDateTime configStart = config.getStartTimestamp();
            LocalDateTime configEnd = (i < configurations.size() - 1)
                    ? configurations.get(i + 1).getStartTimestamp()
                    : endOfDay;

            // Fetch related machine info
            Machine machine = machineRepository.findById(config.getMachineId())
                    .orElseThrow(() -> new RuntimeException("Machine not found"));

            // Fetch related part info
            String partName = partRepository.findById(config.getPartId())
                    .map(Part::getName)
                    .orElse("Unknown Part");

            // Fetch related raw material info
            String rawMaterialName = rawMaterialRepository.findById(config.getRawMaterialId())
                    .map(RawMaterial::getName)
                    .orElse("Unknown Material");

            String nodeMcuDeviceCode = nodeMcuRepository.findById(machine.getCurrentNodeMcuId())
                    .map(NodeMcu::getDeviceId)
                    .orElse("Unknown NodeMcu");

            logger.info("Generating report for machine: {}, part: {}, raw material: {}, start: {}, end: {} , nodeMcuId: {} config start: {} config end: {}",
                    machine.getName(), partName, rawMaterialName, configStart, configEnd,machine.getCurrentNodeMcuId(),configStart,configEnd);
            // Fetch messages from MCU for this configuration period
            Long messageCount =Optional.ofNullable(mcuMessageRepository.getSumOfCountsForDevice(
                    nodeMcuDeviceCode, configStart, configEnd)).orElse(0L);

            // Build the report
            ProductionReportEntry report = new ProductionReportEntry();
            report.setMachineName(machine.getName());
            report.setOperatorName(config.getOperatorId()==null ? "test operator" : config.getOperatorId().toString()); // Fetch operator details if needed
            report.setPartName(partName);
            report.setRawMaterialName(rawMaterialName);
            report.setTotalCount(messageCount);
            report.setStartTimestamp(configStart);
            report.setEndTimestamp(configEnd);

            reports.add(report);
        }

        return reports;
    }


    public List<ProductionReportEntry> generateDailyReport(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();

        // Fetch machine configurations for the day
        List<MachineConfigurationHistory> configurations =
                machineConfigurationHistoryRepository.findConfigurationsForDay(startOfDay, endOfDay);


        List<ProductionReportEntry> reports = new ArrayList<>();

        for (int i = 0; i < configurations.size(); i++) {
            MachineConfigurationHistory config = configurations.get(i);
            LocalDateTime configStart = config.getStartTimestamp();
            LocalDateTime configEnd = (i < configurations.size() - 1)
                    ? configurations.get(i + 1).getStartTimestamp()
                    : endOfDay;

            // Fetch related machine info
            Optional<Machine> machine = machineRepository.findById(config.getMachineId());
            String machineName = machine.map(Machine::getName)
                    .orElseThrow(() -> new RuntimeException("Machine not found"));
            String machineStatus = machine.map(Machine::getStatus)
                    .orElse("Unknown Status");

            // Fetch related part info
            Optional<Part> part = partRepository.findById(config.getPartId());
            String partName = part.map(Part::getName)
                    .orElse("Unknown Part");
            Double partWeight = part.map(Part::getWeight)
                    .orElse(0.0);

            // Fetch related raw material info
            Optional<RawMaterial> rawMaterial = rawMaterialRepository.findById(config.getRawMaterialId());
            String rawMaterialName = rawMaterial
                    .map(RawMaterial::getName)
                    .orElse("Unknown Material");
            Double rawMaterialSize = rawMaterial
                    .map(RawMaterial::getSize)
                    .orElse(0.0);
            String rawMaterialGrade = rawMaterial
                    .map(RawMaterial::getGrade)
                    .orElse("Unknown Grade");


            String nodeMcuDeviceCode = nodeMcuRepository.findById(machine.get().getCurrentNodeMcuId())
                    .map(NodeMcu::getDeviceId)
                    .orElse("Unknown NodeMcu");

            logger.info("Generating report for machine: {}, part: {}, raw material: {}, start: {}, end: {} , nodeMcuId: {} config start: {} config end: {}",
                    machineName, partName, rawMaterialName, configStart, configEnd,machine.get().getCurrentNodeMcuId(),configStart,configEnd);
            // Fetch messages from MCU for this configuration period
            Long messageCount =Optional.ofNullable(mcuMessageRepository.getSumOfCountsForDevice(
                    nodeMcuDeviceCode, configStart, configEnd)).orElse(0L);

            Float workingHours = 0.0f;

            String idealProduction = "NA";
            String actualProduction = "NA";
            String feedPerMin = "NA";

            // Build the report
            ProductionReportEntry report = new ProductionReportEntry();
            report.setMachineName(machineName);
            report.setOperatorName(config.getOperatorId()==null ? "test operator" : config.getOperatorId().toString()); // Fetch operator details if needed
            report.setPartName(partName);
            report.setPartWeight(partWeight);
            report.setRawMaterialName(rawMaterialName);
            report.setRawMaterialSize(rawMaterialSize);
            report.setRawMaterialGrade(rawMaterialGrade);
            report.setTotalCount(messageCount);
            report.setWorkingHours(workingHours);
            report.setFeedPerMin(feedPerMin);
            report.setIdleProduction(idealProduction);
            report.setActualProduction(actualProduction);
            report.setStartTimestamp(configStart);
            report.setEndTimestamp(configEnd);
            reports.add(report);
        }

        return reports;
    }
}
