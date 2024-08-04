package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;
import static primitives.Util.alignZero;

/**
 * Represents a spotlight, which is a type of light source that emits light in a specific direction.
 * The intensity of the light decreases with the cosine of the angle between the direction vector
 *
 * @autor Shay and Asaf
 */
public class SpotLight extends PointLight {

	private final Vector direction;
	private double narrowBeam = 1.0;

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

	@Override
	public Vector getL(Point p) {
		return super.getL(p); // Returns the direction from the point to the light source
	}

	@Override
	public SpotLight setKC(double kC) {
		return (SpotLight) super.setKC(kC);
	}

	@Override
	public SpotLight setKL(double kL) {
		return (SpotLight) super.setKL(kL);
	}

	@Override
	public SpotLight setKQ(double kQ) {
		return (SpotLight) super.setKQ(kQ);
	}

	@Override
	public Color getIntensity(Point point) {
		double cos = alignZero(direction.dotProduct(getL(point)));
		return cos <= 0 ? Color.BLACK //
				: super.getIntensity(point).scale(narrowBeam == 1 ? cos //
						: Math.pow(cos, narrowBeam));
	}
}
