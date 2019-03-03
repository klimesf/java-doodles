package cz.filipklimes.monteCarlo.pi;

import cz.filipklimes.monteCarlo.pi.MonteCarloPiApproximator.ApproximationStep;

import javax.swing.*;

public class Main {

    public static final int DEFAULT_NUMBER_OF_POINTS = 1_000_000;

    public static void main(String[] args) {
        final int numberOfPoints;
        if (args.length > 1) {
            numberOfPoints = Integer.parseInt(args[1]);
        } else {
            numberOfPoints = DEFAULT_NUMBER_OF_POINTS;
        }

        VisualizationPanel panel = new VisualizationPanel();

        JFrame frame = new JFrame("MonteCarlo Pi Approximation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(400, 430);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        MonteCarloPiApproximator approximator = new MonteCarloPiApproximator(numberOfPoints);

        for (MonteCarloPiApproximator it = approximator; it.hasNext(); ) {
            ApproximationStep result = it.next();
            panel.pi(result.pi);
            panel.appendPoint(result.point);

            frame.revalidate();
            frame.repaint();
        }
    }

}
