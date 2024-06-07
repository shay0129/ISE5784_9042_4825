package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Plane class represents a geometric plane in 3-dimensional space.
 * It extends the `Geometry` class and adds specific methods related to planes.
 * The plane is defined by a point and a normal vector.
 *
 * @author Shay and Asaf
 */
public class Plane extends  FlatGeometry {

    final Point q0;

    /**
     * Constructor that creates a new plane given a point on the plane and a normal vector.
     * @param q0 A point on the plane.
     * @param normal The normal vector to the plane.
     */
    public Plane(Point point, Vector normal) {
        this.q0 = point;
        this.normal = normal.normalize();
    }

    /**
     * Returns a point on the plane.
     * @return A point on the plane.
     */
    /**
     * constructor for Plane
     * @param x
     * @param y
     * @param z
     */
    public Plane(Point x, Point y, Point z) {
        Vector U = x.subtract(y); // x->y
        Vector V = x.subtract(z); // x->z
        Vector N = U.crossProduct(V); // x->y X x->z

        this.normal = N.normalize();
    }


    public Point getPoint() {
        return this.point;
    }

    /**
     * Returns the normal vector to the plane.
     * @return The normal vector to the plane.
     */
    public Vector getNormal() {
        return this.normal;
    }

    /**
     * Calculates the normal vector to the plane at a given point.
     * @param point The point on the plane at which to calculate the normal vector.
     * @return The normal vector to the plane at the specified point.
     */
    @Override
    public Vector getNormal(Point point) {
        return this.normal;
    }

    /**
     * Returns a string representation of the plane.
     * @return A string that describes the plane.
     */
    @Override
    public String toString() {
        return String.format("Plane(point: %s, normal: %s)", q0, normal);
    }
}