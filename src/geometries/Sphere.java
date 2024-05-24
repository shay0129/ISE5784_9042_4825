package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Sphere class represents a geometric sphere defined by a center point and a radius.
 * It extends the `RadianGeometry` class and adds specific methods related to spheres.
 * The sphere is defined by a central point and a radius.
 *
 * @author Shay and Asaf
 */
public class Sphere extends RadianGeometry {

    // Instance variables

    /**
     * The center point of the sphere.
     */
    private final Point center;

    /**
     * The radius of the sphere.
     */
    private final double radius;

    // Constructor

    /**
     * Constructor that creates a new sphere given its center point and radius.
     * @param center The center point of the sphere.
     * @param radius The radius of the sphere.
     */
    public Sphere(Point center, double radius) {
        super(radius); // Initialize RadianGeometry with radius
        this.center = center;
        this.radius = radius;
    }

    // Getter methods

    /**
     * Returns the center point of the sphere.
     * @return The center point of the sphere.
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Returns the radius of the sphere.
     * @return The radius of the sphere.
     */
    public double getRadius() {
        return radius;
    }

    // Other potential methods

    /**
     * Calculates and returns the surface area of the sphere.
     * @return The surface area of the sphere.
     */
    public double getSurfaceArea() {
        return 4 * Math.PI * Math.pow(radius, 2);
    }

    /**
     * Calculates and returns the volume of the sphere.
     * @return The volume of the sphere.
     */
    public double getVolume() {
        return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }

    /**
     * Checks if a given point is inside the sphere.
     * @param point The point to check.
     * @return true if the point is inside the sphere, false otherwise.
     */
    public boolean containsPoint(Point point) {
        double distance = center.distance(point);
        return distance <= radius;
    }

    /**
     * Calculates the normal vector to the sphere at a given point.
     * @param point The point on the sphere at which to calculate the normal vector.
     * @return The normal vector to the sphere at the specified point.
     */
    @Override
    public Vector getNormal(Point point) {
        return null;
    }

    /**
     * Returns a string representation of the sphere.
     * @return A string that describes the sphere.
     */
    @Override
    public String toString() {
        return String.format("Sphere(center: %s, radius: %.2f)", center, radius);
    }
}