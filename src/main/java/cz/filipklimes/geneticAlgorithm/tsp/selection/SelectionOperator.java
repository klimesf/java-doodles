package cz.filipklimes.geneticAlgorithm.tsp.selection;

import cz.filipklimes.geneticAlgorithm.tsp.Route;

import java.util.List;

public interface SelectionOperator {

    Route select(List<Route> population);

}
