package com.pragya.oimspro.part.entity.service;

import com.pragya.oimspro.machine.entity.Machine;
import com.pragya.oimspro.machine.repository.MachineRepository;
import com.pragya.oimspro.part.entity.Part;
import com.pragya.oimspro.part.entity.Repository.PartRepository;
import com.pragya.oimspro.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class PartService {

    private final PartRepository partRepository;

    @Transactional
    public Part savePart(Part part) {
        return partRepository.save(part);
    }

    public List<Part> getAllParts() {
        return partRepository.findAll();
    }

    public void deletePart(long id) {
        partRepository.deleteById(id);
    }

}
