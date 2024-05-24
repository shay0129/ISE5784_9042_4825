package geometries;
import primitives.Point;
import primitives.Vector;

/**
 * Plane class represents a flat geometric surface defined by a point and a normal vector.
 *
 * @author Shay and Asaf
 */
public class Plane extends Geometry {

    private Point q; // Reference point on the plane
    private Vector normal; // Normal vector to the plane

    // Constructor with 3 points
    public Plane(Point point1, Point point2, Point point3) {
        // Calculate the normal vector using the formula for the normal vector of a triangle
        this.normal = calculateNormal(point1, point2, point3);

        // Choose one of the points as the reference point
        this.q = point1; // You can choose any of the three points here
    }

    // Constructor with a point and a normal vector
    public Plane(Point point, Vector normal) {
        normal = normal.normalize(); // Normalize the normal vector

        this.normal = normal;
        this.q = point;
    }

    // Private method to calculate the normal vector from three points
    private Vector calculateNormal(Point point1, Point point2, Point point3) {
        // Calculate vectors from the reference point to the other two points
        Vector v1 = point2.subtract(point1);
        Vector v2 = point3.subtract(point1);

        // Calculate the cross product of these vectors to get the normal vector
        return v1.crossProduct(v2);
    }

    // Getter for the reference point
    public Point getQ() {
        return q;
    }

    // Getter for the normal vector
    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point point) {
        return null; // The normal vector of a plane is constant for all points on the plane
    }

    // Other methods (optional)
    // - Check if a point lies on the plane
    // - Calculate the intersection of a ray with the plane

    @Override
    public String toString() {
        return String.format("Plane(q: %s, normal: %s)", q, normal);
    }
}