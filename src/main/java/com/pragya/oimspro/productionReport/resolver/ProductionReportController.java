package com.pragya.oimspro.productionReport.resolver;

import com.pragya.oimspro.productionReport.entity.ProductionReportEntry;
import com.pragya.oimspro.productionReport.productionReportService.ProductionReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ProductionReportController {

    @Autowired
    private ProductionReportService productionReportService;

    @GetMapping("/daily/machine")
    public List<ProductionReportEntry> getDailyMachineReport(@RequestParam Long machineId, @RequestParam String date) {
        LocalDate reportDate = LocalDate.parse(date); // Format: yyyy-MM-dd
        return productionReportService.generateDailyMachineReport(machineId, reportDate);
    }
    @GetMapping("/daily")
    public List<ProductionReportEntry> getDailyReport(@RequestParam String date) {
        LocalDate reportDate = LocalDate.parse(date); // Format: yyyy-MM-dd
        return productionReportService.generateDailyReport(reportDate);
    }
}