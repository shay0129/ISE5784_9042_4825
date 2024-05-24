package geometries;
import primitives.Point;
import primitives.Vector;

/**
 * Tube class represents a geometric tube defined by an axis and a radius.
 * It extends the `RadianGeometry` class and adds specific methods related to tubes.
 *
 * @author Shay and Asaf
 */
public class Tube extends RadianGeometry {

    // Instance variables
    protected final Vector axis;    // Axis of the tube
    protected final double radius;  // Radius of the tube

    // Constructor
    public Tube(Vector axis, double radius) {
        super(radius); // Initialize RadianGeometry with radius
        this.axis = axis;
        this.radius = radius;
    }

    // Getter methods
    public Vector getAxis() {
        return axis;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return String.format("Tube(axis: %s, radius: %.2f)", axis, radius);
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}