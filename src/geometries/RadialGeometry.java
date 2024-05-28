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
public abstract class RadialGeometry {

    /**
     * The radius of the geometric shape.
     */
    protected final double radius;

    /**
     * Constructor that creates a new geometric shape given its radius.
     * @param radius The radius of the geometric shape.
     */
    public RadialGeometry(double radius) {
        this.radius = radius;
    }

    /**
     * Returns the radius of the geometric shape.
     * @return The radius of the geometric shape.
     */
    public double getRadius() {
        return radius;
    }


}