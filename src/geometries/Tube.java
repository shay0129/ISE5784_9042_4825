package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Tube class represents a geometric tube defined by an axis and a radius.
 * It extends the `RadianGeometry` class and adds specific methods related to tubes.
 * The tube is defined by a central axis and a radius.
 *
 * @author Shay and Asaf
 */
public class Tube extends RadianGeometry {
    // Instance variables

    /**
     * The axis of the tube.
     */
    protected final Vector axis;

    /**
     * The radius of the tube.
     */
    protected final double radius;

    // Constructor

    /**
     * Constructor that creates a new tube given its axis and radius.
     * @param axis The axis of the tube.
     * @param radius The radius of the tube.
     */
    public Tube(Vector axis, double radius) {
        super(radius); // Initialize RadianGeometry with radius
        this.axis = axis;
        this.radius = radius;
    }

    // Getter methods

    /**
     * Returns the axis of the tube.
     * @return The axis of the tube.
     */
    public Vector getAxis() {
        return axis;
    }

    /**
     * Returns the radius of the tube.
     * @return The radius of the tube.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Returns a string representation of the tube.
     * @return A string that describes the tube.
     */
    @Override
    public String toString() {
        return String.format("Tube(axis: %s, radius: %.2f)", axis, radius);
    }

    /**
     * Calculates the normal vector to the tube at a given point.
     * @param point The point on the tube at which to calculate the normal vector.
     * @return The normal vector to the tube at the specified point.
     */
    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}