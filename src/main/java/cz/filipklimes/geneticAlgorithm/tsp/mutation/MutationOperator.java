package cz.filipklimes.geneticAlgorithm.tsp.mutation;

import cz.filipklimes.geneticAlgorithm.tsp.Route;

public interface MutationOperator {

    Route mutate(Route route);

}
