package org.datnh.quadtree;

import java.util.ArrayList;
import java.util.List;

/**
 * This class demonstrates how to find points within a specific area using a quadtree.
 * It creates a quadtree, inserts random points, and queries for points within a defined rectangle.
 */
public class FindPointsInArea {
    public static void main(String[] args) {
        Rectangle boundary = new Rectangle(0, 0, 100, 100); // center at (0,0), size 200x200
        Quadtree qt = new Quadtree(boundary);

        // Insert 10 random points
        for (int i = 0; i < 100; i++) {
            double x = Math.random() * 200 - 100;
            double y = Math.random() * 200 - 100;
            qt.insert(new Point(x, y));
        }

        // Query all points within a small area
        Rectangle range = new Rectangle(0, 0, 25, 25);
        List<Point> found = new ArrayList<>();
        qt.query(range, found);

        System.out.println("Points found in query range:");
        for (Point p : found) {
            System.out.printf("(%.2f, %.2f)\n", p.x, p.y);
        }
    }
}
