package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * RadianGeometry class represents a geometric shape in 3-dimensional space.
 * It is an abstract class that is extended by specific geometric shapes.
 * The class is defined by a radius.
 *
 * @author Shay and Asaf
 */
public abstract class RadianGeometry {

    /**
     * The radius of the geometric shape.
     */
    protected final double radius;

    /**
     * Constructor that creates a new geometric shape given its radius.
     * @param radius The radius of the geometric shape.
     */
    public RadianGeometry(double radius) {
        this.radius = radius;
    }

    /**
     * Returns the radius of the geometric shape.
     * @return The radius of the geometric shape.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Calculates the normal vector to the geometric shape at a given point.
     * This method is abstract and must be implemented by subclasses.
     * @param point The point on the geometric shape at which to calculate the normal vector.
     * @return The normal vector to the geometric shape at the specified point.
     */
    public abstract Vector getNormal(Point point);
}