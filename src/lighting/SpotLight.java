package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * A class representing a spotlight source. Inherits from PointLight and
 * implements LightSource.
 *
 * @autor Shay and Asaf
 */
public class SpotLight extends PointLight {

	private final Vector direction;

	/**
	 * Constructs a spotlight with the specified intensity, position, and direction.
	 *
	 * @param intensity The color intensity of the spotlight.
	 * @param position  The position of the spotlight in 3D space.
	 * @param direction The direction vector indicating the direction in which the
	 *                  spotlight is pointing. This vector will be normalized
	 *                  internally.
	 */
	public SpotLight(Color intensity, Point position, Vector direction) {
		super(intensity, position);
		this.direction = direction.normalize();
	}

	@Override
	public Color getIntensity(Point p) {
		Color pointIntensity = super.getIntensity(p);
		double projection = direction.dotProduct(getL(p));
		return pointIntensity.scale(Math.max(0, projection));
	}

	@Override
	public Vector getL(Point p) {
		return super.getL(p); // Returns the direction from the point to the light source
	}

}
