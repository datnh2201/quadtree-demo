package org.datnh.quadtree;

/**
 * This class represents an area in 2D space defined by a center point (x, y) and dimensions (2*width, 2*height).
 */
public class Rectangle {
    public double x, y;  // center x, y
    public double width, height;  // half-width and half-height

    public Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Check if this rectangle contains a given point.
     *
     * @return true if this rectangle contains the point, false otherwise
     */
    public boolean contains(Point p) {
        return (p.x >= x - width &&
                p.x <= x + width &&
                p.y >= y - height &&
                p.y <= y + height);
    }

    /**
     * Check whether this rectangle intersects with given rectangle.
     *
     * @return true if they intersect, false otherwise
     */
    public boolean intersects(Rectangle range) {
        return !(range.x - range.width > this.x + this.width ||
                range.x + range.width < this.x - this.width ||
                range.y - range.height > this.y + this.height ||
                range.y + range.height < this.y - this.height);
    }
}
