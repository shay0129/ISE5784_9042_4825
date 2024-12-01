package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Represents a sphere in a 3D Cartesian coordinate system.
 */
public class Sphere extends RadialGeometry {

	private final Point center; // The center point of the sphere.

	/**
	 * Constructs a sphere with the specified radius and center point.
	 *
	 * @param center the center point of the sphere
	 * @param radius the radius of the sphere
	 * @throws IllegalArgumentException if the radius is negative
	 */
	public Sphere(Point center,double radius) throws IllegalArgumentException {
		super(radius);
		this.center = center;
	}

	@Override
	public Vector getNormal(Point point) {
		return point.subtract(center).normalize();
	}

	@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
		if (center.equals(ray.getHead())) {
			return List.of(new GeoPoint(this, ray.getPoint(radius)));
		}

		Vector u = center.subtract(ray.getHead());
		double tm = u.dotProduct(ray.getDirection());
		double d = Math.sqrt(u.lengthSquared() - tm * tm);

		if (d >= radius || isZero(d - radius)) {
			return null;
		}

		double th = Math.sqrt(radius * radius - d * d);

		if (tm + th < 0 || isZero(tm + th)) {
			return null;
		}
		if ((tm - th < 0 || isZero(tm - th)) && alignZero(tm + th - maxDistance) < 0) {
			return List.of(new GeoPoint(this, ray.getPoint(tm + th)));
		}
		if (tm - th < 0 || isZero(tm - th)) {
			return null;
		}
		if (alignZero(tm + th - maxDistance) < 0) {
			return List.of(
					new GeoPoint(this, ray.getPoint(tm - th)),
					new GeoPoint(this, ray.getPoint(tm + th))
			);
		}
		if (alignZero(tm - th - maxDistance) < 0) {
			return List.of(new GeoPoint(this, ray.getPoint(tm - th)));
		}

		return null;
	}
}
