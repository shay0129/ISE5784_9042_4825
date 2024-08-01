package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Represents a geometric tube in 3-dimensional space, defined by an axis and a radius.
 * The tube extends the {@link RadialGeometry} class and includes methods specific to tubes.
 * It is characterized by a central axis (defined by a ray) and a radius.
 *
 * @author Shay and Asaf
 */
public class Tube extends RadialGeometry {
    /** Axis ray of the tube */
    protected final Ray axisRay;

    /**
     * Constructs a Tube object with the specified radius and axis ray.
     *
     * @param radius  the radius of the tube
     * @param axisRay the axis ray that defines the central axis of the tube
     */
    public Tube(double radius, Ray axisRay) {
        super(radius);
        this.axisRay = axisRay;
    }

    @Override
    public Vector getNormal(Point point) {
        // Compute the projection of the point onto the axis of the tube
        double t = axisRay.getDirection().dotProduct(point.subtract(axisRay.getHead()));

        // Find the point on the axis closest to the given point
        Point o = axisRay.getPoint(t);

        // Calculate and return the normal vector by subtracting the axis point from the surface point
        return point.subtract(o).normalize();
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        // Auto-generated method stub
        return null;
    }
}
