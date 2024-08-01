package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Interface representing a light source in a scene. A light source has
 * properties to determine its intensity and direction.
 *
 * @autor Shay and Asaf
 */
public interface LightSource {

	/**
	 * Returns the intensity (color) of the light source at a given point in the
	 * scene.
	 *
	 * @param p The point in the scene.
	 * @return The intensity (color) of the light source at the given point.
	 */
	public Color getIntensity(Point p);

	/**
	 * Returns the direction vector from the light source to a given point in the
	 * scene. This vector represents the direction in which the light is shining.
	 *
	 * @param p The point in the scene.
	 * @return The direction vector from the light source to the given point.
	 */
	public Vector getL(Point p);
}
