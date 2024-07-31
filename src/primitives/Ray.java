package primitives;

import java.util.List;

import static java.lang.Double.MAX_VALUE;

/**
 * The Ray class represents a directed line segment in Euclidean space.
 * It is defined by a starting point (head) and a direction vector.
 *
 * @author Shay and Asaf
 */
public class Ray {
    /** The starting point of the ray */
    private final Point head;
    /** The direction vector of the ray */
    private final Vector direction;

    /**
     * Constructs a Ray with the specified starting point and direction vector.
     *
     * @param point   the starting point (head) of the ray
     * @param vector  the direction vector of the ray; it is normalized internally
     */
    public Ray(Point point, Vector vector) {
        head = point;
        direction = vector.normalize(); // Ensure vector is normalized
    }

    /**
     * Returns the starting point (head) of the ray.
     *
     * @return the starting point of the ray
     */
    public Point getHead() {
        return head;
    }

    /***
     *
     * @param obj - an Object
     * @return true if the object equals this ray, else return false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Ray other)
                && this.head.equals(other.head)
                && this.direction.equals(other.direction);
    }

    /**
     * Returns the direction vector of the ray.
     *
     * @return the direction vector of the ray
     */
    public Vector getDirection() {
        return direction;
    }

    /**
     * Computes a point on the ray at a specified distance from the ray's origin.
     *
     * @param t the distance from the ray's origin to the computed point
     * @return the computed point on the ray at the specified distance from its origin
     *         If {@code t} is zero, the method returns the ray's starting point.
     */
    public Point getPoint(double t) {
        if (Util.isZero(t)) {
            return head;
        } else {
            return head.add(direction.scale(t));
        }
    }

    @Override
    public String toString() {
        return "Ray: " + head + " -> " + direction;
    }

    /**
     * Finds the closest point
     * @param points //collection of points
     * @return closest point to ray's head
     */
    public Point findClosestPoint(List<Point> points ) {
        double closestDistance = MAX_VALUE;
        if (points.isEmpty())
            return null;
        //If there are values in the points list
        Point closestPoint = points.getFirst(); //set the first point
        for (Point p : points){
            if (p.distance(this.head) < closestDistance) { //closer than previous points
                closestDistance = p.distance((this.head));
                closestPoint = p;
            }
        }
        return closestPoint;

    }
}

