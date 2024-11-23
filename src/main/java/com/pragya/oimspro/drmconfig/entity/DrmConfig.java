package com.pragya.oimspro.drmconfig.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "DRM_CONFIG")
public class DrmConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @TableGenerator(
            name = "drm_id",
            table = "ID_GEN",
            pkColumnName = "GEN_KEY",
            valueColumnName = "GEN_VALUE",
            allocationSize = 1)
    private String drmId;

    @Column(name = "drm_name")
    private String drmName;
    @Column(name = "drm_config")
    private String drmConfig;
    @Column(name = "description")
    private String description;
    @Column(name = "created_by")
    private String modifiedBy;
    @Column(name = "created_at")
    private LocalDateTime modifiedAt;

    public String getDrmId() {
        return drmId;
    }

    public String getDrmName() {
        return drmName;
    }

    public String getDrmConfig() {
        return drmConfig;
    }

    public String getDescription() {
        return description;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    @Override
    public String toString() {
        return "drmConfig{" +
                "drmId='" + drmId + '\'' +
                ", drmName='" + drmName + '\'' +
                ", drmConfig='" + drmConfig + '\'' +
                ", description='" + description + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", modifiedAt=" + modifiedAt +
                '}';
    }
}
