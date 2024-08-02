package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Represents a spotlight, which is a type of light source that emits light in a specific direction.
 * The intensity of the light decreases with the cosine of the angle between the direction vector
 *
 * @autor Shay and Asaf
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
	@Override
	public SpotLight setKc(double kC) {
		return (SpotLight) super.setKc(kC);
	}

	@Override
	public SpotLight setKl(double kL) {
		return (SpotLight) super.setKl(kL);
	}

	@Override
	public SpotLight setKq(double kQ) {
		return (SpotLight) super.setKq(kQ);
	}

	@Override
	public Color getIntensity(Point p) {
		double dirDotL = direction.dotProduct(getL(p)); // Dot product of direction and light direction to point
		return super.getIntensity(p).scale(Math.max(0, dirDotL)); // Scale intensity by maximum of 0 and dot product
	}

	@Override
	public Vector getL(Point p) {
		return super.getL(p); // Delegate to the PointLight implementation
	}

}
