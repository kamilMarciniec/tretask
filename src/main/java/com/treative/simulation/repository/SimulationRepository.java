package com.treative.simulation.repository;

import com.treative.simulation.domain.Simulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SimulationRepository extends JpaRepository<Simulation, Long> {
    Optional<Simulation> findByN(String n);
}
