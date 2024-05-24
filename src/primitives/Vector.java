package primitives;
/**
 * Vector class is the basic class, represents a mathematical vector
 * in the Cartesian 3-Dimensional coordinate system.
 * @author Shay and Asaf
 */
import primitives.Point;

public class Vector extends Point {
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("vector canoot be zero");
        }
    }
    /**
     * constructor for Vector
     * @param xyz
     */
    public Vector(Double3 xyz) {
        super(xyz);
        if (this.xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("vector canoot be zero");
        }
    }
    /**
     * @return the x coordinate of the point
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        return (o instanceof Vector v) && xyz.equals(v.xyz);
    }
    public String toString() {
        return "Vector{" +
                "xyz=" + xyz +
                '}';
    }
    /**
     * @return the x coordinate of the point
     */
    public Vector add(Vector v) {
        return new Vector(xyz.add(v.xyz));
    }
    /**
     * @return the x coordinate of the point
     */
    public Vector scale(double t) {
        return new Vector(xyz.scale(t));
    }
    /**
     * @return the x coordinate of the point
     */
    public double dotProduct(Vector v) {
        return (v.xyz.d1 * xyz.d1 + v.xyz.d2 * xyz.d2 + v.xyz.d3 * xyz.d3);
    }
    /**
     * @return the x coordinate of the point
     */
    public Vector crossProduct(Vector v) {
        return new Vector(xyz.d2 * v.xyz.d3 - xyz.d3 * v.xyz.d2,
                xyz.d3 * v.xyz.d1 - xyz.d1 * v.xyz.d3,
                xyz.d1 * v.xyz.d2 - xyz.d2 * v.xyz.d1);
    }
    /**
     * @return the x coordinate of the point
     */
    public double lengthSquared() {
        double dx = xyz.d1 * xyz.d1;
        double dy = xyz.d2 * xyz.d2;
        double dz = xyz.d3 * xyz.d3;
        return (dx + dy + dz);
    }
    /**
     * @return the x coordinate of the point
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }
    /**
     * @return the x coordinate of the point
     */
    public Vector normalize() {
        return new Vector(xyz.reduce(length()));
    }
}
