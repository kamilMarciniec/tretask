package com.treative.simulation.service;

import com.treative.simulation.domain.Simulation;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface SimulationService {
    ResponseEntity createSimulation(Simulation simulationData);
    ResponseEntity<Simulation> getSimulationById(long id);
    ResponseEntity<Simulation> getSimulationByName(String name);
    ResponseEntity<String> deleteSimulationById(long id);
    ResponseEntity<Simulation> updateSimulation(long id, Simulation simulation);
    ResponseEntity<Simulation> updateSimulationFields(long id, Map<String, Object> fields);
}
