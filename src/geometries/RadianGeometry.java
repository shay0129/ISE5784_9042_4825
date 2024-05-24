package geometries;
import primitives.Point;
import primitives.Vector;

/**
 * RadianGeometry class is an abstract class that represents a geometry object with a radius.
 * It extends the `Geometry` class and provides a constructor for initializing the radius.
 *
 * @author Shay and Asaf
 */
public abstract class RadianGeometry extends Geometry {

    protected final double radius;

    // Constructor
    public RadianGeometry(double radius) {
        this.radius = radius;
    }

    // Getter for radius
    public double getRadius() {
        return radius;
    }
}
