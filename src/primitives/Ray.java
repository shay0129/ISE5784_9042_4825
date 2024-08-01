package primitives;

import geometries.Intersectable.GeoPoint;
import java.util.List;

/**
 * The Ray class represents a directed line segment in Euclidean space.
 * It is defined by a starting point (head) and a direction vector.
 *
 * @author Shay and Asaf
 */
public class Ray {
    /** The starting point of the ray */
    private final Point head;
    /** The direction vector of the ray */
    private final Vector direction;

    /**
     * Constructs a Ray with the specified starting point and direction vector.
     *
     * @param point   the starting point (head) of the ray
     * @param vector  the direction vector of the ray; it is normalized internally
     */
    public Ray(Point point, Vector vector) {
        head = point;
        direction = vector.normalize(); // Ensure vector is normalized
    }

    /**
     * Returns the starting point (head) of the ray.
     *
     * @return the starting point of the ray
     */
    public Point getHead() {
        return head;
    }

    /***
     *
     * @param obj - an Object
     * @return true if the object equals this ray, else return false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Ray other)
                && this.head.equals(other.head)
                && this.direction.equals(other.direction);
    }

    /**
     * Returns the direction vector of the ray.
     *
     * @return the direction vector of the ray
     */
    public Vector getDirection() {
        return direction;
    }

    /**
     * Computes a point on the ray at a specified distance from the ray's origin.
     *
     * @param t the distance from the ray's origin to the computed point
     * @return the computed point on the ray at the specified distance from its origin
     *         If {@code t} is zero, the method returns the ray's starting point.
     */
    public Point getPoint(double t) {
        if (Util.isZero(t)) {
            return head;
        } else {
            return head.add(direction.scale(t));
        }
    }

    /**
     * Finds the closest point
     * @param pList //collection of points
     * @return closest point to ray's head
     */
    public Point findClosestPoint(List<Point> pList) {
        if (pList == null)
            return null;

        double distance = Double.POSITIVE_INFINITY;
        Point closest = null;
        for (Point p : pList)
            if (p.distance(head) < distance) {
                distance = p.distance(head);
                closest = p;
            }
        return closest;
    }

    /**
     * Finds the closest GeoPoint to the start of the ray from a collection of
     * GeoPoints.
     *
     * @param intersections The collection of GeoPoints.
     * @return The closest GeoPoint to the start of the ray.
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> intersections) {
        // Initialize variables to store the closest GeoPoint and its distance
        GeoPoint closestGeoPoint = null;
        double closestDistance = Double.POSITIVE_INFINITY;

        // Iterate through the list of GeoPoints
        for (GeoPoint geoPoint : intersections) {
            // Calculate the distance between the origin of the ray and the current GeoPoint
            double distance = head.distance(geoPoint.point);

            // Check if the current GeoPoint is closer than the previous closest GeoPoint
            if (distance < closestDistance) {
                closestGeoPoint = geoPoint;
                closestDistance = distance;
            }
        }

        // Return the closest GeoPoint
        return closestGeoPoint;
    }

    @Override
    public String toString() {
        return "Ray: " + head + " -> " + direction;
    }


}

