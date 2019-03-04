package cz.filipklimes.geneticAlgorithm.tsp;

import cz.filipklimes.geneticAlgorithm.tsp.crossover.CrossoverOperator;
import cz.filipklimes.geneticAlgorithm.tsp.crossover.CrossoverOperator.Offsprings;
import cz.filipklimes.geneticAlgorithm.tsp.mutation.MutationOperator;
import cz.filipklimes.geneticAlgorithm.tsp.selection.SelectionOperator;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static cz.filipklimes.geneticAlgorithm.tsp.crossover.CrossoverOperator.Offsprings.offsprings;

public class TspGeneticAlgorithm {

    private static final double CROSSOVER_PROBABILITY = .50d;
    private static final int ELITE_SIZE = 4;
    private static final int MATING_POOL_SIZE = 96;
    private static final int GENERATION_COUNT = 10_000;
    private static final int POPULATION_SIZE = 200;
    private static final double MUTATION_PROBABILITY = .75d;

    private final List<City> cities;
    private final double[][] distances;
    private final CrossoverOperator crossoverOperator;
    private final MutationOperator mutationOperator;
    private final SelectionOperator selectionOperator;
    private final Comparator<Route> comparator;

    public TspGeneticAlgorithm(
        List<City> cities,
        CrossoverOperator crossoverOperator,
        MutationOperator mutationOperator,
        SelectionOperator selectionOperator
    ) {
        this.cities = new ArrayList<>(cities);
        this.distances = createDistanceMatrix(cities);
        this.crossoverOperator = crossoverOperator;
        this.mutationOperator = mutationOperator;
        this.selectionOperator = selectionOperator;
        this.comparator = Comparator.comparingDouble(r -> r.calculateFitness(distances));
    }

    public void run() {
        List<Route> orderedPopulation = createInitialPopulation();
        System.out.printf("[init]: %5.3f km%n", orderedPopulation.get(0).calculateFitness(distances));

        for (int i = 0; i < GENERATION_COUNT; i++) {
            orderedPopulation = createNextGeneration(orderedPopulation);
            System.out.printf("[%4d]: %.3f km%n", i, orderedPopulation.get(0).calculateFitness(distances));
        }

        System.out.println("final");
    }

    private List<Route> createNextGeneration(List<Route> orderedPopulation) {
        Route[] matingPool = selectMatingPool(orderedPopulation);
        List<Route> nextGeneration = breedNewPopulation(matingPool);
        mutatePopulation(nextGeneration);

        nextGeneration.sort(comparator);
        return nextGeneration;
    }

    private double[][] createDistanceMatrix(List<City> cities) {
        double[][] result = new double[cities.size()][cities.size()];
        for (int i = 0; i < cities.size(); i++) {
            for (int j = 0; j < i; j++) {
                double distance = City.distance(cities.get(i), cities.get(j));
                result[i][j] = distance;
                result[j][i] = distance;
            }
        }
        return result;
    }

    private List<Route> createInitialPopulation() {
        final List<Integer> order = IntStream.range(0, cities.size())
            .boxed()
            .collect(Collectors.toList());

        return IntStream.range(0, POPULATION_SIZE)
            .mapToObj(i -> {
                Collections.shuffle(order);
                return new Route(order.stream().mapToInt(num -> num).toArray());
            })
            .sorted(Comparator.comparingDouble(r -> r.calculateFitness(distances)))
            .collect(Collectors.toList());
    }

    private Route[] selectMatingPool(List<Route> orderedPopulation) {
        Route[] matingPool = new Route[MATING_POOL_SIZE];
        for (int i = 0; i < ELITE_SIZE; i++) {
            matingPool[i] = orderedPopulation.get(i);
        }
        for (int i = ELITE_SIZE; i < MATING_POOL_SIZE; i++) {
            matingPool[i] = selectionOperator.select(orderedPopulation);
        }
        return matingPool;
    }

    private List<Route> breedNewPopulation(Route[] matingPool) {
        Random random = new Random();
        List<Route> newGeneration = new ArrayList<>(POPULATION_SIZE);
        for (int i = 0; i < ELITE_SIZE; i++) {
            newGeneration.add(matingPool[i]);
        }
        for (int i = 0; i < (POPULATION_SIZE - ELITE_SIZE) / 2; i++) {
            Route parent1 = matingPool[random.nextInt(MATING_POOL_SIZE)];
            Route parent2 = matingPool[random.nextInt(MATING_POOL_SIZE)];

            Offsprings offsprings;
            if (Math.random() > CROSSOVER_PROBABILITY) {
                offsprings = offsprings(parent1, parent2);
            } else {
                offsprings = crossoverOperator.crossover(parent1, parent2);
            }

            newGeneration.add(offsprings.child1);
            newGeneration.add(offsprings.child2);
        }

        return newGeneration;
    }

    private void mutatePopulation(List<Route> population) {
        for (int i = 0; i < POPULATION_SIZE; i++) {
            if (Math.random() > MUTATION_PROBABILITY) {
                continue;
            }

            Route r = population.get(i);
            population.set(i, mutationOperator.mutate(r));
        }
    }

}
