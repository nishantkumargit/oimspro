package com.pragya.oimspro.productionReport.productionReportService;

import com.pragya.oimspro.productionReport.entity.ProductionReportEntry;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public interface ProductionReportService {
    List<ProductionReportEntry> generateDailyMachineReport(Long machineId, LocalDate reportDate);
    List<ProductionReportEntry> generateDailyReport(LocalDate date);
}