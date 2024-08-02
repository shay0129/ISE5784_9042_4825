package lighting;

import primitives.*;

/**
 * Represents a point light source that emits light from a specific point in all directions.
 * The intensity of the light decreases with the square of the distance from the light source.
 *
 * @autor Shay and Asaf
 */
public class PointLight extends Light implements LightSource {

	protected Point position;
	private double kC = 1.0;
	private double kL = 0.0;
	private double kQ = 0.0;

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
	 * Sets the constant attenuation factor.
	 *
	 * @param kC the constant attenuation factor
	 * @return the current PointLight object (for chaining method calls)
	 */
	public PointLight setKc(double kC) {
		this.kC = kC;
		return this;
	}

	/**
	 * Sets the linear attenuation factor.
	 *
	 * @param kL the linear attenuation factor
	 * @return the current PointLight object (for chaining method calls)
	 */
	public PointLight setKl(double kL) {
		this.kL = kL;
		return this;
	}

	/**
	 * Sets the quadratic attenuation factor.
	 *
	 * @param kQ the quadratic attenuation factor
	 * @return the current PointLight object (for chaining method calls)
	 */
	public PointLight setKq(double kQ) {
		this.kQ = kQ;
		return this;
	}

	@Override
	public double getDistance(Point point) {
		return position.distance(point);
	}

	@Override
	public Color getIntensity(Point p) {
		double d = position.distance(p);
		double denominator = kC + kL * d + kQ * (d * d);
		return intensity.scale(1 / denominator);
	}


	@Override
	public Vector getL(Point p) {
		try {
			return p.subtract(position).normalize();
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}
