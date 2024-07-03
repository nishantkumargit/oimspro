package com.pragya.oimspro.nodemcu.repository;

import com.pragya.oimspro.nodemcu.entity.McuMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface McuMessageRepository extends JpaRepository<McuMessage, Long>{
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO mcu_message (device_id, machine_id, count, published_time, nodemcu_code, hourly_bucket) " +
            "VALUES (:deviceId, :siteId, :count, :publishedTime, :nodeMcuCode, :hourlyBucket) " +
            "ON CONFLICT (device_id,hourly_bucket) DO UPDATE SET " +
            "machine_id = EXCLUDED.machine_id, " +
            "count = EXCLUDED.count, " +
            "published_time = EXCLUDED.published_time, " +
            "nodemcu_code = EXCLUDED.nodemcu_code", nativeQuery = true
            )
    void upsertMcuMessage(@Param("deviceId") String deviceId,
                          @Param("siteId") String siteId,
                          @Param("count") int count,
                          @Param("publishedTime") Date publishedTime,
                          @Param("nodeMcuCode") String nodeMcuCode,
                          @Param("hourlyBucket") Date hourlyBucket);
}
