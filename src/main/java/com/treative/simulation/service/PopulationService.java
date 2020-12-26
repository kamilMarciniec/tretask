package com.treative.simulation.service;

import com.treative.simulation.domain.Population;
import com.treative.simulation.domain.Simulation;

import java.util.Optional;

public interface PopulationService {
    Optional<Population> generateNextPopulation(Simulation simulation);
    Optional<Population> initPopulation(Simulation simulation);
}
