package com.pragya.oimspro.part.entity.Repository;

import com.pragya.oimspro.machine.entity.Machine;
import com.pragya.oimspro.part.entity.Part;
import com.pragya.oimspro.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {}