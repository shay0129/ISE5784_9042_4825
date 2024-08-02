package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Represents a plane in three-dimensional space.
 */
public class Plane extends Geometry {

    private final Point q;
    private final Vector normal;

    /**
     * Constructs a plane from three non-collinear points.
     *
     * @param a The first point.
     * @param b The second point.
     * @param c The third point.
     */
    public Plane(Point a, Point b, Point c) {
        Vector v1 = b.subtract(a);
        Vector v2 = c.subtract(a);
        normal = v1.crossProduct(v2).normalize();
        q = a;
    }

    /**
     * Constructs a plane from a point on the plane and its normal vector.
     *
     * @param a The point on the plane.
     * @param n The normal vector to the plane.
     */
    public Plane(Point a, Vector n) {
        q = a;
        normal = n.normalize();
    }

    /**
     * Retrieves the normal vector of the plane.
     *
     * @return The normal vector of the plane.
     */
    public Vector getNormal() {
        return normal;}

    @Override
    public Vector getNormal(Point point) {
        return getNormal(); }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Point p0 = ray.getHead();
        Vector v = ray.getDirection();

        //if the ray's head at the plane
        if(p0.equals(q))
            return null;

        //if the ray is parallel to the plane
        double t = alignZero((normal.dotProduct(v)));
        if(isZero(t))
            return null;


        //if the ray is the opposite direction to the normal
        t = alignZero(normal.dotProduct(q.subtract(p0))/ t);
        if(t <= 0 || alignZero(t - maxDistance) > 0)
            return null;


        return List.of(new GeoPoint(this, ray.getPoint(t)));
    }
}
