package cz.filipklimes.geneticAlgorithm.tsp.mutation;

import cz.filipklimes.geneticAlgorithm.tsp.Route;

import java.util.Random;

public class SwapMutationOperator implements MutationOperator {

    private final static Random RANDOM = new Random();

    @Override
    public Route mutate(Route route) {
        int[] order = route.getOrder();

        int toSwap = RANDOM.nextInt(order.length);
        int toSwap2 = RANDOM.nextInt(order.length);

        int temp = order[toSwap];
        order[toSwap] = order[toSwap2];
        order[toSwap2] = temp;

        return new Route(order);
    }

}
