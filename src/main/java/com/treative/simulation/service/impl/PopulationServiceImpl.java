package com.treative.simulation.service.impl;

import com.treative.simulation.domain.Population;
import com.treative.simulation.domain.Simulation;
import com.treative.simulation.service.PopulationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class PopulationServiceImpl implements PopulationService {

    @Override
    public Optional<Population> generateNextPopulation(Simulation simulation) {
        LocalDate currentDate = simulation.getPopulationList().get(simulation.getPopulationList().size()-1).getDate().plusDays(1);

        long daysBetweenSimulationAndPopulation = getDaysBetweenSimAndPopulation(simulation, currentDate);
        int tm =(int) daysBetweenSimulationAndPopulation - simulation.getTm();
        int ti = (int) daysBetweenSimulationAndPopulation - simulation.getTi();

        long currentPm = calculatePm(simulation, tm);
        long currentPr = calculatePr(simulation, ti);
        long currentPi = calculatePi(simulation, currentPr, currentPm);
        long currentPv = calculatePv(simulation, currentPi, currentPm, currentPr);



        return Optional.of(new Population(currentPi, currentPv, currentPm, currentPr, currentDate));
    }

    private long getDaysBetweenSimAndPopulation(Simulation simulation, LocalDate currentDate) {
        return ChronoUnit.DAYS.between(simulation.getDate(), currentDate);
    }

    public int calculatePm(Simulation simulation, int tm) {
        double v;

        if (tm >= 0) {
            Population population = simulation.getPopulationList().get(tm);
            v = (population.getPi() + population.getPm() + population.getPr()) * simulation.getM();
        } else {
            v = 0;
        }

        return (int)Math.round(v);
    }

    public int calculatePr(Simulation simulation, int ti) {
        double v;
        double recoveryRate = 1-simulation.getM();

        if (ti >= 0) {
            Population population = simulation.getPopulationList().get(ti);
            v = (population.getPi() + population.getPr() + population.getPm()) * recoveryRate;
        } else {
            v=0;
        }


        return (int)Math.round(v);
    }

    public int calculatePi(Simulation simulation, long pr, long pm) {
        int listSize = simulation.getPopulationList().size();
        double v;
        double newCases;
        Population lastPopulation = simulation.getPopulationList().get(listSize - 1);

        if (listSize>1) {
            Population penultimatePopulation = simulation.getPopulationList().get(listSize - 2);
            newCases = (totalInfected(lastPopulation) - totalInfected(penultimatePopulation)) * simulation.getR();

            if (newCases > lastPopulation.getPv()) {
                newCases = lastPopulation.getPv();
            }

            v = newCases + (lastPopulation.getPi() +lastPopulation.getPm() + lastPopulation.getPr()) - pr - pm;

        } else {
            v = (lastPopulation.getPi() * simulation.getR()) + lastPopulation.getPi() - pr - pm;
        }


        return (int)Math.round(v);
    }

    private long totalInfected(Population population) {
        return population.getPi() + population.getPr() + population.getPm();
    }

    private long calculatePv(Simulation simulation, long currentPm, long currentPr, long currentPi) {
        return simulation.getP() - currentPi - currentPm - currentPr;
    }


    public Optional<Population> initPopulation(Simulation simulation) {
             return Optional.of(new Population(
                     Math.round(simulation.getI()),
                     Math.round(simulation.getP() - simulation.getI()),
                     0L,
                     0L,
                     simulation.getDate()
        ));
    }

}
