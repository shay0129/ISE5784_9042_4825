package geometries;
import primitives.Point;
import primitives.Vector;

/**
 * Triangle class represents a geometric shape in 3-dimensional space.
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

    @Override
    public Vector getNormal(Point point) {
    // Implementation of getNormal for Triangle (returns null for now)
        return null;
    }
}
