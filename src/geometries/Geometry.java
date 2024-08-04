package geometries;

import primitives.Point;
import primitives.Vector;
import primitives.Color;
import primitives.Material;

/**
 * Abstract class Geometry represents any geometric body.
 * It defines common properties and methods for all geometric shapes.
 *
 * @autor Shay and Asaf
 */
public abstract class Geometry extends Intersectable {

	/**
	 * Field representing the emission color of the geometry.
	 */
	protected Color emission = Color.BLACK;
	private Material material = new Material();

	/**
	 * Method that returns the emission color of the geometry.
	 *
	 * @return the emission color
	 */
	public Color getEmission() {
		return emission;
	}

	/**
	 * Method that returns the material of the geometry.
	 *
	 * @return the material of the geometry
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * Method that returns the normal vector to the surface body at a given point.
	 * This method must be implemented by subclasses.
	 *
	 * @param point the point on the body's surface
	 * @return a vector
	 */
	public abstract Vector getNormal(Point point);

	/**
	 * Method to update the emission color of the geometry.
	 *
	 * @param emission the new emission color to set
	 * @return the updated Geometry object
	 */
	public Geometry setEmission(Color emission) {
		if (emission == null)
			throw new IllegalArgumentException("Emission cannot be null");
		this.emission = emission;
		return this;
	}

	/**
	 * Method to update the material of the geometry.
	 *
	 * @param material the new material to set
	 * @return the updated Geometry object
	 */
	public Geometry setMaterial(Material material) {
		if (material == null)
			throw new IllegalArgumentException("Material cannot be null");
		this.material = material;
		return this;
	}
}
