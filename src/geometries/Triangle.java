package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
/**
 * Triangle class represents a geometric triangle defined by three points.
 * It inherits from the `Polygon` class and doesn't have any additional fields.
 *
 * @author Shay and Asaf
 */
public class Triangle extends Polygon {

    // Constructor with three points
    public Triangle(Point point1, Point point2, Point point3) {
        super(point1, point2, point3); // Initialize polygon with the three points
    }

    public boolean findIntersections(Ray ray) {
        // Check for intersection using the intersection method for a plane
        // (since a triangle can be represented by a plane)
        Plane plane = new Plane(getQ(), getNormal());
        return plane.findIntersections(ray);
    }

    @Override
    public String toString() {
        return String.format("Triangle(vertices: %s)", vertices);
    }
}
