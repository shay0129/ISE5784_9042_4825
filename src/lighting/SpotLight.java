package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static primitives.Util.alignZero;

/**
 * Represents a spotlight, which is a type of light source that emits light in a specific direction.
 */
public class SpotLight extends PointLight {

	private Vector direction;
	private double narrowBeam = 1;

	/**
	 * Constructs a SpotLight object with the specified color intensity, position, and direction.
	 *
	 * @param intensity the color intensity of the light
	 * @param position the position of the light source
	 * @param direction the direction in which the light is emitted
	 */
	public SpotLight(Color intensity, Point position, Vector direction) {
		super(intensity, position);
		this.direction = direction.normalize();
	}

	/**
	 * Sets the narrow beam factor of the spotlight.
	 *
	 * @param narrow the narrow beam factor
	 * @return the current SpotLight object (for method chaining)
	 */
	public SpotLight setNarrowBeam(double narrow) {
		narrowBeam = narrow;
		return this;
	}

	/**
	 * Sets the constant attenuation factor of the spotlight.
	 *
	 * @param kC the constant attenuation factor
	 * @return the current SpotLight object (for method chaining)
	 */
	public SpotLight setKc(double kC) {
		return (SpotLight) super.setKc(kC);
	}

	/**
	 * Sets the linear attenuation factor of the spotlight.
	 *
	 * @param kL the linear attenuation factor
	 * @return the current SpotLight object (for method chaining)
	 */
	public SpotLight setKl(double kL) {
		return (SpotLight) super.setKl(kL);
	}

	/**
	 * Sets the quadratic attenuation factor of the spotlight.
	 *
	 * @param kQ the quadratic attenuation factor
	 * @return the current SpotLight object (for method chaining)
	 */
	public SpotLight setKq(double kQ) {
		return (SpotLight) super.setKq(kQ);
	}

	/**
	 * Calculates the intensity of the light at a given point.
	 *
	 * @param p the point at which to calculate the light intensity
	 * @return the color intensity at the specified point
	 */
	public Color getIntensity(Point p) {
		// Calculate the direction vector from the light source to the point
		Vector l = getL(p);

		// If the direction vector is null, use the spotlight's direction
		if (l == null)
			l = direction;

		// Calculate the cosine of the angle between the direction vector and the spotlight's direction
		double cos = alignZero(l.dotProduct(direction));

		// If the cosine is less than or equal to 0, return black color as the point is not illuminated
		if (cos <= 0) return Color.BLACK;

		// If the narrow beam factor is not 1, adjust the cosine value accordingly
		if (narrowBeam != 1)
			cos = alignZero(Math.pow(cos, narrowBeam));

		// Calculate the final color intensity at the point by scaling the base intensity with the adjusted cosine value
		return super.getIntensity(p).scale(cos);
	}

}
