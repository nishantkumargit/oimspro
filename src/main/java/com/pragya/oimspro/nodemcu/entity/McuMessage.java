package com.pragya.oimspro.nodemcu.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Data
@Table(name = "MCU_MESSAGE")
public class McuMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String deviceId;
    @Column(name = "machine_id")
    private String siteId;
    private int count;

    private Date publishedTime;
    @Column(name = "nodemcu_code")
    private String nodeMcuCode;

    @Override
    public String toString() {
        return "MCUMessage{" +
                "id=" + id +
                ", deviceId='" + deviceId + '\'' +
                ", siteId='" + siteId + '\'' +
                ", count=" + count +
                ", publishedTime=" + publishedTime +
                ", nodeMcuCode='" + nodeMcuCode + '\'' +
                '}';
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(Date publishedTime) {
        this.publishedTime = publishedTime;
    }
}