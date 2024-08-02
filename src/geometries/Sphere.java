package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Represents a sphere in three-dimensional space.
 * The class is derived from RadialGeometry.
 *
 * @author Shay and Asaf
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

        Vector normalVector = point.subtract(center);
        // Check if the point is the center of the sphere (should not happen for valid sphere surface points)
        if (normalVector.length() == 0) {
            throw new IllegalArgumentException("Point cannot be the center of the sphere");
        }
        return normalVector.normalize();
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        if (this.center.equals(ray.getHead())) {
            return List.of(new GeoPoint(this, ray.getHead().add(ray.getDirection().scale(this.radius))));
        }

        Vector U = this.center.subtract(ray.getHead());
        double tm = ray.getDirection().dotProduct(U);
        double dSquared = U.lengthSquared() - tm * tm;
        double radiusSquared = this.radius * this.radius;

        // If dSquared is greater than or equal to radiusSquared, there are no intersections
        if (dSquared >= radiusSquared) {
            return null;
        }

        double th = Math.sqrt(radiusSquared - dSquared);
        double t1 = tm - th;
        double t2 = tm + th;
        if (t1 > maxDistance || t2 > maxDistance) return null;


        if (alignZero(t1) > 0 && alignZero(t2) > 0) {
            return List.of(new GeoPoint(this, ray.getPoint(t1)), new GeoPoint(this, ray.getPoint(t2)));
        } else if (alignZero(t1) > 0) {
            return List.of(new GeoPoint(this, ray.getPoint(t1)));
        } else if (alignZero(t2) > 0) {
            return List.of(new GeoPoint(this, ray.getPoint(t2)));
        } else {
            return null;
        }
    }
}




