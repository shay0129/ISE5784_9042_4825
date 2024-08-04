package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.*;

/**
 * Represents a tube in a three-dimensional (3D) space.
 * A tube is defined by its radius and an axis - a Ray.
 *
 * @autor Shay and Asaf
 */
public class Tube extends RadialGeometry {
	/** Axis ray of the tube */
	protected final Ray axisRay;

	/**
	 * Constructs a Tube object with the given radius and axis ray.
	 *
	 * @param radius  the radius of the tube
	 * @param axisRay the axis ray of the tube
	 */
	public Tube(double radius, Ray axisRay) {
		super(radius);
		this.axisRay = axisRay;
	}

	@Override
	public Vector getNormal(Point point) {
		// Calculate the projection of the point on the axis ray
		double t = axisRay.getDirection().dotProduct(point.subtract(axisRay.getHead()));
		Point o = axisRay.getPoint(t);

		return point.subtract(o).normalize(); // Return the normalized vector from the point to the projection
	}

	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		// Auto-generated method stub
		return null;
	}

	/**
	 *
	 */
	public Ray getAxisRay() {
		return axisRay;
	}

}
