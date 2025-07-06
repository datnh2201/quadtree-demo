package org.datnh.quadtree;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a node in a quadtree tree structure.
 */
public class Quadtree {

    // maximum number of points allowed in a node,
    // if number of points in this node exceeds this limit it should be split into four smaller quadrants
    private final int CAPACITY = 4;

    // the area represented by this node
    private Rectangle boundary;

    // a flag to check whether this node has been divided into four quadrants
    private boolean divided = false;

    // list of points contained in this node,
    // when number of points exceeds CAPACITY, it will be moved to child nodes
    private List<Point> points;

    private Quadtree northwest;
    private Quadtree northeast;
    private Quadtree southwest;
    private Quadtree southeast;

    public Quadtree(Rectangle boundary) {
        this.boundary = boundary;
        this.points = new LinkedList<>();
    }

    /**
     *
     * Insert a given point into this quadtree node.
     *
     * @return true if this point was successfully inserted, false if the point is outside the area of this node
     */
    public boolean insert(Point point) {
        if (!this.boundary.contains(point)) {
            // ignore if the point is outside the area of this node
            return false;
        }
        if (points.size() < CAPACITY) {
            return this.points.add(point);
        }

        if (!divided) {
            // when number of points exceed the capacity,
            // if this node has not been divided yet, divide it into four quadrants
            this.subdivide();
        }

        // assign given point to one of the four children quadrants
        return (northeast.insert(point) || northwest.insert(point) ||
                southeast.insert(point) || southwest.insert(point));
    }

    /**
     * Divide this node into four quadrants.
     */
    private void subdivide() {
        double x = boundary.x;
        double y = boundary.y;
        double w = boundary.width / 2;
        double h = boundary.height / 2;

        northeast = new Quadtree(new Rectangle(x + w, y - h, w, h));
        northwest = new Quadtree(new Rectangle(x - w, y - h, w, h));
        southeast = new Quadtree(new Rectangle(x + w, y + h, w, h));
        southwest = new Quadtree(new Rectangle(x - w, y + h, w, h));

        divided = true;
    }

    public void query(Rectangle rectangle, List<Point> found){
        if(this.boundary.intersects(rectangle)){
            // if this node's area intersects with the given rectangle,
            // check all points in this node
            // with this check, we can narrow down the search area
            for (Point point : points) {
                if (rectangle.contains(point)) {
                    found.add(point);
                }
            }

            // if this node has been divided, check all four quadrants
            if (divided) {
                northeast.query(rectangle, found);
                northwest.query(rectangle, found);
                southeast.query(rectangle, found);
                southwest.query(rectangle, found);
            }
        }
    }

}
