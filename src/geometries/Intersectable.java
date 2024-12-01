package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Intersectable is the base abstract class representing any type of intersectable object,
 * such as spheres or planes, within a 3D Cartesian coordinate system.
 */
public abstract class Intersectable {

	/**
	 * GeoPoint is a nested class representing a point of intersection,
	 * along with additional information about the associated geometry.
	 */
	public static class GeoPoint {
		/**
		 * The geometry to which the point of intersection belongs.
		 */
		public Geometry geometry;

		/**
		 * The point of intersection.
		 */
		public Point point;

		/**
		 * Constructs a GeoPoint with the specified geometry and point.
		 *
		 * @param geometry the geometry associated with the intersection point
		 * @param point    the point of intersection
		 */
		public GeoPoint(Geometry geometry, Point point) {
			this.geometry = geometry;
			this.point = point;
		}

		/**
		 * Retrieves the normal vector at this point on the geometry.
		 *
		 * @return the normal vector at this intersection point
		 */
		public Vector getNormal() {
			return geometry.getNormal(point);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			return obj instanceof GeoPoint other &&
					other.geometry == this.geometry &&
					other.point.equals(this.point);
		}

		@Override
		public String toString() {
			return "Geometry: " + geometry + " Point: " + point;
		}
	}

	/**
	 * Finds intersection points of the given ray with this object.
	 *
	 * @param ray the ray used for intersection
	 * @return a list of intersection points, or null if none are found
	 */
	public List<Point> findIntersections(Ray ray) {
		var geoList = findGeoIntersections(ray);
		return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
	}

	/**
	 * Finds intersection points of the ray with geometries up to a specified maximum distance.
	 *
	 * @param ray         the ray used for intersection
	 * @param maxDistance the maximum distance to consider for intersections
	 * @return a list of intersection points, or null if none are found
	 */
	public List<Point> findIntersections(Ray ray, double maxDistance) {
		var geoList = findGeoIntersections(ray, maxDistance);
		return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
	}

	/**
	 * Finds intersection details of the ray with this object, considering an infinite distance.
	 *
	 * @param ray the ray used for intersection
	 * @return a list of GeoPoint instances representing intersections, or null if none are found
	 */
	public final List<GeoPoint> findGeoIntersections(Ray ray) {
		return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
	}

	/**
	 * Finds intersection details of the ray with this object, considering a specified maximum distance.
	 *
	 * @param ray         the ray used for intersection
	 * @param maxDistance the maximum distance to consider for intersections
	 * @return a list of GeoPoint instances representing intersections, or null if none are found
	 */
	public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
		return findGeoIntersectionsHelper(ray, maxDistance);
	}

	/**
	 * A helper method for subclasses to implement specific intersection logic.
	 *
	 * @param ray         the ray used for intersection
	 * @param maxDistance the maximum distance to consider for intersections
	 * @return a list of GeoPoint instances representing intersections, or null if none are found
	 */
	protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);
}
