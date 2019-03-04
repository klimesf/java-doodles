package cz.filipklimes.geneticAlgorithm.tsp;

import java.util.Arrays;

public class Route {

    private final int[] order;

    public Route(int[] order) {
        this.order = Arrays.copyOf(order, order.length);
    }

    public double calculateFitness(double[][] distances) {
        double distance = 0.d;
        int previous = order[0];
        for (int i = 1; i < order.length; i++) {
            int next = order[i];
            distance += distances[previous][next];
            previous = next;
        }
        distance += distances[previous][order[0]];
        return distance;
    }

    public int[] getOrder() {
        return Arrays.copyOf(order, order.length);
    }

}
