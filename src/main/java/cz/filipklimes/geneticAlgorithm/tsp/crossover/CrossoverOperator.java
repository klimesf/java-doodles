package cz.filipklimes.geneticAlgorithm.tsp.crossover;

import cz.filipklimes.geneticAlgorithm.tsp.Route;

public interface CrossoverOperator {

    Offsprings crossover(Route parent1, Route parent2);

    final class Offsprings {
        public Route child1;
        public Route child2;

        public Offsprings(Route child1, Route child2) {
            this.child1 = child1;
            this.child2 = child2;
        }

        public static Offsprings offsprings(Route child1, Route child2) {
            return new Offsprings(child1, child2);
        }
    }

}
