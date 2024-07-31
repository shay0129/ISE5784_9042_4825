package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * Geometries class to represent a collection of geometric shapes.
 * Implements the Composite design pattern.
 * This class allows for managing and intersecting multiple geometric shapes as a single entity.
 *
 * @see Intersectable
 * @see Point
 * @see Ray
 *
 * @author Shay and Asaf
 */
public class Geometries implements Intersectable {
    private final List<Intersectable> geometries;

    /**
     * Default constructor for creating an empty Geometries object.
     */
    public Geometries() {
        geometries = new LinkedList<>();
    }

    /**
     * Constructor for creating a Geometries object with specified geometries.
     *
     * @param geometries The intersectable geometries to add to this collection.
     */
    public Geometries(Intersectable... geometries) {
        this.geometries = new LinkedList<>(List.of(geometries));
    }

    /**
     * Adds one or more intersectable geometries to the collection.
     *
     * @param geometries The intersectable geometries to add.
     */
    public void add(Intersectable... geometries) {
        for (Intersectable geometry : geometries) {
            this.geometries.add(geometry);
        }
    }
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> intersections = new LinkedList<>();
        for (Intersectable geometry : geometries) {
            List<Point> geometryIntersections = geometry.findIntersections(ray);
            if (geometryIntersections != null) {
                intersections.addAll(geometryIntersections);
            }
        }
        return intersections.isEmpty() ? null : intersections;
    }


}
