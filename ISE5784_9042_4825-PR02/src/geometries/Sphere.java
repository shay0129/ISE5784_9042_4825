package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
import java.util.List;

/**
 * Represents a geometric sphere in 3-dimensional space.
 * The sphere is defined by a center point and a radius.
 * It extends the {@link RadialGeometry} class and provides methods specific to spheres.
 *
 * @autor Shay and Asaf
 */
public class Sphere extends RadialGeometry {

    /** Center point of the sphere */
    private final Point center;

    /**
     * Constructs a Sphere object with the specified center point and radius.
     *
     * @param center the center point of the sphere
     * @param radius the radius of the sphere
     */
    public Sphere(Point center, double radius) {
        super(radius);
        this.center = center;
    }

    /**
     * Computes the normal vector to the sphere at a given point.
     *
     * @param point the point on the surface of the sphere where the normal vector is computed
     * @return the normal vector to the sphere at the specified point
     */
    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }

    /**
     * Finds the intersection points between the sphere and a given ray.
     *
     * @param ray the ray to intersect with the sphere
     * @return a list of intersection points between the ray and the sphere.
     *         If no intersections are found, an empty list is returned.
     */
    @Override
    public List<Point> findIntersections(Ray ray) {

        if (ray.getHead().equals(this.center)) {
            return List.of(ray.getPoint(this.radius));
        }

        Vector u = this.center.subtract(ray.getHead());
        double tm = u.dotProduct(ray.getDirection());
        double d = Math.sqrt(u.lengthSquared() - tm * tm);

        if (d >= this.radius) {
            return List.of(); // Return an empty list if no intersection
        }

        double th = Math.sqrt(radiusSquared - d * d);
        double t1 = tm - th;
        double t2 = tm + th;

        boolean t1Valid = Util.alignZero(t1) > 0;
        boolean t2Valid = Util.alignZero(t2) > 0;

        if (t1Valid && t2Valid) {
            Point p1 = ray.getPoint(t1);
            Point p2 = ray.getPoint(t2);
            return List.of(p1, p2);

        } else if (t2Valid) {
            Point p2 = ray.getPoint(t2);
            return List.of(p2);

        } else {
            return List.of(); // Return an empty list if no valid intersections
        }
    }
}
