package geometries;

import primitives.Ray;
import primitives.Vector;
import primitives.Util;
import java.util.List;
import primitives.Point;

/**
 * Represents a geometric triangle in 3-dimensional space.
 * The triangle is defined by three vertices and extends the {@link Polygon} class.
 * It provides methods specific to triangles.
 *
 * @author Shay and Asaf
 */
public class Triangle extends Polygon {

    /**
     * Constructs a Triangle object with the specified vertices.
     *
     * @param point1 the first vertex of the triangle
     * @param point2 the second vertex of the triangle
     * @param point3 the third vertex of the triangle
     */
    public Triangle(Point point1, Point point2, Point point3) {
        super(point1, point2, point3); // Calls the constructor of the superclass Polygon
    }

    /**
     * Finds the intersection points between the triangle and the given ray.
     *
     * @param ray the ray to intersect with the triangle
     * @return a list of intersection points between the ray and the triangle.
     *         If no intersections are found, an empty list is returned.
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        // Check if the ray intersects the plane of the triangle
        List<Point> planeIntersections = plane.findIntersections(ray);
        if (planeIntersections == null) {
            return List.of(); // Return an empty list if no intersection with the plane
        }

        // Get the intersection point with the plane (assuming the ray intersects the plane)
        Point intersection = planeIntersections.get(0);

        // Define the vectors from the intersection point to the vertices of the triangle
        Vector v1 = vertices.get(0).subtract(intersection);
        Vector v2 = vertices.get(1).subtract(intersection);
        Vector v3 = vertices.get(2).subtract(intersection);

        // Calculate the normal vectors for each of the triangle's edges
        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();

        // Get the direction vector of the ray
        Vector rayDirection = ray.getDirection();

        // Check if the intersection point is inside the triangle
        boolean isInside = (Util.alignZero(rayDirection.dotProduct(n1)) >= 0 &&
                Util.alignZero(rayDirection.dotProduct(n2)) >= 0 &&
                Util.alignZero(rayDirection.dotProduct(n3)) >= 0) ||
                (Util.alignZero(rayDirection.dotProduct(n1)) <= 0 &&
                        Util.alignZero(rayDirection.dotProduct(n2)) <= 0 &&
                        Util.alignZero(rayDirection.dotProduct(n3)) <= 0);

        if (isInside) {
            return List.of(intersection); // Return the intersection point if inside
        }
        return List.of(); // Return an empty list if outside
    }
}
