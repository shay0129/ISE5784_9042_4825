package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.List;
import static primitives.Util.*;

/**
 * Represents a sphere in three-dimensional space.
 * The class is derived from RadialGeometry.
 *
 * @author Shay and Asaf
 */
public class Sphere extends RadialGeometry {

	private final Point center;

	/**
	 * Constructs a sphere with a specified radius and center point.
	 *
	 * @param radius The radius of the sphere.
	 * @param point The center point of the sphere.
	 */
	public Sphere(Point point, double radius) {
		super(radius);
		center = point;
	}

	@Override
	public Vector getNormal(Point point) {
		Vector normalVector = point.subtract(center);
		// Check if the point is the center of the sphere (should not happen for valid sphere surface points)
		if (normalVector.length() == 0) {
			throw new IllegalArgumentException("Point cannot be the center of the sphere");
		}
		return (normalVector.normalize());
	}

	@Override
	protected List<Intersectable.GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		// Check if the ray's head is at the center of the sphere
		if (ray.getHead().equals(this.center)) {
			return List.of(new Intersectable.GeoPoint(this, ray.getPoint(this.radius)));
		}

		// Calculate the vector from the ray's start point to the center of the sphere
		Vector u = this.center.subtract(ray.getHead());

		// Calculate the projection of u on the ray's direction vector
		double tm = u.dotProduct(ray.getDirection());

		// Calculate the distance squared from the ray's start point to the closest point to the sphere's center
		double dSquared = u.lengthSquared() - tm * tm;

		// Calculate the distance squared from the closest point to the intersection points on the sphere's surface
		double thSquared = this.radiusSquared - dSquared;

		// If the distance squared is greater than the sphere's radius squared, there are no intersections
		if (alignZero(thSquared) <= 0) {
			return null; // No intersections
		}

		// Calculate the distance from the closest point to the intersection points on the sphere's surface
		double th = Math.sqrt(thSquared);

		// Calculate the intersection points along the ray
		double t2 = tm + th;
		if (alignZero(t2) <= 0) {
			return null; // Both points are behind the ray
		}

		double t1 = tm - th;

		// Return the intersection points based on their positions relative to the ray's start point
		if (alignZero(t1) <= 0) {
			// Only one intersection point
			return List.of(new Intersectable.GeoPoint(this, ray.getPoint(t2)));
		} else {
			// Two intersection points
			return List.of(new Intersectable.GeoPoint(this, ray.getPoint(t1)), new Intersectable.GeoPoint(this, ray.getPoint(t2)));
		}
	}


}