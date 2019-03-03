package cz.filipklimes.monteCarlo.pi;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cz.filipklimes.monteCarlo.pi.MonteCarloPiApproximator.Point;

public class VisualizationPanel extends JPanel {

    private final List<Point> points = Collections.synchronizedList(new ArrayList<>());
    private double pi;

    public void pi(double pi) {
        this.pi = pi;
    }

    public void appendPoint(Point point) {
        this.points.add(point);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString(String.format("%.3f", pi), 20, 20);

        g.setColor(Color.WHITE);
        g.fillRect(20, 30, 360, 360);

        synchronized (points) {
            for (Point p : points) {
                g.setColor(p.liesInCircle ? Color.GREEN : Color.RED);
                g.drawOval(200 + (int) Math.round(p.x * 180), 210 + (int) Math.round(p.y * 180), 2, 2);
            }
        }
    }

}
