package cz.filipklimes.monteCarlo.pi;

import java.util.*;

public class MonteCarloPiApproximator implements Iterator<MonteCarloPiApproximator.ApproximationStep> {

    private final Random random = new Random();
    private final List<Point> points = new ArrayList<>();
    private final int numberOfPoints;
    private long in;

    public MonteCarloPiApproximator(int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    @Override
    public boolean hasNext() {
        return points.size() < numberOfPoints;
    }

    @Override
    public ApproximationStep next() {
        Point p = generatePoint(random);
        points.add(p);
        if (p.liesInCircle) {
            in++;
        }
        return new ApproximationStep(((double) in / points.size()) * 4, p);
    }

    private Point generatePoint(Random random) {
        double x = random.nextDouble() * 2 - 1;
        double y = random.nextDouble() * 2 - 1;
        return new Point(x, y, liesInCircle(x, y));
    }

    private boolean liesInCircle(double x, double y) {
        return Math.pow(x, 2) + Math.pow(y, 2) <= 1;
    }

    public static final class ApproximationStep {
        public double pi;
        public Point point;

        public ApproximationStep(double pi, Point point) {
            this.pi = pi;
            this.point = point;
        }
    }

    public static final class Point {
        public double x;
        public double y;
        public boolean liesInCircle;

        public Point(double x, double y, boolean liesInCircle) {
            this.x = x;
            this.y = y;
            this.liesInCircle = liesInCircle;
        }
    }

}
