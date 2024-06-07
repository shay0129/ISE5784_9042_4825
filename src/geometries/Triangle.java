package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

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
     *
     * @param vertices The vertices of the triangle.
     * @throws IllegalArgumentException if the number of vertices is not 3.
     */
    public Triangle(Point... vertices) {
        super(vertices);
    }

}