package com.pragya.oimspro.part.entity;

import jakarta.persistence.*;
import lombok.Data;

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

//    @ManyToMany
//    private Set<RawMaterial> rawMaterial;

    @Column(name="QUANTITY")
    private double quantity;

    @Column(name="WEIGHT")
    private double weight;

    @Column(name="DRAWING_URL")
    private String drawingUrl;
}