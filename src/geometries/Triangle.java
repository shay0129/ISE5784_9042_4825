package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Triangle class represents a geometric shape in 3-dimensional space.
 * It extends the `Polygon` class and adds specific methods related to triangles.
 * The triangle is defined by three vertices.
 *
 * @author Shay and Asaf
 */
public class Triangle extends Polygon {

    /**
     * Constructs a triangle with the given vertices.
     * @param vertices The vertices of the triangle.
     * @throws IllegalArgumentException if the number of vertices is not 3.
     */
    public Triangle(Point... vertices){
        super(vertices);
    }

    /**
     * Calculates the normal vector to the triangle at a given point.
     * @param point The point on the triangle at which to calculate the normal vector.
     * @return The normal vector to the triangle at the specified point.
     */
    @Override
    public Vector getNormal(Point point) {
        // Implementation of getNormal for Triangle (returns null for now)
        return null;
    }
}