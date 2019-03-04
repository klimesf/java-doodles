package cz.filipklimes.geneticAlgorithm.tsp.selection;

import cz.filipklimes.geneticAlgorithm.tsp.Route;

import java.util.List;
import java.util.Random;

public class TournamentSelectionOperator implements SelectionOperator {

    private final static Random RANDOM = new Random();

    @Override
    public Route select(List<Route> orderedPopulation) {
        int winnerIndex = RANDOM.nextInt(orderedPopulation.size());
        for (int i = 0; i < 3; i++) {
            int candidate = RANDOM.nextInt(orderedPopulation.size());
            if (candidate < winnerIndex) {
                winnerIndex = candidate;
            }
        }

        return orderedPopulation.get(winnerIndex);
    }

}
