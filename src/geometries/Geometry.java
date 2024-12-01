package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * Represents a geometric object in a 3D Cartesian coordinate system.
 * Includes properties for material and emission color, and provides
 * an abstract method for calculating the normal vector at a given point.
 * This class extends the Intersectable class.
 */
public abstract class Geometry extends Intersectable {
	/**
	 * The emission color of the geometry.
	 */
	protected Color emission = Color.BLACK;

	/**
	 * The material properties of the geometry.
	 */
	private Material material = new Material();

	/**
	 * Sets the emission color of the geometry.
	 *
	 * @param emission the emission color to set
	 * @return the geometry instance for chaining
	 */
	public Geometry setEmission(Color emission) {
		this.emission = emission;
		return this;
	}

	/**
	 * Sets the material of the geometry.
	 *
	 * @param material the material to set
	 * @return the geometry instance for chaining
	 */
	public Geometry setMaterial(Material material) {
		this.material = material;
		return this;
	}

	/**
	 * Retrieves the emission color of the geometry.
	 *
	 * @return the emission color
	 */
	public Color getEmission() {
		return emission;
	}

	/**
	 * Retrieves the material of the geometry.
	 *
	 * @return the material
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * Calculates the normal vector to the geometry at the specified point.
	 *
	 * @param point the point on the surface of the geometry
	 * @return the normal vector at the given point
	 */
	public abstract Vector getNormal(Point point);
}
