package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Represents a triangle in three-dimensional space.
 * A triangle is defined by three vertices and extends the {@link Polygon} class.
 *
 * @see Polygon
 * @see Ray
 * @see Vector
 * @see Point
 *
 * @autor Shay and Asaf
 */
public class Triangle extends Polygon {

    /**
     * Constructs a triangle with three given vertices.
     *
     * @param p1 the first vertex
     * @param p2 the second vertex
     * @param p3 the third vertex
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    @Override
    protected List<Intersectable.GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        // First, check for intersection with the plane
        List<Point> planeIntersections = plane.findIntersections(ray);

        // If no intersections with the plane, return null
        if (planeIntersections == null || planeIntersections.isEmpty()) {
            return null;
        }

        // Proceed to check if the intersection point is within the triangle
        Point intersectionPoint = planeIntersections.get(0);

        Vector v = ray.getDirection();
        Vector v1 = vertices.get(0).subtract(ray.getHead());
        Vector v2 = vertices.get(1).subtract(ray.getHead());
        Vector v3 = vertices.get(2).subtract(ray.getHead());

        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();

        double sign1 = alignZero(v.dotProduct(n1));
        double sign2 = alignZero(v.dotProduct(n2));
        double sign3 = alignZero(v.dotProduct(n3));

        // Check if the intersection point is inside the triangle
        if ((sign1 > 0 && sign2 > 0 && sign3 > 0) || (sign1 < 0 && sign2 < 0 && sign3 < 0)) {
            // Create a GeoPoint for the intersection point
            return List.of(new Intersectable.GeoPoint(this, intersectionPoint));
        }

        return null;
    }
}
