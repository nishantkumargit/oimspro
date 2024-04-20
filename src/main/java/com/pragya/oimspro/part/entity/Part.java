package com.pragya.oimspro.part.entity;

import com.pragya.oimspro.machine.entity.Machine;
import com.pragya.oimspro.rawmaterial.entity.RawMaterial;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "PART")
public class Part{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @TableGenerator(
            name = "PART_ID",
            table = "ID_GEN",
            pkColumnName = "GEN_KEY",
            valueColumnName = "GEN_VALUE",
            pkColumnValue = "PART_ID",
            allocationSize = 1)
    private long id;

    @Column(name="NAME")
    private String name;

    @ManyToMany
    private Set<RawMaterial> rawMaterial;

    @Column(name="QUANTITY")
    private double quantity;

    @Column(name="WEIGHT")
    private double weight;

    @Column(name="DRAWING_URL")
    private String drawingUrl;

    @ManyToMany
    private Set<Machine> machines;
}