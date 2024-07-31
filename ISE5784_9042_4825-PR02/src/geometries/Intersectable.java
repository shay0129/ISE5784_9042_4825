package geometries;

import java.util.List;
import primitives.Point;
import primitives.Ray;

/**
 * Intersectable is an interface that represents a geometric object that can be intersected by a ray.
 * Implementations of this interface should provide the logic to find intersections between the object and a given ray.
 *
 * @see Point
 * @see Ray
 *
 * @autor Shay and Asaf
 */
public interface Intersectable {

    /**
     * Finds intersection points between the intersectable object and a given ray.
     *
     * @param ray The ray to intersect with the object.
     * @return A list of intersection points between the object and the ray. If no
     *         intersections are found, an empty list is returned.
     */
    List<Point> findIntersections(Ray ray);
}
