package primitives;

public class Point {

    // Assuming a custom Double3 class exists to represent 3D coordinates
    private final Double3 xyz;

    // Ctor
    public Point(double x, double y, double z) {
        this.xyz = new Double3(x, y, z);
    }
    public Point(Double3 xyz){
        this.xyz = xyz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point point)) return false;

        return xyz.equals(point.xyz);
    }

    @Override
    public int hashCode() {
        return xyz.hashCode();
    }

    @Override
    public String toString() {
        return "Point {" + xyz + '}';
    }

    public double distanceSquared(Point point) {
        double dx = xyz.d1 - point.xyz.d1;
        double dy = xyz.d2 - point.xyz.d2;
        double dz = xyz.d3 - point.xyz.d3;
        return (dx * dx + dy * dy + dz * dz);
    }

    public double distance(Point point) {
        return Math.sqrt(distanceSquared(point));
    }

    public Point add(Vector vector) {
        return new Point(xyz.add(vector.xyz));
    }

    public Vector subtract(Point point) {
        return new Vector(xyz.subtract(point.xyz));
    }
}

