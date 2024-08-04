package primitives;

import geometries.Intersectable.GeoPoint;
import java.util.List;

import static primitives.Util.isZero;

/**
 * The Ray class represents a directed line segment in Euclidean space.
 * It is defined by a starting point (head) and a direction vector.
 *
 * @author Shay and Asaf
 */
public class Ray {

	private final Point head;
	private final Vector direction;
	private static final double DELTA = 0.1;

	/**
	 * ray constructor
	 *
	 * @param point  the starting point (head) of the ray
	 * @param vector  the direction vector of the ray; it is normalized internally
	 */
	public Ray(Point point, Vector vector) {
		head = point;
		direction = vector.normalize();
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
	 * @param d the distance
	 * @return the ray points from a given distance
	 */
	public Point getPoint(double d) {
		return isZero(d) ? head : head.add(direction.scale(d));
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
	 * @param intersections the list of points
	 * @return the closest GeoPoint to the head of the ray, or null if the list is empty
	 */
	public GeoPoint findClosestGeoPoint(List<GeoPoint> intersections) {
		if (intersections == null || intersections.isEmpty()) {
			return null;
		}

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
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Ray ray)) return false;
		return head.equals(ray.head) && direction.equals(ray.direction);
	}

	@Override
	public String toString() {
		return "Ray:" + head + "->" + direction;
	}
}
