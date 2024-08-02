package primitives;

import geometries.Intersectable.GeoPoint;

import java.util.List;
import java.util.Objects;

import static primitives.Util.isZero;

/**
 * The Ray class represents a directed line segment in Euclidean space.
 * It is defined by a starting point (head) and a direction vector.
 *
 * @author Shay and Asaf
 */
public class Ray {

    final private Point head;
    final private Vector direction;
    private static final double DELTA = 0.1;

    /**
     * Constructs a Ray with the specified starting point and direction vector.
     *
     * @param pHead   the starting point (head) of the ray
     * @param vDirection  the direction vector of the ray; it is normalized internally
     */
    public Ray(Point pHead, Vector vDirection) {
        head = pHead;
        direction = vDirection.normalize(); // Ensure vector is normalized
    }

    /**
     * Constructor to initialize a Ray object with a given head point, direction vector and normal vector.
     * The direction vector is normalized.
     * @param pHead the starting point of the ray
     * @param vDirection the direction vector of the ray
     * @param n the normal vector
     */
    public Ray(Point pHead, Vector vDirection, Vector n) {
        this.direction = vDirection.normalize();
        double nDir = this.direction.dotProduct(n);
        this.head = isZero(nDir) ? pHead :
                nDir > 0 ? pHead.add(n.scale(0.1)) : pHead.add(n.scale(-0.1));
    }


    /**
     * @param t the distance
     * @return the ray points from a given distance
     */
    public Point getPoint(double t){
        if(isZero(t))
            return head;
        return head.add(direction.scale(t));
    }
    /**
     * @return the starting point of the ray.
     */
    public Point getHead() {
        return head;
    }
    /**
     * @return the direction of the ray.
     */
    public Vector getDirection() {
        return direction;
    }

    /**
     * Finds the closest point to the head of the ray from a list of points.
     * @param points the list of points
     * @return the closest point to the head of the ray, or null if the list is empty
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    /**
     * Finds the closest GeoPoint to the head of the ray from a list of points.
     * @param points the list of points
     * @return the closest GeoPoint to the head of the ray, or null if the list is empty
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> points) {
        if (points == null || points.isEmpty()) {
            return null;
        }
        GeoPoint closest = null;
        double closestDistance = Double.MAX_VALUE;
        for (GeoPoint point : points) {
            double distance = head.distance(point.point);
            if (distance < closestDistance) {
                closestDistance = distance;
                closest = point;
            }
        }
        return closest;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return head.equals(ray.head) && direction.equals(ray.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, direction);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "head=" + head +
                ", direction=" + direction +
                '}';
    }
}

