package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
import java.util.List;

import static primitives.Util.alignZero;

/**
 * Represents a sphere in 3D space, defined by a center point and a radius.
 * The sphere is a spherical shape with a constant radius from its center to any point on its surface.
 * It extends the {@link RadialGeometry} class, inheriting the radius attribute.
 *
 * <p>The sphere provides methods to:
 * <ul>
 *     <li>Compute the normal vector at a given point on the surface.</li>
 *     <li>Find intersection points with a given {@link Ray}.</li>
 * </ul>
 * </p>
 *
 * @see RadialGeometry
 * @see Point
 * @see Ray
 * @see Vector
 * @see Util
 *
 * @autor Shay and Asaf
 */
public class Sphere extends RadialGeometry {
    private final Point center;

    /**
     * Constructs a {@code Sphere} object with the specified center point and radius.
     *
     * <p>The radius must be a positive value. If the radius is non-positive, an {@link IllegalArgumentException}
     * will be thrown. The center of the sphere defines the location of the sphere in 3D space.</p>
     *
     * @param center the center point of the sphere, which defines the location of the sphere in 3D space
     * @param radius the radius of the sphere, which must be positive
     * @throws IllegalArgumentException if the radius is non-positive
     */
    public Sphere(Point center, double radius) {
        super(radius);
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive.");
        }
        this.center = center;
    }

    @Override
    public Vector getNormal(Point point) {
        if (point.equals(center)) {
            throw new IllegalArgumentException("The point cannot be the center of the sphere.");
        }
        // The normal vector to a sphere is the unit vector from the center to the point
        return point.subtract(center).normalize();
    }

    @Override
    protected List<Intersectable.GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        // Initialize an empty list to store the intersection GeoPoints
        // List<GeoPoint> intersections = new ArrayList<>();
        if (ray.getHead().equals(this.center))
            return List.of(new Intersectable.GeoPoint(this, ray.getPoint(this.radius)));

        // Calculate the vector from the ray's start point to the center of the sphere
        Vector u = this.center.subtract(ray.getHead());

        // Calculate the projection of u on the ray's direction vector
        double tm = u.dotProduct(ray.getDirection());
        // Calculate the distance from the ray's start point to the closest point to the
        // sphere's center
        double dSquared = u.lengthSquared() - tm * tm;
        double thSquared = this.radiusSquared - dSquared;
        // If the distance is greater than the sphere's radius, there are no
        // intersections
        if (alignZero(thSquared) <= 0)
            return null; // Return an empty list

        // Calculate the distance from the closest point to the intersection points on
        // the sphere's surface
        double th = Math.sqrt(thSquared);

        // Calculate the intersection points. It's always t2 > t1
        double t2 = tm + th;
        if (alignZero(t2) <= 0)
            return null; // both points are behind the ray

        double t1 = tm - th;

        if (alignZero(t1) <= 0) {
            // Only one intersection point
            return List.of(new Intersectable.GeoPoint(this, ray.getPoint(t2)));
        } else {
            // Two intersection points
            return List.of(new Intersectable.GeoPoint(this, ray.getPoint(t1)),
                    new Intersectable.GeoPoint(this, ray.getPoint(t2)));
        }
    }
}