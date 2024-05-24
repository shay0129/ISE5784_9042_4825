package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Plane class represents a geometric plane in 3-dimensional space.
 * It extends the `Geometry` class and adds specific methods related to planes.
 * The plane is defined by a point and a normal vector.
 *
 * @author Shay and Asaf
 */
public class Plane extends Geometry {

    // Instance variables

    /**
     * A point on the plane.
     */
    private final Point point;

    /**
     * The normal vector to the plane.
     */
    private final Vector normal;

    // Constructor

    /**
     * Constructor that creates a new plane given a point on the plane and a normal vector.
     * @param point A point on the plane.
     * @param normal The normal vector to the plane.
     */
    public Plane(Point point, Vector normal) {
        this.point = point;
        this.normal = normal.normalize();
    }

    // Getter methods

    /**
     * Returns a point on the plane.
     * @return A point on the plane.
     */
    public Point getPoint() {
        return this.point;
    }

    /**
     * Returns the normal vector to the plane.
     * @return The normal vector to the plane.
     */
    public Vector getNormal() {
        return this.normal;
    }

    /**
     * Calculates the normal vector to the plane at a given point.
     * @param point The point on the plane at which to calculate the normal vector.
     * @return The normal vector to the plane at the specified point.
     */
    @Override
    public Vector getNormal(Point point) {
        return this.normal;
    }

    /**
     * Returns a string representation of the plane.
     * @return A string that describes the plane.
     */
    @Override
    public String toString() {
        return String.format("Plane(point: %s, normal: %s)", point, normal);
    }
}