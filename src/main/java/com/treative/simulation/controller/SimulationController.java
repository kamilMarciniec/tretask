package com.treative.simulation.controller;


import com.treative.simulation.domain.Simulation;
import com.treative.simulation.service.impl.SimulationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/simulation")
public class SimulationController {

    @Autowired
    SimulationServiceImpl simulationService;

    @PostMapping("")
    public ResponseEntity<Simulation> createSimulation(@RequestBody Simulation simulation) {
        return simulationService.createSimulation(simulation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Simulation> getSimulationById(@PathVariable("id") long id) {
        return simulationService.getSimulationById(id);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Simulation> getSimulationByName(@PathVariable("name") String name) {
        return simulationService.getSimulationByName(name);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSimulationById(@PathVariable("id") long id) {
        return simulationService.deleteSimulationById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Simulation> updateSimulation(@PathVariable("id") long id, @RequestBody Simulation simulation) {
        return simulationService.updateSimulation(id, simulation);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Simulation> updateSimulationFieldsById(@PathVariable("id") long id, @RequestBody Map<String, Object> fields) {
        return simulationService.updateSimulationFields(id, fields);
    }
}
