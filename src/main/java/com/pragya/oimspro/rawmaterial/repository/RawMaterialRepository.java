package com.pragya.oimspro.rawmaterial.repository;

import com.pragya.oimspro.rawmaterial.entity.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawMaterialRepository extends JpaRepository<RawMaterial,Long> {
}
