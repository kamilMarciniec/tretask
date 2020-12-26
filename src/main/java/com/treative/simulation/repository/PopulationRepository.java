package com.treative.simulation.repository;

import com.treative.simulation.domain.Population;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PopulationRepository extends JpaRepository <Population, Long> {
}
