package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Represents a tube in a three-dimensional (3D) space.
 * A tube is defined by its radius and an axis - a Ray.
 */
public class Tube extends RadialGeometry {

    /** The axis of the tube. */
    final protected Ray axis;

    /**
     * Constructs a new Tube with the specified radius and axis.
     *
     * @param radius The radius of the tube.
     * @param axis   The axis of the tube.
     */
    public Tube(double radius, Ray axis) {
        super(radius);
        this.axis = axis;
    }


    @Override
    public Vector getNormal(Point point) {

        double t = point.subtract(axis.getPoint(0)).dotProduct(axis.getDirection());
        return point.subtract(axis.getPoint(t)).normalize();
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        return null;
    }
}
