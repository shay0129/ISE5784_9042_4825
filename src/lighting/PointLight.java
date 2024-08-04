package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Represents a point light source that emits light from a specific point in all directions.
 * The intensity of the light decreases with the square of the distance from the light source.
 *
 * @autor Shay and Asaf
 */
public class PointLight extends Light implements LightSource {

	private Point position;
	private double kC = 1.0; // Constant attenuation
	private double kL = 0.0; // Linear attenuation
	private double kQ = 0.0; // Quadratic attenuation

	/**
	 * Constructs a PointLight object with the specified color intensity and position.
	 *
	 * @param intensity the color intensity of the light
	 * @param position the position of the light source
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
	 * Sets the constant attenuation factor.
	 *
	 * @param kC the constant attenuation factor
	 * @return the current PointLight object (for chaining method calls)
	 */
	public PointLight setKC(double kC) {
		this.kC = kC;
		return this;
	}

	/**
	 * Sets the linear attenuation factor.
	 *
	 * @param kL the linear attenuation factor
	 * @return the current PointLight object (for chaining method calls)
	 */
	public PointLight setKL(double kL) {
		this.kL = kL;
		return this;
	}

	/**
	 * Sets the quadratic attenuation factor.
	 *
	 * @param kQ the quadratic attenuation factor
	 * @return the current PointLight object (for chaining method calls)
	 */
	public PointLight setKQ(double kQ) {
		this.kQ = kQ;
		return this;
	}

	@Override
	public Color getIntensity(Point p) {
		double d = position.distance(p);
		return intensity.scale(1d / (kC + kL * d + kQ * d * d));
	}

	@Override
	public Vector getL(Point p) {
		// if the point is the same as the light source, return null
		if (p.equals(position))// ??למחוק
			return null;
		// otherwise, return the normalized vector from the light source to the point
		return p.subtract(position).normalize();
	}

	@Override
	public double getDistance(Point point) {
		return point.distance(position);
	}

}
