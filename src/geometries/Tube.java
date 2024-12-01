package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

/**
 * Represents a tube in a three-dimensional coordinate system.
 * A tube is defined by its radius and its central axis.
 */
public class Tube extends RadialGeometry {
	protected Ray axis; // The central axis of the tube.

	/**
	 * Constructs a tube with the specified radius and axis.
	 *
	 * @param radius The radius of the tube. Must be non-negative.
	 * @param axis   The axis of the tube.
	 * @throws IllegalArgumentException if the radius is negative.
	 */
	public Tube(double radius, Ray axis) throws IllegalArgumentException {
		super(radius);
		this.axis = axis;
	}

	@Override
	public Vector getNormal(Point point) throws IllegalArgumentException {
		// Calculate the vector from the axis head to the given point
		Vector headToPoint = point.subtract(axis.getHead());
		// Calculate the projection of the head-to-point vector onto the axis direction
		double t = headToPoint.dotProduct(axis.getDirection());
		if (!isZero(t)) {
			// Calculate the point on the axis closest to the given point
			Point o = axis.getPoint(t);
			if (o.equals(point)) {
				throw new IllegalArgumentException("Point cannot lie on the axis.");
			}
			// Calculate the vector from the closest point on the axis to the given point and normalize it
			return point.subtract(o).normalize();
		} else {
			// If t=0, the closest point on the axis is the axis head
			return point.subtract(axis.getHead()).normalize();
		}
	}

	@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
		return null;
	}
}
