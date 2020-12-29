package com.treative.simulation.service.impl;

import com.treative.simulation.domain.Population;
import com.treative.simulation.domain.Simulation;
import com.treative.simulation.repository.SimulationRepository;
import com.treative.simulation.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SimulationServiceImpl implements SimulationService {
    PopulationServiceImpl populationService;
    SimulationRepository simulationRepository;

    @Autowired
    public SimulationServiceImpl(PopulationServiceImpl populationService, SimulationRepository simulationRepository) {
        this.populationService = populationService;
        this.simulationRepository = simulationRepository;
    }

    @Override
    public ResponseEntity<Simulation> createSimulation(Simulation simulationData) {
        Simulation simulation  = initSimulation(simulationData);

        for (int i=0; i<simulation.getTs(); i++) {
            simulation.getPopulationList()
                    .add(populationService.generateNextPopulation(simulation)
                    .orElseThrow(()-> new RuntimeException("An error occurred while generating a population.")));
        }

        simulationRepository.save(simulation);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(simulation);
    }

    public Simulation initSimulation(Simulation simulation) {
        LocalDate date = LocalDate.now();
        simulation.setDate(date);

        Population firstPopulation = populationService.initPopulation(simulation)
                .orElseThrow(() -> new RuntimeException("An error occured while initializing first population."));

        simulation.getPopulationList().add(firstPopulation);
        return simulation;
    }

    @Override
    public ResponseEntity<Simulation> getSimulationById(long id) {
        Simulation simulation = simulationRepository.findById(id).get();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(simulation);
    }

    @Override
    public ResponseEntity<Simulation> getSimulationByName(String name) {
        Simulation simulation = simulationRepository.findByN(name).get();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(simulation);
    }

    @Override
    public ResponseEntity<String> deleteSimulationById(long id) {
        simulationRepository.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Simulation deleted successfully!");
    }

    @Override
    public ResponseEntity<Simulation> updateSimulation(long id, Simulation simulation) {
        Simulation savedSimulation = null;

        try {
            savedSimulation = simulationRepository.findById(id).orElseThrow(() -> new RuntimeException("Simulation not found"));
            simulation.setId(id);
            simulationRepository.save(simulation);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(savedSimulation);
        }

        return ResponseEntity.status(HttpStatus.OK).body(savedSimulation);
    }
}
