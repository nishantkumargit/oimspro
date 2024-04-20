package com.pragya.oimspro.machine.entity;

import com.pragya.oimspro.nodemcu.entity.NodeMcu;
import com.pragya.oimspro.part.entity.Part;
import com.pragya.oimspro.rawmaterial.entity.RawMaterial;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

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

    @Column(name="TYPE")
    private String type;

    @Column(name="STATUS")
    private String status;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "MACHINE_PART_MAPPING",
            joinColumns = @JoinColumn(name = "MACHINE_ID"),
            inverseJoinColumns = @JoinColumn(name = "PART_ID"))
    private Set<Part> part;


    @Nullable
    @ManyToMany
    private Set<RawMaterial> rawMaterial;

    @Nullable
    @OneToOne(cascade = CascadeType.ALL)
    private NodeMcu nodeMcu;
}
