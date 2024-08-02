package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static java.lang.Math.sqrt;
import static primitives.Util.alignZero;

/**
 * Represents a sphere in three-dimensional space.
 */
public class Sphere extends RadialGeometry {

    final private Point center;

    /**
     * Constructs a sphere with a specified radius and center point.
     *
     * @param radius The radius of the sphere.
     * @param point The center point of the sphere.
     */
    public Sphere(Point point, double radius) {
        super(radius);
        center = point;
    }

    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Vector u;
        try {
            // Vector from the ray's origin to the sphere's center
            u = center.subtract(ray.getPoint(0));
        } catch (IllegalArgumentException e) {
            // Ray starts at the sphere's center, return the point on the sphere's surface
            return List.of(new GeoPoint(this,ray.getPoint(radius)));
        }

        // Projection of vector u onto the ray's direction
        double tm = alignZero(ray.getDirection().dotProduct(u));

        // Distance squared from the sphere's center to the ray's path
        double dSquared = u.lengthSquared() - tm * tm;
        double d = alignZero(sqrt(dSquared));

        // If the distance is greater than the sphere's radius, there's no intersection
        if (d >= radius) {
            return null;
        }

        // Distance from the closest point to the intersection points
        double th = alignZero(sqrt(radius * radius - d * d));

        // Intersection points along the ray
        double t1 = alignZero(tm + th);
        double t2 = alignZero(tm - th);

        // Both intersection points are in front of the ray's origin
        if (t1 > 0 && t2 > 0 && alignZero(t1 - maxDistance) <= 0 && alignZero(t2 - maxDistance) <= 0) {
            return List.of(
                    new GeoPoint(this,ray.getPoint(t1)),
                    new GeoPoint(this,ray.getPoint(t2)));
        }

        // Only the first intersection point is in front of the ray's origin
        if (t1 > 0 && alignZero(t1 - maxDistance) <= 0) {
            return List.of(new GeoPoint(this,ray.getPoint(t1)));
        }

        // Only the second intersection point is in front of the ray's origin
        if (t2 > 0 && alignZero(t2 - maxDistance) <= 0) {
            return List.of(new GeoPoint(this,ray.getPoint(t2)));
        }

        // No intersection points are in front of the ray's origin
        return null;
    }

}




