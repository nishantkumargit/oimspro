package com.pragya.oimspro.part.service.impl;

import com.pragya.oimspro.part.Repository.PartRepository;
import com.pragya.oimspro.part.entity.Part;
import com.pragya.oimspro.part.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartServiceImpl implements PartService {
    @Autowired
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

    public Part getPartById(long partId) {
        return partRepository.findById(partId).orElse(null);
    }
}
