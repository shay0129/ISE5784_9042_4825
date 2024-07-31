package geometries;

import primitives.*;

/**
 * Represents a cylinder in 3D space, which is a tube with a finite height.
 * The cylinder is defined by a central axis (represented by a {@link Ray}), a radius, and a height.
 * It extends the {@link Tube} class by adding the height attribute.
 *
 * @see Tube
 * @see Ray
 * @see Vector
 * @see Point
 *
 * @autor Shay and Asaf
 */
public class Cylinder extends Tube {

    private final double height;

    /**
     * Constructs a Cylinder object with the given radius, central axis, and height.
     *
     * @param radius the radius of the cylinder
     * @param axis the central axis of the cylinder, represented as a {@link Ray}
     * @param height the height of the cylinder
     */
    public Cylinder(double radius, Ray axis, double height) {
        super(radius, axis);
        if (height <= 0 )
            throw new IllegalArgumentException("Height must be greater than 0");
        this.height = height;
    }


    @Override
    public Vector getNormal(Point point) {
        // Placeholder implementation
        return null;
    }

    @Override
    public String toString() {
        return "Cylinder{" + super.toString() + "height=" + height + '}';
    }
}
