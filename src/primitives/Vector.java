package primitives;

import static primitives.Util.alignZero;
public class Vector extends Point{
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (xyz.equals(Double3.ZERO))
            throw new ArithmeticException("Vector(0,0,0) is not valid");
    }

    public Vactor(Double3 xyz){
        this.xyz = xyz;
        if (xyz.equals(Double3.ZERO))
            throw new ArithmeticException("Vector(0,0,0) is not valid");
    }

    @Override
    public String toString() {
        return "Vector (" + xyz + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector vector)) return false;
        return xyz.equals(vector.xyz);
    }

    public Vector add(Vector vector) {
        return new Vector(xyz.add(vector.xyz));
    }

    public double scale(double scalar) {
        return new Vector(xyz.d1 * scalar, xyz.d2 * scalar, xyz.d3 * scalar);
    }

    public double lengthSquared() {
        return xyz.d1xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3;
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public Vector normalize() {
        double length = length();
        if (length == 0) {
            throw new ArithmeticException("Cannot normalize Zero Vector");
        }
        return new Vector(head.d1 / length, head.d2 / length, head.d3 / length);
    }
}
