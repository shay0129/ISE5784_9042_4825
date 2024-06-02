package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    Triangle tr = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
    Plane pl = new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
    Ray ray;

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Triangle pl = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3),
                pl.getNormal(new Point(0, 0, 1)),
                "Bad normal to triangle");
    }


}