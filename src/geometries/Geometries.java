package geometries;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import primitives.Ray;

/**
 * The Geometries class represents a collection of geometric shapes that can be intersected by a ray.
 * It implements the Intersectable interface.
 *
 * @author Shay and Asaf
 */
public class Geometries extends Intersectable {

	private final List<Intersectable> geometries = new LinkedList<>();

	/**
	 * Default constructor for creating an empty Geometries object.
	 */
	public Geometries() {	}

	/**
	 * Constructor for creating a Geometries object with specified geometries.
	 *
	 * @param geometries The intersectable geometries to add to this collection.
	 */
	public Geometries(Intersectable... geometries) {
		add(geometries);
	}

	/**
	 * Adds one or more intersectable geometries to the collection.
	 *
	 * @param geometries The intersectable geometries to add.
	 */
	public void add(Intersectable... geometries) {
		Collections.addAll(this.geometries, geometries);
	}

	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		List<GeoPoint> intersections = null;

		for (Intersectable geometry : this.geometries) {
			List<GeoPoint> geometryIntersections = geometry.findGeoIntersections(ray);
			if (geometryIntersections != null) {
				if (intersections == null)
					intersections = new LinkedList<>(geometryIntersections);
				else
					intersections.addAll(geometryIntersections);
			}
		}

		return intersections;
	}

}
