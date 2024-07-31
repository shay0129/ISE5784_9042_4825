package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.ArrayList;
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
 * <p>Author: Shay and Asaf</p>
 */
public class Plane implements Geometry {

    private final Point point;
    private final Vector normal;

    /**
     * Constructs a Plane object using three points.
     *
     * <p>
     * The constructor calculates the normal vector based on the points given, and
     * stores one of the points as the reference point of the plane.
     *
     * @param point1 the first point
     * @param point2 the second point
     * @param point3 the third point
     */
    public Plane(Point point1, Point point2, Point point3) {
        point = point1; // Store one of the points as the reference point
        normal = (point2.subtract(point1)).crossProduct(point3.subtract(point1)).normalize(); // Normal vector calculation
    }

    /**
     * Constructs a Plane object using a point and a normal vector.
     *
     * @param point a point on the plane
     * @param normal the normal vector to the plane
     */
    public Plane(Point point, Vector normal) {
        this.point = point;
        this.normal = normal.normalize(); // Ensure the normal vector is normalized
    }

    /**
     * Returns the normal vector to the plane.
     *
     * @return the normal vector to the plane
     */
    public Vector getNormal() {
        return this.normal;
    }

    /**
     * Calculates the normal vector to the plane at a given point.
     *
     * @param point the point on the plane at which to calculate the normal vector
     * @return the normal vector to the plane at the specified point
     */
    @Override
    public Vector getNormal(Point point) {
        return getNormal();
    }

    /**
     * Finds the intersections between the plane and the given ray.
     *
     * @param ray the ray for which intersections with the plane are to be found
     * @return a list of intersection points. If no intersections are found, an empty list should be returned
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> intersections = null;
        Vector planeNormal = getNormal();
        double denominator = planeNormal.dotProduct(ray.getDirection());

        if (Util.isZero(denominator)) {
            return null;
        }

        Vector p0MinusQ0 = point.subtract(ray.getHead());
        double numerator = planeNormal.dotProduct(p0MinusQ0);
        double t = Util.alignZero(numerator / denominator);

        if (t < 0) {
            return null;
        } else {
            Point intersectionPoint = ray.getPoint(t);
            intersections = new ArrayList<>();
            intersections.add(intersectionPoint);
            return intersections;
        }
    }
}
