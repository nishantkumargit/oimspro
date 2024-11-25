package com.pragya.oimspro.drmconfig.repository;

import com.pragya.oimspro.drmconfig.entity.DrmConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DrmConfigRepository extends JpaRepository<DrmConfig,Long> {
    Optional<DrmConfig> findByDrmName(String drmName); // New method

}
