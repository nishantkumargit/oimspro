package com.pragya.oimspro.rawmaterial.entity;

import com.pragya.oimspro.machine.entity.Machine;
import com.pragya.oimspro.part.entity.Part;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "RAW_MATERIAL")
public class RawMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @TableGenerator(
            name = "RAW_MATERIAL_ID",
            table = "ID_GEN",
            pkColumnName = "GEN_KEY",
            valueColumnName = "GEN_VALUE",
            pkColumnValue = "RAW_MATERIAL_ID",
            allocationSize = 1)
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "GRADE")
    private String grade;

    @Column(name = "MATERIAL")
    private String material;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "RAW_MATERIAL_PART_MAPPING",
            joinColumns = @JoinColumn(name = "RAW_MATERIAL_ID"),
            inverseJoinColumns = @JoinColumn(name = "PART_ID"))
    private Set<Part> parts;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "RAW_MATERIAL_MACHINE_MAPPING",
            joinColumns = @JoinColumn(name = "RAW_MATERIAL_ID"),
            inverseJoinColumns = @JoinColumn(name = "MACHINE_ID"))
    private Set<Machine> machine;

    @Column(name = "SIZE")
    private double size;

    @Column(name = "QUANTITY")
    private double quantity;
}
