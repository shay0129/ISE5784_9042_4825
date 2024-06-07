package geometries;

import primitives.Point;
import primitives.Vector;


public abstract class Geometry extends Intersectable{

    /**change
     *
     * declaration of getNormal method from a specific Point
     * to a specific Geometry object
     *
     * @param point Point from where to create a Normal vector to the geometry object
     * @return normal vector
     */
    public abstract Vector getNormal(Point p);

}
