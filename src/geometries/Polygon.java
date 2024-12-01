package geometries;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.isZero;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Represents a two-dimensional polygon in a 3D Cartesian coordinate system.
 */
public class Polygon extends Geometry {
	/**
	 * List of the polygon's vertices.
	 */
	protected final List<Point> vertices;

	/**
	 * Plane in which the polygon lies.
	 */
	protected final Plane plane;

	/**
	 * Number of vertices in the polygon.
	 */
	private final int size;

	/**
	 * Constructs a polygon using a list of vertices. The vertices must be ordered along the edge path,
	 * and the polygon must be convex.
	 *
	 * @param vertices List of vertices ordered by edge path.
	 * @throws IllegalArgumentException If:
	 *                                  <ul>
	 *                                  <li>There are less than 3 vertices.</li>
	 *                                  <li>Consecutive vertices are the same point.</li>
	 *                                  <li>The vertices are not coplanar.</li>
	 *                                  <li>The order of vertices does not match the edge path.</li>
	 *                                  <li>The polygon is concave.</li>
	 *                                  </ul>
	 */
	public Polygon(Point... vertices) {
		if (vertices.length < 3) {
			throw new IllegalArgumentException("A polygon must have at least 3 vertices.");
		}
		this.vertices = List.of(vertices);
		this.size = vertices.length;

		// Create the plane based on the first three vertices
		plane = new Plane(vertices[0], vertices[1], vertices[2]);
		if (size == 3) return; // No further checks needed for a triangle

		Vector n = plane.getNormal();
		Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
		Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

		boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
		for (int i = 1; i < vertices.length; i++) {
			if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n))) {
				throw new IllegalArgumentException("All vertices must lie in the same plane.");
			}
			edge1 = edge2;
			edge2 = vertices[i].subtract(vertices[i - 1]);
			if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0)) {
				throw new IllegalArgumentException("Vertices must be ordered, and the polygon must be convex.");
			}
		}
	}

	/**
	 * Constructs a polygon using a list of vertices. The vertices must be ordered along the edge path,
	 * and the polygon must be convex.
	 *
	 * @param vertices List of vertices ordered by edge path.
	 * @throws IllegalArgumentException If the vertices do not form a valid polygon.
	 */
	public Polygon(List<Point> vertices) {
		this(vertices.toArray(new Point[0]));
	}

	@Override
	public Vector getNormal(Point point) {
		return plane.getNormal();
	}

	@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
		var list = plane.findIntersections(ray, maxDistance);
		if (list == null) return null;

		List<Vector> vs = new ArrayList<>(size);
		for (Point vertex : vertices) {
			vs.add(vertex.subtract(ray.getHead()));
		}

		List<Double> ts = new ArrayList<>(size);
		List<Vector> ns = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			ns.add(vs.get(i).crossProduct(vs.get((i + 1) % size)));
		}

		for (Vector n : ns) {
			ts.add(n.dotProduct(ray.getDirection()));
		}

		int flag;
		if (isZero(ts.getFirst())) return null;
		flag = ts.getFirst() > 0 ? 1 : -1;

		for (Double t : ts) {
			if (isZero(t)) return null;
			if ((t > 0 && flag == -1) || (t < 0 && flag == 1)) return null;
		}

		return List.of(new GeoPoint(this, list.getFirst()));
	}
}
