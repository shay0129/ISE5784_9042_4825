package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PointTests {

    Point p = new Point(1,1,1);
    @Test
    void testSubtract1() {
        assertEquals(new Vector(0,-1,4),p.subtract(new Point(1,2,-3)),"bachalom!!");
    }
    @Test
    void testSubtract2() {
        assertThrows(IllegalArgumentException.class, () -> p.subtract(p),"Vector(0,0,0) is not not legal");
    }
    @Test
    void testAdd() {
        assertEquals(new Point(2,3,4),p.add(new Vector(1,2,3)),
                "Wrong point add");
    }

    @Test
    void testDistanceSquared1() {
        assertEquals(14,p.distanceSquared(new Point(2,3,4)),"Wrong distance squared");
    }

    @Test
    void testDistanceSquared2() {
        assertEquals(0d,new Point(1,2,3).distanceSquared(new Point(1,2,3)),0.0001,
                "Wrong squared distance between the point and itself");
    }

    @Test
    void testDistance() {
        assertEquals(Math.sqrt(14),p.distance(new Point(2,3,4)), 0.0001,"Wrong distance");
        assertEquals(0d,new Point(1,2,3).distance(new Point(1,2,3)),0.0001,
                "Wrong distance between the point and itself");
    }

}