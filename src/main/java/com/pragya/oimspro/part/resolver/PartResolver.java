package com.pragya.oimspro.part.resolver;


import com.pragya.oimspro.part.entity.Part;
import com.pragya.oimspro.part.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RestController
@RequestMapping("/api/part/")
@RequiredArgsConstructor

public class PartResolver {

    @Autowired
    private PartService partService;


    @PostMapping
    public void savePart(@RequestBody Part part) {
        partService.savePart(part);
    }

    @GetMapping
    public List<Part> getParts() {
        return partService.getAllParts();
    }

    @DeleteMapping("{id}")
    public void deleteMachine(@PathVariable long id) {
        partService.deletePart(id);
    }


}

