package com.pragya.oimspro.machine.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "MACHINE")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @TableGenerator(
            name = "MACHINE_ID",
            table = "ID_GEN",
            pkColumnName = "GEN_KEY",
            valueColumnName = "GEN_VALUE",
            pkColumnValue = "MACHINE_ID",
            allocationSize = 1)
    private long id;

    @Column(name="CODE")
    private String code;

    @Column(name="NAME")
    private String name;

    @Column(name="MACHINE_OPERATOR")
    private String machineOperator;

    @Column(name="TYPE")
    private String type;

    @Column(name="STATUS")
    private String status;

    @Column(name="CURRENT_PART_ID")
    private Long currentPartId;

    @Column(name="CURRENT_RAW_MATERIAL_ID")
    private Long currentRawMaterialId;

    @Column(name="CURRENT_NODEMCU_ID")
    private Long currentNodeMcuId;

}
