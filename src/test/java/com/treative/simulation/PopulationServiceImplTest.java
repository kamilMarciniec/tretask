package com.treative.simulation;

import com.treative.simulation.domain.Population;
import com.treative.simulation.domain.Simulation;
import com.treative.simulation.service.impl.PopulationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class PopulationServiceImplTest {

    @InjectMocks
    PopulationServiceImpl populationService;

    @Test
    public void secondPopulationTest() {
        Simulation simulation = new Simulation("test", 10000L, 10D, 3D, 0.1, 4,2,10);
        simulation.setDate(LocalDate.now());

        simulation.getPopulationList().add(populationService.initPopulation(simulation).get());

        Population next = populationService.generateNextPopulation(simulation).get();

        assertEquals(40, next.getPi());
        assertEquals(0, next.getPm());
        assertEquals(0, next.getPr());
        assertEquals(9960, next.getPv());
    }

    @Test
    public void thirdPopulationTest() {
        Simulation simulation = new Simulation("test", 10000L, 10D, 3D, 0.1, 4,2,10);
        simulation.setDate(LocalDate.now());

        simulation.getPopulationList().add(populationService.initPopulation(simulation).get());
        simulation.getPopulationList().add(new Population(40L, 9960L,0L,0L,LocalDate.now().plusDays(1)));

        Population next = populationService.generateNextPopulation(simulation).get();

        assertEquals(129, next.getPi());
        assertEquals(1, next.getPm());
        assertEquals(0, next.getPr());
        assertEquals(9870, next.getPv());
    }

    @Test
    public void eleventhPopulationTest() {
        Simulation simulation = new Simulation("test", 10000L, 10D, 3D, 0.1, 4,2,15);
        simulation.setDate(LocalDate.now());

        simulation.getPopulationList().add(populationService.initPopulation(simulation).get());
        for(int i=0; i<simulation.getTs(); i++) {
            simulation.getPopulationList().add(populationService.generateNextPopulation(simulation).get());
        }


        assertEquals(0, simulation.getPopulationList().get(11).getPi());
        assertEquals(1000, simulation.getPopulationList().get(11).getPm());
        assertEquals(9000, simulation.getPopulationList().get(11).getPr());
        assertEquals(0, simulation.getPopulationList().get(11).getPv());
    }

    @Test
    public void calculatePiTest() {
        Simulation simulation = new Simulation("test", 10000L, 10D, 3D, 0.1, 4,2,10);
        simulation.setDate(LocalDate.now());

        simulation.getPopulationList().add(populationService.initPopulation(simulation).get());
        simulation.getPopulationList().add(new Population(40L, 9960L,0L,0L,LocalDate.now().plusDays(1)));


        assertEquals(129, populationService.calculatePi(simulation, populationService.calculatePr(simulation, -2), populationService.calculatePm(simulation, 0)));
    }


    @Test
    public void calculatePmTest() {
        Simulation simulation = new Simulation("test", 10000L, 10D, 3D, 0.1, 4,2,10);
        simulation.setDate(LocalDate.now());

        simulation.getPopulationList().add(populationService.initPopulation(simulation).get());
        simulation.getPopulationList().add(new Population(40L, 9960L,0L,0L,LocalDate.now().plusDays(1)));
        simulation.getPopulationList().add(new Population(129L, 9870L,1L,0L,LocalDate.now().plusDays(2)));

        assertEquals(simulation.getPopulationList().size(), 3);
        assertEquals(0, populationService.calculatePm(simulation, -2));
        assertEquals(0, populationService.calculatePm(simulation, -1));
        assertEquals(1, populationService.calculatePm(simulation, 0));
    }

    @Test
    public void calculatePrTest() {
        Simulation simulation = new Simulation("test", 10000L, 10D, 3D, 0.1, 4,2,10);
        simulation.setDate(LocalDate.now());


        simulation.getPopulationList().add(populationService.initPopulation(simulation).get());
        simulation.getPopulationList().add(new Population(40L, 9960L,0L,0L,LocalDate.now().plusDays(1)));
        simulation.getPopulationList().add(new Population(129L, 9870L,1L,0L,LocalDate.now().plusDays(2)));
        simulation.getPopulationList().add(new Population(387L, 9600L,4L,9L,LocalDate.now().plusDays(3)));

        assertEquals(simulation.getPopulationList().size(), 4);
        assertEquals(0, populationService.calculatePr(simulation, -3));
        assertEquals(0, populationService.calculatePr(simulation, -2));
        assertEquals(0, populationService.calculatePr(simulation, -1));
        assertEquals(9, populationService.calculatePr(simulation, 0));
    }

    @Test
    public void initPopulationTest() {
        Simulation simulation = new Simulation("test", 10000L, 100D, 3D, 0.1, 4,2,10);

        populationService.initPopulation(simulation);

        assertThat(populationService.initPopulation(simulation)).isNotNull();
    }
}
