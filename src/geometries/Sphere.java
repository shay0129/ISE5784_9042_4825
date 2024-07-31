package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
import java.util.List;

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
    public List<Point> findIntersections(Ray ray) {

        Point p0 = ray.getHead();
        Vector v = ray.getDirection();
        Vector u;

        try {
            u = center.subtract(p0); // p0 == center the ray start from the center of the sphere
        } catch (IllegalArgumentException e) {
            return List.of(Util.isZero(this.radius) ? p0 : p0.add(ray.getDirection().scale(this.radius)));
        }

        double tm = Util.alignZero(v.dotProduct(u));
        double dSquared = u.lengthSquared() - tm * tm;
        double thSquared = Util.alignZero(this.radius * this.radius - dSquared);

        if (thSquared <= 0)
            return null;// no intersections

        double th = Util.alignZero(Math.sqrt(thSquared));
        if (th == 0)
            return null;// ray tangent to sphere

        double t1 = Util.alignZero(tm - th);
        double t2 = Util.alignZero(tm + th);

        // ray starts after sphere
        if (Util.alignZero(t1) <= 0 && Util.alignZero(t2) <= 0)
            return null;

        // 2 intersections
        if (Util.alignZero(t1) > 0 && Util.alignZero(t2) > 0) {
            // P1 , P2
            return List.of(Util.isZero(t1) ? p0 : p0.add(ray.getDirection().scale(t1)),
                    Util.isZero(t2) ? p0 : p0.add(ray.getDirection().scale(t2)));
        }

        // 1 intersection
        if (Util.alignZero(t1) > 0)
            return List.of(Util.isZero(t1) ? p0 : p0.add(ray.getDirection().scale(t1)));
        else
            return List.of(Util.isZero(t2) ? p0 : p0.add(ray.getDirection().scale(t2)));
    }
}