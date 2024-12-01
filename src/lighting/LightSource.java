package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Defines the common behaviors that all light sources must implement.
 * Includes methods for obtaining light intensity at a specific point,
 * the direction vector of the light relative to a point, and the distance
 * to a given point.
 */
public interface LightSource {
	/**
	 * Gets the intensity of the light at a specified point.
	 *
	 * @param p the point at which the light intensity is calculated
	 * @return the color intensity of the light at the specified point
	 */
	Color getIntensity(Point p);

	/**
	 * Gets the direction vector of the light from the source to a specified point.
	 *
	 * @param p the point at which the direction is calculated
	 * @return the direction vector of the light at the specified point
	 */
	Vector getL(Point p);

	/**
	 * Computes the distance from the light source to the specified point.
	 *
	 * @param point the point to compute the distance to
	 * @return the distance from the light source to the specified point
	 */
	double getDistance(Point point);
}
