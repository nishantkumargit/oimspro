package com.pragya.oimspro.part.service;

import com.pragya.oimspro.part.entity.Part;
import com.pragya.oimspro.part.Repository.PartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PartService {

    Part savePart(Part part);

    List<Part> getAllParts();

    void deletePart(long id);

    Part getPartById(long partId);
}
