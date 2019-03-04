package cz.filipklimes.geneticAlgorithm.tsp.crossover;

import cz.filipklimes.geneticAlgorithm.tsp.Route;

import static cz.filipklimes.geneticAlgorithm.tsp.crossover.CrossoverOperator.Offsprings.offsprings;

public class PartialMappedCrossoverOperator implements CrossoverOperator {
    @Override
    public Offsprings crossover(Route parent1, Route parent2) {
        return offsprings(parent1, parent2);
    }
}
