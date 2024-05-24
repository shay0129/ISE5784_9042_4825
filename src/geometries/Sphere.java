package geometries;
import primitives.Point;
import primitives.Vector;
/**
 * Sphere class represents a geometric sphere defined by a center point and a radius.
 * It extends the `RadianGeometry` class and adds specific methods related to spheres.
 *
 * @author Shay and Asaf
 */
public class Sphere extends RadianGeometry {

    // Instance variables
    private final Point center;  // Center point of the sphere
    private final double radius;    // Radius of the sphere

    // Constructor
    public Sphere(Point center, double radius) {
        super(radius); // Initialize RadianGeometry with radius
        this.center = center;
        this.radius = radius;
    }

    // Getter methods
    public Point getCenter() {
        return this.center;
    }

    public double getRadius() {
        return radius;
    }

    // Other potential methods
    public double getSurfaceArea() {
        // Calculate and return the surface area of the sphere (4 * PI * radius^2)
        return 4 * Math.PI * Math.pow(radius, 2);
    }

    public double getVolume() {
        // Calculate and return the volume of the sphere (4/3 * PI * radius^3)
        return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }

    public boolean containsPoint(Point point) {
        // Check if the distance between the center and the point is less than or equal to the radius
        double distance = center.distanceTo(point);
        return distance <= radius;
    }

    // You can add other methods based on your needs, like:
    // - Finding intersections with rays
    // - Calculating the centroid (center of mass)

    @Override
    public Vector getNormal(Point point) {
        return null;
    }

    @Override
    public String toString() {
        return String.format("Sphere(center: %s, radius: %.2f)", center, radius);
    }
}
