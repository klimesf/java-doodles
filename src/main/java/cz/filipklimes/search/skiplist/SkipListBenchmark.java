package cz.filipklimes.search.skiplist;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class SkipListBenchmark {

    private static final int NUMBER_OF_ELEMENTS = 2 << 15;
    private static final int MAX_LEVEL = 8;
    private static final float PROBABILITY = 0.25f;
    private static final int NUMBER_OF_RETRIES = 10;

    public static void main(String[] args) {
        benchmarkRandomInsertion();
        benchmarkSearch();
    }

    private static void benchmarkSearch() {
        System.out.println("-- Search benchmark");

        final SkipList skipList = new NaiveSkipList(MAX_LEVEL, PROBABILITY);
        final List<Integer> arrayList = new ArrayList<>(NUMBER_OF_ELEMENTS);
        final SortedSet<Integer> sortedSet = new TreeSet<>();


        for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            skipList.insert(i);
            arrayList.add(i);
            sortedSet.add(i);
        }

        final long[] skipListResults = new long[NUMBER_OF_RETRIES];
        final long[] arrayListResults = new long[NUMBER_OF_RETRIES];
        final long[] sortedSetResults = new long[NUMBER_OF_RETRIES];

        for (int retry = 0; retry < NUMBER_OF_RETRIES; retry++) {
            skipListResults[retry] = benchmark(skipList::find, 10);
            arrayListResults[retry] = benchmark(arrayList::contains, 10);
            sortedSetResults[retry] = benchmark(sortedSet::contains, 10);
        }

        showResults(skipListResults, arrayListResults, sortedSetResults);
    }

    private static void benchmarkRandomInsertion() {
        System.out.println("-- Randomized insert benchmark");

        final SkipList skipList = new NaiveSkipList(MAX_LEVEL, PROBABILITY);
        final List<Integer> arrayList = new ArrayList<>(NUMBER_OF_ELEMENTS);
        final SortedSet<Integer> sortedSet = new TreeSet<>();

        Random rand = new Random();
        int[] elements = new int[NUMBER_OF_ELEMENTS];
        for (int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            elements[i] = rand.nextInt(Integer.MAX_VALUE);
        }

        final long[] skipListResults = new long[NUMBER_OF_RETRIES];
        final long[] arrayListResults = new long[NUMBER_OF_RETRIES];
        final long[] sortedSetResults = new long[NUMBER_OF_RETRIES];

        // warmup
        benchmark(i -> skipList.insert(elements[i]));
        benchmark(i -> arrayList.add(elements[i]));
        benchmark(i -> sortedSet.add(elements[i]));
        for (int retry = 0; retry < NUMBER_OF_RETRIES; retry++) {
            skipListResults[retry] = benchmark(i -> skipList.insert(elements[i]));
            arrayListResults[retry] = benchmark(i -> arrayList.add(elements[i]));
            sortedSetResults[retry] = benchmark(i -> sortedSet.add(elements[i]));
        }

        showResults(skipListResults, arrayListResults, sortedSetResults);
    }

    private static long benchmark(Consumer<Integer> consumer) {
        return benchmark(consumer, 1);
    }

    private static long benchmark(Consumer<Integer> consumer, int step) {
        long start = System.nanoTime();
        for (int i = 0; i < NUMBER_OF_ELEMENTS; i += step) {
            consumer.accept(i);
        }
        return System.nanoTime() - start;
    }

    private static void showResults(long[] skipListResults, long[] arrayListResults, long[] sortedSetResults) {
        long skipListAvg = Arrays.stream(skipListResults).sum() / NUMBER_OF_RETRIES;
        long arrayListAvg = Arrays.stream(arrayListResults).sum() / NUMBER_OF_RETRIES;
        long sortedSetAvg = Arrays.stream(sortedSetResults).sum() / NUMBER_OF_RETRIES;

        double skipListStdDeviation = stdDeviation(skipListResults, skipListAvg);
        double arrayListStdDeviation = stdDeviation(arrayListResults, arrayListAvg);
        double sortedSetStdDeviation = stdDeviation(sortedSetResults, sortedSetAvg);

        printResults("Skip list", skipListAvg, (long) skipListStdDeviation);
        printResults("Array list", arrayListAvg, (long) arrayListStdDeviation);
        printResults("Sorted set", sortedSetAvg, (long) sortedSetStdDeviation);
    }

    private static void printResults(String name, long skipListAvg, long skipListStdDeviation) {
        System.out.printf(
            "%-12s%5d ms \u00B1 %2dms%n",
            name,
            TimeUnit.NANOSECONDS.toMillis(skipListAvg),
            TimeUnit.NANOSECONDS.toMillis(skipListStdDeviation)
        );
    }

    private static double stdDeviation(long[] results, long avg) {
        return Math.sqrt(Arrays.stream(results)
            .map(v -> v - avg)
            .mapToDouble(v -> Math.pow(v, 2))
            .sum()
            / NUMBER_OF_RETRIES);
    }

}
