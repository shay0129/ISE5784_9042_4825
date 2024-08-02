package geometries;

import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * The Geometries class represents a collection of geometric shapes that can be intersected by a ray.
 * It implements the Intersectable interface.
 *
 * @author Shay and Asaf
 */
public class Geometries extends Intersectable {

    /**
     * A list of intersectable geometric shapes.
     */
    private final List<Intersectable> geometries = new LinkedList<>();

    /**
     * Default constructor for an empty Geometries collection.
     */
    public Geometries() {}

    /**
     * Constructor that initializes the Geometries collection with given Intersectable objects.
     *
     * @param geometries Varargs of Intersectable objects to be added to the collection.
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * Adds one or more Intersectable objects to the Geometries collection.
     *
     * @param geometries Varargs of Intersectable objects to be added.
     */
    public void add(Intersectable... geometries) {
        for (Intersectable geo : geometries) {
            this.geometries.add(geo);
        }
    }
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        // Initialize a list to hold the intersection points
        List<GeoPoint> intersections = null;

        // Iterate through each geometry in the collection
        for (Intersectable geometry : geometries) {
            // Find intersections of the ray with the current geometry within the maximum distance
            List<GeoPoint> geometryIntersections = geometry.findGeoIntersections(ray, maxDistance);

            // If intersections are found, add them to the main intersections list
            if (geometryIntersections != null) {
                // Lazy initialization of the intersections list
                if (intersections == null) {
                    intersections = new LinkedList<>();
                }
                // Add all intersections of the current geometry to the main list
                intersections.addAll(geometryIntersections);
            }
        }
        // Return the list of all intersection points, or null if no intersections are found
        return intersections;
    }
}