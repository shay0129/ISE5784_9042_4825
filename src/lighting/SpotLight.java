package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Represents a spotlight, which is a point light source with a specific direction.
 * The intensity diminishes with distance and the beam's direction, creating a focused beam of light.
 * Useful for effects such as flashlights or stage lighting.
 *
 * @see PointLight
 * @see LightSource
 * @see Color
 * @see Point
 * @see Vector
 *
 * @author Shay and Asaf
 */
public class SpotLight extends PointLight {
	private Vector direction; // Direction vector of the spotlight
	private int beamWidth;    // Beam width or narrowness level

	/**
	 * Constructs a SpotLight with the specified intensity, position, and direction.
	 * The direction vector is normalized, and the default beam width is set to 1.
	 *
	 * @param intensity the color intensity of the light
	 * @param position  the position of the light in space
	 * @param direction the direction vector of the light
	 */
	public SpotLight(Color intensity, Point position, Vector direction) {
		super(intensity, position);
		this.direction = direction.normalize();
		this.beamWidth = 1;
	}

	/**
	 * Sets the narrowness of the spotlight beam.
	 *
	 * @param n the narrowness level to set
	 * @return this SpotLight instance for chaining
	 */
	public SpotLight setNarrowBeam(int n) {
		this.beamWidth = n;
		return this;
	}

	@Override
	public Color getIntensity(Point p) {
		double nlOr0 = 1.0;
		double dirL = Math.max(0, this.direction.dotProduct(super.getL(p)));
		for (int i = 0; i < beamWidth; ++i) {
			nlOr0 *= dirL;
		}
		return super.getIntensity(p).scale(nlOr0).scale(1 + (double) (beamWidth - 1) / 10);
	}
}
