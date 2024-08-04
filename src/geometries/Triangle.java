package geometries;

import primitives.Ray;
import primitives.Vector;

import java.util.List;
import primitives.Point;
import static primitives.Util.*;

/**
 * Represents a triangle in three-dimensional space.
 * The class is derived from Polygon.
 *
 * @author Shay and Asaf
 */
public class Triangle extends Polygon {

	/**
	 * Constructs a triangle from three specified points.
	 *
	 * @param p1 The first point.
	 * @param p2 The second point.
	 * @param p3 The third point.
	 */
	public Triangle(Point p1, Point p2, Point p3) {
		super(p1, p2, p3);
	}


	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		// Find intersection points with the plane containing the triangle
		List<Point> intersectionPoints = plane.findIntersections(ray);

		// If there are no intersection points with the plane, return null
		if (intersectionPoints == null) {
			return null;
		}

		// Get the ray's head and direction
		Point head = ray.getHead();
		Vector v = ray.getDirection();

		// Check if the intersection point lies inside the triangle
		Vector v1 = vertices.getFirst().subtract(head);
		Vector v2 = vertices.get(1).subtract(head);
		Vector n1 = v1.crossProduct(v2).normalize();
		double sign1 = alignZero(v.dotProduct(n1));
		if (sign1 == 0) {
			return null;
		}

		Vector v3 = vertices.get(2).subtract(head);
		Vector n2 = v2.crossProduct(v3).normalize();
		double sign2 = alignZero(v.dotProduct(n2));
		if (sign1 * sign2 <= 0) {
			return null;
		}

		Vector n3 = v3.crossProduct(v1).normalize();
		double sign3 = alignZero(v.dotProduct(n3));
		if (sign1 * sign3 <= 0) {
			return null;
		}

		// Return the intersection point inside the triangle
		return List.of(new GeoPoint(this, intersectionPoints.get(0)));
	}


}