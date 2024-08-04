package geometries;

/**
 * Abstract class RadialGeometry implements the Geometry interface and
 * represents a geometric body with a radius.
 *
 * @author Shay and Asaf
 */
public abstract class RadialGeometry extends Geometry {

	/**
	 * Radius of the geometric body.
	 */
	protected final double radius;

	/**
	 * Radius squared of the geometric body.
	 */
	protected final double radiusSquared;

	/**
	 * Constructs a RadialGeometry object with the given radius.
	 *
	 * @param radius the radius of the geometric body
	 */
	public RadialGeometry(double radius) {
		if (radius <= 0) {
			throw new IllegalArgumentException("Radius must be positive");
		}
		this.radius = radius;
		this.radiusSquared = radius * radius;
	}
}
