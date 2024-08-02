package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * A class representing a point light source. Inherits from Light and implements
 * LightSource.
 *
 * @autor Shay and Asaf
 */
public class PointLight extends Light implements LightSource {

	private Point position;
	private double kC = 1.0; // Constant attenuation
	private double kL = 0.0; // Linear attenuation
	private double kQ = 0.0; // Quadratic attenuation

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
	 * Sets the linear attenuation factor (kL) for the point light source.
	 *
	 * @param kL The new value of the linear attenuation factor.
	 * @return This PointLight object with the updated linear attenuation factor
	 *         (kL), allowing method chaining.
	 */
	public PointLight setKl(double kL) {
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
	public PointLight setKq(double kQ) {
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

	@Override
	public double getDistance(Point point) {
		return position.distance(point);
	}
}
