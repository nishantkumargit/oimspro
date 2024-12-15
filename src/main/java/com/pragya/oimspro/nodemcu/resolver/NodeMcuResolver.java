package com.pragya.oimspro.nodemcu.resolver;

import com.pragya.oimspro.machine.entity.Machine;
import com.pragya.oimspro.machine.service.MachineService;
import com.pragya.oimspro.nodemcu.entity.NodeMcu;
import com.pragya.oimspro.nodemcu.service.NodeMcuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nodemcu/")
public class NodeMcuResolver {
    @Autowired
    private NodeMcuService nodeMcuService;

    @PostMapping
    public void saveNodeMcu(@RequestBody NodeMcu nodeMcu) {
        nodeMcuService.saveNodemcu(nodeMcu);
    }

    @GetMapping
    public List<NodeMcu> getNodeMcu() {
        return nodeMcuService.getAllNodeMcu();
    }

    @DeleteMapping("{id}")
    public void deleteNodeMcu(@PathVariable long id) {
        nodeMcuService.deleteNodeMcu(id);
    }
}
