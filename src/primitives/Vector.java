package primitives;
import primitives.Point;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Vector extends Point {
    public Vector(double x, double y, double z) {
        this(new Double3(x, y, z));
    }
    public Vector(Double3 xyz) {
        super(xyz);
        if (this.xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("vector cannot be zero");
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        return (o instanceof Vector v) && xyz.equals(v.xyz);
    }

    public String toString() {
        return "Vector{" +
                "xyz=" + xyz +
                '}';
    }

    public Vector add(Vector v) {
        return new Vector(xyz.add(v.xyz));
    }

    public Vector scale(double t) {
        if (isZero(t)) {
            throw new IllegalArgumentException("Cannot scale a zero vector");
        }
        return new Vector(xyz.scale(t));
    }

    public double dotProduct(Vector v) {
        return (v.xyz.d1 * xyz.d1 + v.xyz.d2 * xyz.d2 + v.xyz.d3 * xyz.d3);
    }

    public boolean isParallel(Vector v) {
        double ratio1 = this.xyz.d1 / v.xyz.d1;
        double ratio2 = this.xyz.d2 / v.xyz.d2;
        double ratio3 = this.xyz.d3 / v.xyz.d3;

        final double EPSILON = 0.0001;
        return Math.abs(ratio1 - ratio2) < EPSILON && Math.abs(ratio1 - ratio3) < EPSILON;
    }

    public Vector crossProduct(Vector v) {
        if (this.isParallel(v)) {
            throw new IllegalArgumentException("Cannot calculate cross product of parallel vectors");
        }

        return new Vector(new Double3(xyz.d2 * v.xyz.d3 - xyz.d3 * v.xyz.d2,
                xyz.d3 * v.xyz.d1 - xyz.d1 * v.xyz.d3,
                xyz.d1 * v.xyz.d2 - xyz.d2 * v.xyz.d1));
    }

    public double lengthSquared() {
        double dx = xyz.d1 * xyz.d1;
        double dy = xyz.d2 * xyz.d2;
        double dz = xyz.d3 * xyz.d3;
        return (dx + dy + dz);
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public Vector normalize() {
        double length = alignZero(length());
        if (isZero(length)) {
            throw new IllegalArgumentException("Cannot normalize a zero vector");
        }
        return new Vector(xyz.scale(1d/length));
    }
}