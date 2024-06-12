package com.pragya.oimspro.nodemcu.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "NODE_MCU")
public class NodeMcu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String deviceId;
    private String name;
    @Enumerated(EnumType.STRING)
    private Status status;
    //    @OneToMany(mappedBy = "nodeMcu", cascade = CascadeType.ALL)
//    private List<MCUMessage> messages;
    private Date installationDate;
    private Date receivedTime;
}

