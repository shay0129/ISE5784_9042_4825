package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

/**
 * Plane class represents a geometric plane in 3-dimensional space.
 * It implements the {@link Geometry} interface and adds specific methods related to planes.
 * The plane is defined by a point and a normal vector.
 *
 * @see Geometry
 * @see Point
 * @see Vector
 * @see Ray
 * @see Util
 *
 * @author Shay and Asaf
 */
public class Plane extends Geometry {

    private final Point point;
    private final Vector normal;

    /**
     * Constructs a Plane using three points that lie on the plane.
     * The normal vector is calculated using the cross product of the vectors formed by these points.
     *
     * @param point1 the first point on the plane
     * @param point2 the second point on the plane
     * @param point3 the third point on the plane
     * @throws IllegalArgumentException if the points are collinear or any two points are the same
     */
    public Plane(Point point1, Point point2, Point point3) {
        point = point1;
        try {
            normal = point1.subtract(point2).crossProduct(point1.subtract(point3)).normalize();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The points are collinear or any two points are the same");
        }
    }

    /**
     * Constructs a Plane object using a point and a normal vector.
     *
     * @param point a point on the plane
     * @param normal the normal vector to the plane
     */
    public Plane(Point point, Vector normal) {
        if (normal.equals(Vector.ZERO)) {
            throw new IllegalArgumentException("Normal vector cannot be the zero vector");
        }
        this.normal = normal.normalize(); // Ensure the normal vector is normalized
        this.point = point;
    }

    /**
     * Returns the normal vector to the plane.
     *
     * @return the normal vector to the plane
     */
    public Vector getNormal() {
        return this.normal;
    }

    @Override
    public Vector getNormal(Point point) {
        return getNormal();
    }

    @Override
    protected List<Intersectable.GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        // Calculate the denominator of the division for finding the parameter t
        double denominator = this.normal.dotProduct(ray.getDirection());
        // If the denominator is close to zero, the ray is parallel to the plane
        if (Util.isZero(denominator))
            return null; // Ray is parallel to the plane

        // Calculate the numerator of the division for finding the parameter t
        Vector p0MinusQ0;
        try {
            p0MinusQ0 = point.subtract(ray.getHead());
        } catch (IllegalArgumentException ignore) {
            return null;
        }

        double numerator = this.normal.dotProduct(p0MinusQ0);
        // Calculate the parameter t
        double t = Util.alignZero(numerator / denominator);

        // If t is negative, the intersection point is behind the ray's start point
        if (t < 0)
            return null;

        // Calculate the intersection point
        Point intersectionPoint = ray.getPoint(t);

        // Return a list with a single GeoPoint containing this plane and the
        // intersection point
        return List.of(new Intersectable.GeoPoint(this, intersectionPoint));
    }
}
