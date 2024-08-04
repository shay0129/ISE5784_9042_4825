package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * Interface for objects that can be intersected by a ray.
 *
 * @author Shay and Asaf
 */
public abstract class Intersectable {

	/**
	 * Finds intersection points between the intersectable object and a given ray.
	 *
	 * @param ray The ray to intersect with the object.
	 * @return A list of intersection points between the object and the ray. If no
	 *         intersections are found, an empty list is returned.
	 */
	public final List<Point> findIntersections(Ray ray) {
		var geoPointsList = findGeoIntersections(ray);
		return geoPointsList == null ? null : geoPointsList.stream().map(gp -> gp.point).toList();
	}

	/**
	 * Public method findGeoIntersections for finding GeoPoints of intersections
	 * between the intersectable object and a given ray.
	 *
	 * @param ray The ray to intersect with the object.
	 * @return A list of GeoPoints representing intersection points between the
	 *         object and the ray.
	 */
	public final List<GeoPoint> findGeoIntersections(Ray ray) {
		return findGeoIntersectionsHelper(ray);
	}

	/**
	 * Protected method findGeoIntersectionsHelper for finding GeoPoints of
	 * intersections between the intersectable object and a given ray. This method
	 * should be implemented in subclasses.
	 *
	 * @param ray The ray to intersect with the object.
	 * @return A list of GeoPoints representing intersection points between the
	 *         object and the ray.
	 */
	protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);// לתקן


	/**
	 * Inner static class GeoPoint representing a point in three-dimensional space with its related geometry.
	 */
	public static class GeoPoint {
		/**
		 * The geometry object of the intersection
		 * The actual point of intersection
		 */
		public Geometry geometry;
		public Point point;

		/**
		 * Constructor for GeoPoint.
		 *
		 * @param geometry The geometry object of the intersection.
		 * @param point    The actual point of intersection.
		 */
		public GeoPoint(Geometry geometry, Point point) {
			this.geometry = geometry;
			this.point = point;
		}

		@Override
		public boolean equals(Object object) {
			if (this == object) return true;    // Check if the objects are the same object in memory.
			if (!(object instanceof GeoPoint geoPoint)) return false;   // Check if the object is of the correct type.
			return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);  // Check if the two fields are equal.

		}

		@Override
		public String toString() {
			return "GeoPoint{" + "geometry=" + geometry + ", point=" + point + '}';
		}

	}
}
