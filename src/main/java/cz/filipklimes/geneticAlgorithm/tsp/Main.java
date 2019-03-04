package cz.filipklimes.geneticAlgorithm.tsp;

import cz.filipklimes.geneticAlgorithm.tsp.crossover.SubSequenceSwapCrossoverOperator;
import cz.filipklimes.geneticAlgorithm.tsp.mutation.SwapMutationOperator;
import cz.filipklimes.geneticAlgorithm.tsp.selection.TournamentSelectionOperator;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        CityReader cityReader = new CityReader();
        List<City> cities = cityReader.readCities("data/czech-cities/district-cities.csv");

        TspGeneticAlgorithm geneticAlgorithm = new TspGeneticAlgorithm(
            cities,
            new SubSequenceSwapCrossoverOperator(),
            new SwapMutationOperator(),
            new TournamentSelectionOperator()
        );
        geneticAlgorithm.run();
    }

}
