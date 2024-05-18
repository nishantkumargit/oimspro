package com.pragya.oimspro.nodemcu.repository;

import com.pragya.oimspro.nodemcu.entity.McuMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface McuMessageRepository extends JpaRepository<McuMessage, Long>{
}
