package primitives;

/**
 * The Point class represents a location in 3-dimensional space within the Cartesian coordinate system.
 * It is a fundamental class used in Euclidean geometry to describe positions in space.
 *
 * @author Shay and Asaf
 */
public class Point {
    /** Coordinate values of the point */
    protected final Double3 xyz;

    /** The point representing the origin (0,0,0) */
    public static final Point ZERO = new Point(0, 0, 0);

    /**
     * Constructs a Point object with the specified coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     * @param z the z-coordinate of the point
     */
    public Point(double x, double y, double z) {
        this.xyz = new Double3(x, y, z);
    }

    /**
     * Constructs a Point object using a {@link Double3} object to represent the coordinates.
     *
     * @param xyz1 a {@link Double3} object representing the coordinates of the point
     */
    public Point(Double3 xyz1) {
        this.xyz = xyz1;
    }

    /**
     * Computes the vector from this point to another point.
     *
     * @param other the point from which to subtract this point
     * @return a {@link Vector} representing the difference from the specified point to this point
     */
    public Vector subtract(Point other) {
        return new Vector(xyz.subtract(other.xyz));
    }

    /**
     * Adds a vector to this point, resulting in a new point.
     *
     * @param vec the vector to add to this point
     * @return a new {@link Point} that is the result of adding the vector to this point
     */
    public Point add(Vector vec) {
        return new Point(xyz.add(vec.xyz));
    }

    /**
     * Calculates the squared distance between this point and another point.
     *
     * @param p the other point
     * @return the squared distance between this point and the specified point
     */
    public double distanceSquared(Point p) {
        double deltaX = xyz.d1 - p.xyz.d1;
        double deltaY = xyz.d2 - p.xyz.d2;
        double deltaZ = xyz.d3 - p.xyz.d3;
        return deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ;
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param p the other point
     * @return the distance between this point and the specified point
     */
    public double distance(Point p) {
        return Math.sqrt(distanceSquared(p));
    }

    /**
     * Determines if this point is equal to another object.
     *
     * @param obj the object to compare with this point
     * @return {@code true} if the object is a {@link Point} and has the same coordinates as this point;
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        return (obj instanceof Point other) && this.xyz.equals(other.xyz);
    }

    /**
     * Returns a hash code value for this point.
     *
     * @return a hash code value for this point
     */
    @Override
    public int hashCode() {
        return xyz.hashCode();
    }

    /**
     * Returns a string representation of this point.
     *
     * @return a string representing the point, in the format "P{coordinates}"
     */
    @Override
    public String toString() {
        return "P" + xyz;
    }
}
