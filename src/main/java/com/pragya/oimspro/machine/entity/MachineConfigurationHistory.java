package com.pragya.oimspro.machine.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "MACHINE_CONFIGURATION_HISTORY")
public class MachineConfigurationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @TableGenerator(
            name = "MACHINE_CONFIGURATION_HISTORY_ID",
            table = "ID_GEN",
            pkColumnName = "GEN_KEY",
            valueColumnName = "GEN_VALUE",
            pkColumnValue = "MACHINE_ID",
            allocationSize = 1)
    private Long id;

    @Column(name = "START_COUNT")
    private long startCount;

    @Column(name = "END_COUNT")
    private long endCount;

    @Column(name = "MACHINE_ID")
    private Long machineId;

    @Column(name = "PART_ID")
    private Long partId;
    @Column(name = "RAW_MATERIAL_ID")
    private Long rawMaterialId;
    @Column(name = "OPERATOR_ID")
    private Long operatorId;
    @Column(name = "NODE_MCU_ID")
    private Long nodeMcuId;
    @Column(name= "ENTITY_CHANGED")
    private String entityChanged;
    @Column(name = "START_TIMESTAMP")
    private LocalDateTime startTimestamp;
    @Column(name = "END_TIMESTAMP")
    private LocalDateTime endTimestamp;
    @Column(name = "CREATED_AT")
    private LocalDateTime createAt;


    @PrePersist
    public void prePersist() {
        this.createAt = LocalDateTime.now();
    }
}
