package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Sphere class represents a geometric sphere defined by a center point and a radius.
 * It extends the `RadianGeometry` class and adds specific methods related to spheres.
 * The sphere is defined by a central point and a radius.
 *
 * @author Shay and Asaf
 */
public class Sphere extends Geometry{


    /**
     * The center point of the sphere.
     */
    private final Point center;

    /**
     * The radius of the sphere.
     */
    private final double radius;

    // Constructor

    /**
     * Constructor that creates a new sphere given its center point and radius.
     *
     * @param center The center point of the sphere.
     * @param radius The radius of the sphere.
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    // Getter methods

    /**
     * Returns the center point of the sphere.
     *
     * @return The center point of the sphere.
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Returns the radius of the sphere.
     *
     * @return The radius of the sphere.
     */
    public double getRadius() {
        return radius;
    }

    // Other potential methods

    /**
     * Calculates and returns the surface area of the sphere.
     *
     * @return The surface area of the sphere.
     */
    public double getSurfaceArea() {
        return 4 * Math.PI * Math.pow(radius, 2);
    }

    /**
     * Calculates and returns the volume of the sphere.
     *
     * @return The volume of the sphere.
     */
    public double getVolume() {
        return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }

    /**
     * Checks if a given point is inside the sphere.
     *
     * @param point The point to check.
     * @return true if the point is inside the sphere, false otherwise.
     */
    public boolean containsPoint(Point point) {
        double distance = center.distance(point);
        return distance <= radius;
    }

    /**
     * Calculates the normal vector to the sphere at a given point.
     *
     * @param point The point on the sphere at which to calculate the normal vector.
     * @return The normal vector to the sphere at the specified point.
     */

    public Vector getNormal(Point point) {
        if (point.equals(center)) {
            throw new IllegalArgumentException("Cannot calculate normal to a point on the sphere's center");
        }
        return point.subtract(center).normalize();
    }

    /**
     * Returns a string representation of the sphere.
     *
     * @return A string that describes the sphere.
     */
    @Override
    public String toString() {
        return String.format("Sphere(center: %s, radius: %.2f)", center, radius);
    }
    //change
    @Override
    public List<Intersectable.GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Point P0 = ray.getP0();
        Vector v = ray.getDir();

        if (P0.equals(center)) {
            if(alignZero(radius - maxDistance) > 0){
                return null;
            }
            return List.of(new Intersectable.GeoPoint(this, center.add(v.scale(radius))));
        }

        Vector U = center.subtract(P0);

        double tm = alignZero(v.dotProduct(U));
        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm));

        // no intersections : the ray direction is above the sphere
        if (d >= radius) {
            return null;
        }

        double th = alignZero(Math.sqrt(radius * radius - d * d));

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        if(t1 <=0 && t2 <= 0){
            return null;
        }

        if (t1 > 0 && t2 > 0 && alignZero(t1 - maxDistance) <= 0 && alignZero(t2 - maxDistance) <= 0) {
//            Point P1 = P0.add(v.scale(t1));
//            Point P2 = P0.add(v.scale(t2));
            Point P1 =ray.getPoint(t1);
            Point P2 =ray.getPoint(t2);
            return List.of(new Intersectable.GeoPoint(this,P1), new Intersectable.GeoPoint(this,P2));
        }
        if (t1 > 0  && alignZero(t1 - maxDistance) <= 0) {
//            Point P1 = P0.add(v.scale(t1));
            Point P1 =ray.getPoint(t1);
            return List.of(new Intersectable.GeoPoint(this,P1));
        }
        if (t2 > 0 && alignZero(t2 - maxDistance) <= 0) {
//            Point P2 = P0.add(v.scale(t2));
            Point P2 =ray.getPoint(t2);
            return List.of(new Intersectable.GeoPoint(this,P2));
        }
        return null;
    }
    }
}