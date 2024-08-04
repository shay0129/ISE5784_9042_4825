package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Interface representing a light source in a scene. A light source has
 * properties to determine its intensity, direction, and distance from a point.
 *
 * @author Shay and Asaf
 */
public interface LightSource {

	/**
	 * Gets the intensity of the light source at a specific point.
	 *
	 * @param p the point at which to calculate the light intensity
	 * @return the color representing the intensity of the light at the given point
	 */
	public Color getIntensity(Point p);

	/**
	 * Gets the direction vector from the light source to a specific point.
	 *
	 * @param p the point to which the direction vector is calculated
	 * @return the vector representing the direction from the light source to the given point
	 */
	public Vector getL(Point p);

	/**
	 * Gets the distance between the light source and a specific point.
	 *
	 * @param point the point to which the distance is calculated
	 * @return the distance from the light source to the given point
	 */
	double getDistance(Point point);

}
