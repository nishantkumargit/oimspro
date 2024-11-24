package com.pragya.oimspro.machineSchedular.resolver;

import com.pragya.oimspro.machineSchedular.service.MachineSchedularService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/machineschedule/")
@RequiredArgsConstructor
public class MachineSchedularController {
    private final MachineSchedularService machineSchedularService;

    @PostMapping("/schedule")
    public void scheduleMachine(@RequestParam Long machineId, @RequestParam Long partId, @RequestParam Long rawMaterialId) {
        machineSchedularService.scheduleMachine(machineId, partId, rawMaterialId);
    }

}
