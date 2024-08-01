package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * A class representing a point light source. Inherits from Light and implements
 * LightSource.
 */
public class PointLight extends Light implements LightSource {

	/**
	 * The position of the light source in 3D space.
	 */
	private Point position;
	/**
	 * Constant attenuation factor (kC) affecting the intensity of the light source.
	 * This factor is independent of the distance from the light source.
	 */
	private double kC = 1; // Constant attenuation
	/**
	 * Linear attenuation factor (kL) affecting the intensity of the light source.
	 * This factor decreases the intensity linearly with the distance from the light
	 * source.
	 */
	private double kL = 0; // Linear attenuation
	/**
	 * Quadratic attenuation factor (kQ) affecting the intensity of the light
	 * source. This factor decreases the intensity quadratically with the distance
	 * from the light source.
	 */
	private double kQ = 0; // Quadratic attenuation

	/**
	 * Constructs a point light with the given intensity and position.
	 *
	 * @param intensity The intensity (color) of the light source.
	 * @param position  The position of the light source.
	 */
	public PointLight(Color intensity, Point position) {
		super(intensity);
		this.position = position;
	}

	/**
	 * Sets the position of the point light source.
	 *
	 * @param position The new position of the point light source.
	 * @return This PointLight object, allowing method chaining.
	 */
	public PointLight setPosition(Point position) {
		this.position = position;
		return this;
	}

	/**
	 * Sets the constant attenuation factor (kC) for the point light source.
	 *
	 * @param kC The new value of the constant attenuation factor.
	 * @return This PointLight object with the updated constant attenuation factor
	 *         (kC), allowing method chaining.
	 */
	public PointLight setKC(double kC) {
		this.kC = kC;
		return this;
	}

	/**
	 * Sets the linear attenuation factor (kL) for the point light source.
	 *
	 * @param kL The new value of the linear attenuation factor.
	 * @return This PointLight object with the updated linear attenuation factor
	 *         (kL), allowing method chaining.
	 */
	public PointLight setKL(double kL) {
		this.kL = kL;
		return this;
	}

	/**
	 * Sets the quadratic attenuation factor (kQ) for the point light source.
	 *
	 * @param kQ The new value of the quadratic attenuation factor.
	 * @return This PointLight object with the updated quadratic attenuation factor
	 *         (kQ), allowing method chaining.
	 */
	public PointLight setKQ(double kQ) {
		this.kQ = kQ;
		return this;
	}

	@Override
	public Color getIntensity(Point p) {
		double d = position.distance(p);
		return getIntensity().scale(1d / (kC + kL * d + kQ * d * d));
	}

	@Override
	public Vector getL(Point p) {
		// if the point is the same as the light source, return null
		if (p.equals(position))
			return null;
		// otherwise, return the normalized vector from the light source to the point
		return p.subtract(position).normalize();
	}

}
