package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * The Geometry interface represents a geometric object that can be intersected by a ray
 * and provides methods to interact with the object's geometry.
 * This interface extends the {@link Intersectable} interface.
 *
 * @see Intersectable
 * @see Point
 * @see Vector
 *
 * @author Shay and Asaf
 */
public interface Geometry extends Intersectable {

    /**
     * Computes the normal vector to this geometry at the given point.
     *
     * @param point the point on the geometry where the normal vector is computed
     * @return the normal vector to the geometry at the given point
     */
    public Vector getNormal(Point point);
}
