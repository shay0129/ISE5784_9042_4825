package primitives;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Vector class represents a vector in 3-dimensional space.
 * @author Shay and Asaf
 */
public class Vector extends Point {
    /**
     * Constructor that creates a new vector given its x, y, and z coordinates.
     * @param x The x-coordinate of the vector.
     * @param y The y-coordinate of the vector.
     * @param z The z-coordinate of the vector.
     */
    public Vector(double x, double y, double z) {
        this(new Double3(x, y, z));
    }

    /**
     * Constructor that creates a new vector given a Double3 object.
     * @param xyz The Double3 object representing the coordinates of the vector.
     */
    public Vector(Double3 xyz) {
        super(xyz);
        if (this.xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("vector cannot be zero");
        }
    }

    /**
     * Checks if this vector is equal to another object.
     * @param o The object to compare with this vector.
     * @return true if the object is a vector and has the same coordinates, false otherwise.
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        return (o instanceof Vector v) && xyz.equals(v.xyz);
    }

    /**
     * Returns a string representation of the vector.
     * @return A string that describes the vector.
     */
    public String toString() {
        return "Vector{" +
                "xyz=" + xyz +
                '}';
    }

    /**
     * Adds this vector to another vector.
     * @param v The vector to add to this vector.
     * @return A new vector that is the result of the addition.
     */
    public Vector add(Vector v) {
        return new Vector(xyz.add(v.xyz));
    }

    /**
     * Scales this vector by a scalar.
     * @param t The scalar to scale the vector by.
     * @return A new vector that is the result of the scaling.
     */
    public Vector scale(double t) {
        if (isZero(t)) {
            throw new IllegalArgumentException("Cannot scale a zero vector");
        }
        return new Vector(xyz.scale(t));
    }

    /**
     * Calculates the dot product of this vector and another vector.
     * @param v The vector to calculate the dot product with.
     * @return The dot product of the two vectors.
     */
    public double dotProduct(Vector v) {
        return (v.xyz.d1 * xyz.d1 + v.xyz.d2 * xyz.d2 + v.xyz.d3 * xyz.d3);
    }

    /**
     * Checks if this vector is parallel to another vector.
     * @param v The vector to check for parallelism.
     * @return true if the vectors are parallel, false otherwise.
     */
    public boolean isParallel(Vector v) {
        double ratio1 = this.xyz.d1 / v.xyz.d1;
        double ratio2 = this.xyz.d2 / v.xyz.d2;
        double ratio3 = this.xyz.d3 / v.xyz.d3;

        final double EPSILON = 0.0001;
        return Math.abs(ratio1 - ratio2) < EPSILON && Math.abs(ratio1 - ratio3) < EPSILON;
    }

    /**
     * Calculates the cross product of this vector and another vector.
     * @param v The vector to calculate the cross product with.
     * @return A new vector that is the cross product of the two vectors.
     */
    public Vector crossProduct(Vector v) {
        if (this.isParallel(v)) {
            throw new IllegalArgumentException("Cannot calculate cross product of parallel vectors");
        }

        return new Vector(new Double3(xyz.d2 * v.xyz.d3 - xyz.d3 * v.xyz.d2,
                xyz.d3 * v.xyz.d1 - xyz.d1 * v.xyz.d3,
                xyz.d1 * v.xyz.d2 - xyz.d2 * v.xyz.d1));
    }

    /**
     * Calculates the squared length of this vector.
     * @return The squared length of the vector.
     */
    public double lengthSquared() {
        double dx = xyz.d1 * xyz.d1;
        double dy = xyz.d2 * xyz.d2;
        double dz = xyz.d3 * xyz.d3;
        return (dx + dy + dz);
    }

    /**
     * Calculates the length of this vector.
     * @return The length of the vector.
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Normalizes this vector.
     * @return A new vector that is the normalized version of this vector.
     */
    public Vector normalize() {
        double length = alignZero(length());
        if (isZero(length)) {
            throw new IllegalArgumentException("Cannot normalize a zero vector");
        }
        return new Vector(xyz.scale(1d/length));
    }
}