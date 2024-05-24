package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Cylinder class represents a geometric cylinder in 3-dimensional space.
 * It extends the `RadianGeometry` class and adds specific methods related to cylinders.
 * The cylinder is defined by a central axis (represented by a point and a direction vector) and a radius.
 *
 * @author Shay and Asaf
 */
public class Cylinder extends RadianGeometry {

    // Instance variables

    /**
     * The base point of the cylinder's central axis.
     */
    private final Point base;

    /**
     * The direction vector of the cylinder's central axis.
     */
    private final Vector direction;

    // Constructor

    /**
     * Constructor that creates a new cylinder given its base point, direction vector and radius.
     * @param base The base point of the cylinder's central axis.
     * @param direction The direction vector of the cylinder's central axis.
     * @param radius The radius of the cylinder.
     */
    public Cylinder(Point base, Vector direction, double radius) {
        super(radius); // Initialize RadianGeometry with radius
        this.base = base;
        this.direction = direction.normalize();
    }

    // Getter methods

    /**
     * Returns the base point of the cylinder's central axis.
     * @return The base point of the cylinder's central axis.
     */
    public Point getBase() {
        return this.base;
    }

    /**
     * Returns the direction vector of the cylinder's central axis.
     * @return The direction vector of the cylinder's central axis.
     */
    public Vector getDirection() {
        return this.direction;
    }

    /**
     * Calculates the normal vector to the cylinder at a given point.
     * @param point The point on the cylinder at which to calculate the normal vector.
     * @return The normal vector to the cylinder at the specified point.
     */
    @Override
    public Vector getNormal(Point point) {
        // Implementation of getNormal for Cylinder (returns null for now)
        return null;
    }
}