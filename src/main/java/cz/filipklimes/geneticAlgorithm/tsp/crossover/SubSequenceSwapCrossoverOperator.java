package cz.filipklimes.geneticAlgorithm.tsp.crossover;

import cz.filipklimes.geneticAlgorithm.tsp.Route;

import java.util.Random;

import static cz.filipklimes.geneticAlgorithm.tsp.crossover.CrossoverOperator.Offsprings.offsprings;

public class SubSequenceSwapCrossoverOperator implements CrossoverOperator {

    private final static Random RANDOM = new Random();

    @Override
    public Offsprings crossover(Route parent1, Route parent2) {
        int[] p1Order = parent1.getOrder();
        int[] p2Order = parent2.getOrder();
        int[] child1Order = new int[p1Order.length];
        int[] child2Order = new int[p1Order.length];

        int i1 = RANDOM.nextInt(p1Order.length);
        int i2 = RANDOM.nextInt(p1Order.length);

        int start = Math.min(i1, i2);
        int end = Math.max(i1, i2);

        System.arraycopy(p1Order, start, child1Order, start, end - start + 1);
        boolean[] contained = new boolean[p1Order.length];
        for (int i = start; i < end; i++) {
            contained[child1Order[i]] = true;
        }
        int parentPtr = 0;
        for (int i = 0; i < p1Order.length; i++) {
            if (i >= start && i < end) {
                continue;
            }

            do {
                child1Order[i] = p1Order[parentPtr++];
            } while (contained[child1Order[i]]);
            contained[child1Order[i]] = true;
        }

        System.arraycopy(p2Order, start, child2Order, start, end - start + 1);
        contained = new boolean[p2Order.length];
        for (int i = start; i < end; i++) {
            contained[child2Order[i]] = true;
        }
        parentPtr = 0;
        for (int i = 0; i < p2Order.length; i++) {
            if (i >= start && i < end) {
                continue;
            }

            do {
                child2Order[i] = p2Order[parentPtr++];
            } while (contained[child2Order[i]]);
            contained[child2Order[i]] = true;
        }

        return offsprings(new Route(child1Order), new Route(child2Order));
    }

}
