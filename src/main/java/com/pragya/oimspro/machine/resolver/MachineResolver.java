package com.pragya.oimspro.machine.resolver;

import com.pragya.oimspro.machine.entity.Machine;
import com.pragya.oimspro.machine.service.MachineService;
import com.pragya.oimspro.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("machine")
@RequiredArgsConstructor
public class MachineResolver {

    private final MachineService machineService;

    @PostMapping
    public void saveMachine(@RequestBody Machine machine) {
        machineService.saveMachine(machine);
    }

    @GetMapping
    public List<Machine> getMachines() {
        return machineService.getAllMachines();
    }

    @DeleteMapping("{id}")
    public void deleteMachine(@PathVariable long id) {
        machineService.deleteMachine(id);
    }
}
