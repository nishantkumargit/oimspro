package com.pragya.oimspro.part.entity.resolver;


import com.pragya.oimspro.machine.entity.Machine;
import com.pragya.oimspro.machine.service.MachineService;
import com.pragya.oimspro.part.entity.Part;
import com.pragya.oimspro.part.entity.service.PartService;
import com.pragya.oimspro.user.entity.User;
import com.pragya.oimspro.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("part")
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

