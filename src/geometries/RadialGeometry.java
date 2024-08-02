package geometries;

/**
 * RadialGeometry class represents a geometric shape in 3-dimensional space.
 * It is an abstract class that is extended by specific geometric shapes.
 * The class is defined by a radius.
 *
 * @author Shay and Asaf
 */
public abstract class RadialGeometry extends Geometry {

    /**
     * Radius of the geometric body.
     */
    protected final double radius;

    /**
     * Constructs a RadialGeometry object with the given radius.
     *
     * @param radius the radius of the geometric body
     */
    public RadialGeometry(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive");
        }
        this.radius = radius;
    }
}
