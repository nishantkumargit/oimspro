package com.pragya.oimspro.nodemcu.repository;

import com.pragya.oimspro.nodemcu.entity.McuMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface McuMessageRepository extends JpaRepository<McuMessage, Long>{

    @Query("SELECT SUM(m.count) FROM McuMessage m WHERE m.deviceId = :deviceId AND m.publishedTime BETWEEN :start AND :end")
    Long getSumOfCountsForDevice(@Param("deviceId") String deviceId,
                                 @Param("start") LocalDateTime start,
                                 @Param("end") LocalDateTime end);

    @Query("SELECT SUM(m.count) FROM McuMessage m WHERE m.publishedTime BETWEEN :start AND :end")
    Long getSumOfCountsForAllDevices(@Param("start") LocalDateTime start,
                                     @Param("end") LocalDateTime end);

    @Query("SELECT m FROM McuMessage m WHERE m.deviceId = :deviceId AND m.publishedTime <= :targetTimestamp ORDER BY m.publishedTime DESC")
    List<McuMessage> getNearestRecordToTimestamp(@Param("deviceId") String deviceId,
                                       @Param("targetTimestamp") LocalDateTime targetTimestamp);

    @Query("SELECT count FROM McuMessage m WHERE m.siteId = :machineId AND m.publishedTime <= :time ORDER BY m.publishedTime DESC LIMIT 1")
    Long getCountAtTime(@Param("machineId") Long machineId, @Param("time") LocalDateTime time);


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
