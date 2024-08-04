package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Represents a plane in three-dimensional space.
 * The class is derived from Geometry.
 *
 * @author Shay and Asaf
 */
public class Plane extends Geometry {

	private final Point point;
	private final Vector normal;

	/**
	 * Constructs a plane from three non-collinear points.
	 *
	 * @param a The first point.
	 * @param b The second point.
	 * @param c The third point.
	 * @throws IllegalArgumentException if the three points are collinear
	 */
	public Plane(Point a, Point b, Point c) {
		Vector v1 = b.subtract(a);
		Vector v2 = c.subtract(a);

		// Check that the vectors are not collinear
		if (v1.crossProduct(v2).length() == 0) {
			throw new IllegalArgumentException("The three points are collinear");
		}

		this.normal = v1.crossProduct(v2).normalize();
		this.point = a;
	}

	/**
	 * Constructs a Plane object using a point and a normal vector.
	 *
	 * @param p a point on the plane
	 * @param n the normal vector to the plane
	 */
	public Plane(Point p, Vector n) {
		point = p;
		normal = n.normalize();
	}

	/**
	 * Retrieves the normal vector of the plane.
	 *
	 * @return The normal vector of the plane.
	 */
	public Vector getNormal() {
		return normal;
	}

	@Override
	public Vector getNormal(Point point) {
		return getNormal();
	}

	@Override
	protected List<Intersectable.GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		// Calculate the denominator of the division for finding the parameter t
		double denominator = this.normal.dotProduct(ray.getDirection());
		// If the denominator is close to zero, the ray is parallel to the plane
		if (Util.isZero(denominator))
			return null; // Ray is parallel to the plane

		// Calculate the numerator of the division for finding the parameter t
		Vector p0MinusQ0;
		try {
			p0MinusQ0 = point.subtract(ray.getHead());
		} catch (IllegalArgumentException ignore) {
			return null;
		}

		double numerator = this.normal.dotProduct(p0MinusQ0);
		// Calculate the parameter t
		double t = Util.alignZero(numerator / denominator);

		// If t is negative, the intersection point is behind the ray's start point
		if (t < 0)
			return null;

		// Calculate the intersection point
		Point intersectionPoint = ray.getPoint(t);

		// Return a list with a single GeoPoint containing this plane and the intersection point
		return List.of(new Intersectable.GeoPoint(this, intersectionPoint));
	}
}
