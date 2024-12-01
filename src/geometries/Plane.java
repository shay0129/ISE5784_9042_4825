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

	private final Point q;       // A point on the plane
	private final Vector normal; // The normal vector to the plane

	/**
	 * Constructs a plane passing through a point with a given normal vector.
	 *
	 * @param q      A point on the plane.
	 * @param normal The normal vector to the plane. If its length is not 1, it will be normalized.
	 */
	public Plane(Point q, Vector normal) {
		this.q = q;
		// Avoid normalizing if the vector is already normalized
		if (normal.lengthSquared() != 1) {
			normal = normal.normalize();
		}
		this.normal = normal;
	}

	/**
	 * Constructs a plane passing through three non-collinear points.
	 *
	 * @param p1 The first point on the plane.
	 * @param p2 The second point on the plane.
	 * @param p3 The third point on the plane.
	 * @throws IllegalArgumentException If the points are collinear.
	 */
	public Plane(Point p1, Point p2, Point p3) throws IllegalArgumentException {
		q = p1;
		try {
			normal = p1.subtract(p3).crossProduct(p1.subtract(p2)).normalize();
		} catch (IllegalArgumentException ex) {
			throw new IllegalArgumentException("Points must not be collinear.");
		}
	}

	@Override
	public Vector getNormal(Point point) {
		return normal;
	}

	/**
	 * Retrieves the normal vector of the plane.
	 *
	 * @return The normal vector.
	 */
	public Vector getNormal() {
		return normal;
	}

	@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
		// Check if the ray starts from the plane's reference point
		if (q.equals(ray.getHead())) {
			return null;
		}

		// Check if the ray is parallel to the plane
		if (isZero(normal.dotProduct(ray.getDirection()))) {
			return null;
		}

		// Calculate the scalar t for the intersection point
		double t = normal.dotProduct(q.subtract(ray.getHead())) / normal.dotProduct(ray.getDirection());
		if (t <= 0 || isZero(t) || alignZero(t - maxDistance) >= 0) {
			return null;
		}

		return List.of(new GeoPoint(this, ray.getPoint(t)));
	}
}
