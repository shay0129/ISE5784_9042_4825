package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

/**
 * Triangle class represents a geometric shape in 3-dimensional space.
 * It extends the `Polygon` class and adds specific methods related to triangles.
 * The triangle is defined by three vertices.
 *
 * @author Shay and Asaf
 */
public class Triangle extends Polygon {

    /**
     * Constructs a triangle with the given vertices.
     *
     * @param vertices The vertices of the triangle.
     * @throws IllegalArgumentException if the number of vertices is not 3.
     */
    public Triangle(Point... vertices) {
        super(vertices);
    }

    //exe3 change
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> planeIntersections = plane.findGeoIntersections(ray, maxDistance);
        if (planeIntersections == null)
            return null;

        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        Vector v1 = this.p0.subtract(p0);
        Vector v2 = this.p1.subtract(p0);
        Vector v3 = this.p2.subtract(p0);

        double s1 = v.dotProduct(v1.crossProduct(v2));
        if (isZero(s1))
            return null;
        double s2 = v.dotProduct(v2.crossProduct(v3));
        if (isZero(s2))
            return null;
        double s3 = v.dotProduct(v3.crossProduct(v1));
        if (isZero(s3))
            return null;

        if ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) {
            Point point = planeIntersections.get(0).point;
            return List.of(new GeoPoint(this, point));
        }
        return null;
    }

}