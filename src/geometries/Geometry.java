package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Geometry class is an abstract class that represents a basic geometric object.
 * It defines a common interface for all geometries, including a method to calculate the normal vector at a given point.
 * This class is intended to be extended by other specific geometric classes.
 *
 * @author Shay and Asaf
 */
public abstract class Geometry {

    /**
     * Calculates the normal vector to the geometry at a given point.
     * This method is abstract and must be implemented by subclasses.
     *
     * @param point The point on the geometry at which to calculate the normal vector.
     * @return The normal vector to the geometry at the specified point.
     */
    public abstract Vector getNormal(Point point);
}