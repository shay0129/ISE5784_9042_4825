package primitives;

import geometries.Intersectable;

import java.util.List;

import static primitives.Util.isZero;

/**
 * Ray class represents a directed line segment in Euclidean space.
 * It is defined by a starting point (head) and a direction vector.
 * @author Shay and Asaf
 */
public class Ray {
    /**
     * DELTA value to move the point away from original point
     */
    private static final double DELTA = 0.1;

    private final Point p0;
    private final Vector dir;

    /**
     * Constructor that creates a new ray given its head point and direction vector.
     * @param head The starting point of the ray.
     * @param dir The direction vector of the ray.
     */
    public Ray(Point head, Vector dir) {
        this.p0 = head;
        this.dir = dir.normalize();
    }

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }
    /**
     * Checks if this ray is equal to another object.
     * @param o The object to compare with this ray.
     * @return true if the object is a ray and has the same head and direction, false otherwise.
     */
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;

        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    /**
     * Returns a hash code value for the ray.
     * @return A hash code value for this ray.
     */
    @Override
    public int hashCode() {
        int result = p0.hashCode();
        result = 31 * result + dir.hashCode();
        return result;
    }

    /**
     * Returns a string representation of the ray.
     * @return A string that describes the ray.
     */
    @Override
    public String toString() {
        return "Ray{" +
                "head=" + p0 +
                ", direction=" + dir +
                '}';
    }


    public Point getPoint(double delta) {
        if (isZero(delta)) {
            return p0;
        }
        return p0.add(dir.scale(delta));
    }

    public Intersectable.GeoPoint findClosestGeoPoint(List<Intersectable.GeoPoint> intersections) {
        Intersectable.GeoPoint closestpoint = null;
        double minDistance = Double.MAX_VALUE;
        double ptDistance;

        for (Intersectable.GeoPoint geoPoint : intersections) {
            ptDistance = geoPoint.point.distanceSquared(p0);
            if (ptDistance < minDistance) {
                minDistance = ptDistance;
                closestpoint = geoPoint;
            }
        }
        return closestpoint;
    }
}