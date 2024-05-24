package geometries;

/**
 * Cylinder class represents a geometric cylinder defined by a base circle, an axis, and a height.
 * It extends the `Tube` class and adds specific methods related to cylinders.
 *
 * @author Shay and Asaf
 */
public class Cylinder extends Tube {

    // Instance variables
    private final Circle base;  // Base circle of the cylinder
    private final double height;   // Height of the cylinder

    // Constructor
    public Cylinder(Circle base, double height) {
        super(base.getAxis(), base.getRadius()); // Initialize Tube with base's axis and radius
        this.base = base;
        this.height = height;
    }

    // Getter methods
    public Circle getBase() {
        return base;
    }

    public double getHeight() {
        return height;
    }

    // Other potential methods
    public double getSurfaceArea() {
        // Calculate and return the surface area of the cylinder
        // (2 * PI * radius^2) + (2 * PI * radius * height)
        double radius = base.getRadius();
        return 2 * Math.PI * Math.pow(radius, 2) + 2 * Math.PI * radius * height;
    }

    public double getVolume() {
        // Calculate and return the volume of the cylinder
        // (PI * radius^2 * height)
        double radius = base.getRadius();
        return Math.PI * Math.pow(radius, 2) * height;
    }

    // You can add other methods based on your needs, like:
    // - Checking if a point is inside the cylinder
    // - Calculating the intersection of a ray with the cylinder

    @Override
    public String toString() {
        return String.format("Cylinder(base: %s, height: %.2f)", base, height);
    }
}
