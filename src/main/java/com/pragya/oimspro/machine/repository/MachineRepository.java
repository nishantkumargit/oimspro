package com.pragya.oimspro.machine.repository;

import com.pragya.oimspro.machine.entity.Machine;
import com.pragya.oimspro.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {}
