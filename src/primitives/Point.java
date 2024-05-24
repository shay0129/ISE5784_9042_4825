package primitives;

/**
 * Point class is the fundamental class, represents a location in 3D space,
 * in the Cartesian coordinate system of Euclidean geometry.
 * @author Shay and Asaf
 */
public class Point {

    protected final Double3 xyz;

    /**
     * Constructor that creates a new point given its x, y, and z coordinates.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @param z The z-coordinate of the point.
     */
    public Point(double x, double y, double z) {
        this(new Double3(x, y, z)); // Delegate to the other ctor
    }

    /**
     * Constructor that creates a new point given a Double3 object.
     * @param xyz The Double3 object representing the coordinates of the point.
     */
    Point(Double3 xyz){
        this.xyz = xyz;
    }

    /**
     * Checks if this point is equal to another object.
     * @param o The object to compare with this point.
     * @return true if the object is a point and has the same coordinates, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point point)) return false;

        return xyz.equals(point.xyz);
    }

    /**
     * Returns a hash code value for the point.
     * @return A hash code value for this point.
     */
    @Override
    public int hashCode() {
        return xyz.hashCode();
    }

    /**
     * Returns a string representation of the point.
     * @return A string that describes the point.
     */
    @Override
    public String toString() {
        return "Point {" + xyz + '}';
    }

    /**
     * Calculates the squared distance between this point and another point.
     * @param point The other point.
     * @return The squared distance between the two points.
     */
    public double distanceSquared(Point point) {
        double dx = xyz.d1 - point.xyz.d1;
        double dy = xyz.d2 - point.xyz.d2;
        double dz = xyz.d3 - point.xyz.d3;
        return (dx * dx + dy * dy + dz * dz);
    }

    /**
     * Calculates the distance between this point and another point.
     * @param point The other point.
     * @return The distance between the two points.
     */
    public double distance(Point point) {
        return Math.sqrt(distanceSquared(point));
    }

    /**
     * Adds a vector to this point.
     * @param vector The vector to add to this point.
     * @return A new point that is the result of the addition.
     */
    public Point add(Vector vector) {
        return new Point(xyz.add(vector.xyz));
    }

    /**
     * Subtracts a point from this point to create a vector.
     * @param point The point to subtract from this point.
     * @return A new vector that is the result of the subtraction.
     */
    public Vector subtract(Point point) {
        return new Vector(xyz.subtract(point.xyz));
    }
}