package primitives;

/**
 * The Point class represents a location in 3-dimensional space within the Cartesian coordinate system.
 * It is a fundamental class used in Euclidean geometry to describe positions in space.
 * Points are immutable and can be used to perform geometric operations such as vector calculations.
 *
 * @author Shay and Asaf
 */
public class Point {
	/** Coordinate values of the point */
	protected final Double3 xyz;

	/** Zero triad (0,0,0) */
	public static final Point ZERO = new Point(0, 0, 0);

	/**
	 * Constructor that accepts three double values representing the coordinates of
	 * the point
	 * 
	 * @param x coordinate value
	 * @param y coordinate value
	 * @param z coordinate value
	 */
	public Point(double x, double y, double z) {
		this.xyz = new Double3(x, y, z);
	}

	/**
	 * Constructor that accepts a Double3 object representing the coordinates of the
	 * point
	 * 
	 * @param xyz1 representing the coordinates
	 */
	public Point(Double3 xyz1) {
		this.xyz = xyz1;
	}

	/**
	 * Subtract operation between two points, returning a vector from the second
	 * point to the first
	 * 
	 * @param other The other point
	 * @return Vector from 'other' to 'this' point
	 */
	public Vector subtract(Point other) {
		return new Vector(xyz.subtract(other.xyz));
	}

	/**
	 * Add a vector to the point, returning a new point
	 * 
	 * @param vec The vector to add
	 * @return New point after adding the vector
	 */
	public Point add(Vector vec) {
		return new Point(xyz.add(vec.xyz));
	}

	/**
	 * Calculate the squared distance between two points.
	 *
	 * @param p The other point.
	 * @return The squared distance between the two points.
	 */
	public double distanceSquared(Point p) {
		return Math.pow(xyz.d1 - p.xyz.d1, 2) + Math.pow(xyz.d2 - p.xyz.d2, 2) + Math.pow(xyz.d3 - p.xyz.d3, 2);
	}

	/**
	 * Calculate the distance between two points
	 * 
	 * @param p the other point
	 * @return The distance between the two points
	 */
	public double distance(Point p) {
		return (Math.sqrt(distanceSquared(p)));
	}

	/**
	 * Getter for the X coordinate.
	 *
	 * @return the X coordinate.
	 */
	public double getX() {
		return xyz.d1;
	}

	/**
	 * Getter for the Y coordinate.
	 *
	 * @return the Y coordinate.
	 */
	public double getY() {
		return xyz.d2;
	}

	/**
	 * Getter for the Z coordinate.
	 *
	 * @return the Z coordinate.
	 */
	public double getZ() {
		return xyz.d3;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		return (obj instanceof Point other) && this.xyz.equals(other.xyz);
	}

	@Override
	public int hashCode() {
		return xyz.hashCode();
	}

	@Override
	public String toString() {
		return "P" + xyz;
	}

}
