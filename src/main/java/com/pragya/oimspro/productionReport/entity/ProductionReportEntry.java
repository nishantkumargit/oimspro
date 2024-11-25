package com.pragya.oimspro.productionReport.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductionReportEntry {
    private String machineName;
    private String PartName;
    private String rawMaterialName;
    private String operatorName;
    private String status;
    private String grade;
    private Double partWeight;
    private Double rawMaterialSize;
    private String rawMaterialGrade;
    private Float workingHours;
    private String feedPerMin;
    private String idleProduction;
    private String actualProduction;
    private long totalCount;
    private LocalDateTime startTimestamp;
    private LocalDateTime endTimestamp;
}
