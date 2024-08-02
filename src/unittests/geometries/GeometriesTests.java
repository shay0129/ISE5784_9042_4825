package unittests.geometries;

import geometries.Geometries;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GeometriesTests {

    /**
     * Test method for {@link geometries.Geometries#findIntersections(Ray)}.
     * Tests the intersection finding method with various rays and geometries.
     */
    @Test
    void testFindIntersections() {
        Sphere sphere = new Sphere(new Point(0, 0, 1),1);
        Plane plane = new Plane(new Point(0, 0, 3), new Point(-5, 0, 0), new Point(0, -4, 0));
        Triangle triangle = new Triangle(
                new Point(0, 1, 0),
                new Point(0, 0, 2),
                new Point(2, 0, 0));

        Geometries geometries = new Geometries(sphere, plane, triangle);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray intersects some of the geometries (3 intersections expected)
        Ray ray = new Ray(new Point(0, 0, 4), new Vector(-1, 0, -4));
        assertEquals(3, geometries.findIntersections(ray).size(), "Ray intersects some of the geometries");

        // ============ Boundary Values Tests ==============

        // TC02: Geometry list is empty (no intersections expected)
        Geometries emptyGeometries = new Geometries();
        assertNull(emptyGeometries.findIntersections(ray), "Geometry list is empty");

        // TC03: Ray does not intersect any of the geometries (no intersections expected)
        Ray rayOut = new Ray(new Point(0, 0, 2.5), new Vector(3, 0, -2.5));
        assertNull(geometries.findIntersections(rayOut), "Ray does not intersect any of the geometries");

        // TC04: Ray intersects all the geometries (4 intersections expected)
        Sphere sphere2 = new Sphere(new Point(0, 0, 1),2);
        Plane plane2 = new Plane(new Point(-1, 0, 0), new Point(0, 0, 1), new Point(1, 0, 0));
        Triangle triangle2 = new Triangle(
                new Point(0, 0, 2),
                new Point(-2, 0, 0),
                new Point(2, 0, 0));

        Geometries geometries2 = new Geometries(sphere2, plane2, triangle2);
        Ray ray4 = new Ray(new Point(0, -6, 0),
                new Vector(-0.640267776470682, 3.87205743970451, 0.295760943381573));
        assertEquals(4, geometries2.findIntersections(ray4).size(), "Ray intersects all the geometries");

        // TC05: Ray intersects one of the geometries (1 intersection expected)
        Ray ray5 = new Ray(new Point(0, 1, 0),
                new Vector(0, 0.00814685061326, 2.727321605144383));
        assertEquals(1, geometries2.findIntersections(ray5).size(), "Ray intersects one of the geometries");
    }
}
