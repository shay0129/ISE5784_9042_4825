package primitives;

/**
 * The Vector class represents a vector in 3-dimensional space.
 * It extends the {@link Point} class, where a vector is essentially a point with an origin at (0,0,0).
 * A zero vector is not allowed.
 *
 * @author Shay and Asaf
 */
public class Vector extends Point {

    /**
     * Constructs a Vector with the specified coordinates.
     *
     * @param xyz1 the coordinates of the vector
     * @throws IllegalArgumentException if the vector is a zero vector (i.e., has all coordinates as zero)
     */
    public Vector(Double3 xyz1) {
        super(xyz1);
        if (xyz1.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Zero vector is not allowed");
        }
    }

    /**
     * Constructs a Vector with the specified coordinates.
     *
     * @param x the x-coordinate of the vector
     * @param y the y-coordinate of the vector
     * @param z the z-coordinate of the vector
     * @throws IllegalArgumentException if the vector is a zero vector (i.e., all coordinates are zero)
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Zero vector is not allowed");
        }
    }

    /**
     * Adds this vector to another vector, returning a new vector that is the sum of the two vectors.
     *
     * @param v the vector to add to this vector
     * @return a new vector representing the sum of this vector and the given vector
     */
    public Vector add(Vector v) {
        return new Vector(xyz.add(v.xyz));
    }

    /**
     * Scales this vector by a given scalar value, returning a new vector that is the result of the scaling.
     *
     * @param num the scalar value to scale the vector by
     * @return a new vector representing the scaled vector
     */
    public Vector scale(double num) {
        return new Vector(this.xyz.scale(num));
    }

    /**
     * Computes the dot product between this vector and another vector.
     *
     * @param other the other vector
     * @return the dot product of this vector and the given vector
     */
    public double dotProduct(Vector other) {
        return this.xyz.d1 * other.xyz.d1 + this.xyz.d2 * other.xyz.d2 + this.xyz.d3 * other.xyz.d3;
    }

    /**
     * Computes the cross product between this vector and another vector, returning a new vector that is
     * perpendicular to both.
     *
     * @param other the other vector
     * @return a new vector that is the cross product of this vector and the given vector
     */

    public Vector crossProduct(Vector other) {
        return new Vector(other.xyz.d2 * this.xyz.d3 - other.xyz.d3 * this.xyz.d2, //
                -(other.xyz.d1 * this.xyz.d3 - other.xyz.d3 * this.xyz.d1),
                other.xyz.d1 * this.xyz.d2 - other.xyz.d2 * this.xyz.d1);
    }

    /***
     *
     * @return length squared
     */
    public double lengthSquared() {
        return this.dotProduct(this);
    }

    /**
     *length - calls lengthsquared
     * @return the length
     */
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    /***
     *
     * @return the vector normalized
     */
    public Vector normalize() {
        // return new Vector(xyz.d1 / length(), xyz.d2 / length(), xyz.d3 / length());
        return new Vector(xyz.reduce(this.length()));
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj) return true;
        return (obj instanceof Vector other) && this.xyz.equals(other.xyz);
    }

    @Override
    public String toString() {return "->" + super.toString();
    }
}