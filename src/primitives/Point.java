package primitives;

public class Point {

    // Assuming a custom Double3 class exists to represent 3D coordinates
     final Double3 xyz;

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
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return xyz.equals(point.xyz);
    }

    @Override
    public int hashCode() {
        return xyz.hashCode();
    }

    @Override
    public String toString() {
        return "Point (" + xyz.getX() + ", " + xyz.getY() + ", " + xyz.getZ() + ")";
    }

    public double distanceSquared(Point point) {
        double dx = point.x - this.x;
        double dy = point.y - this.y;
        double dz = point.z - this.z;
        return dx * dx + dy * dy + dz * dz;
    }

    public double distance(Point point) {
        return Math.sqrt(distanceSquared(point));
    }

    public Point add(Vector vector) {
        return new Point(x + vector.head.x, y + vector.head.y, z + vector.head.z);
    }
}

