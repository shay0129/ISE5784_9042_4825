package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTests {
    /**
     * Test method for {@link primitives.Vector#normalize()}.
     */
    @Test
    public void testNormalize() {
        Vector v = new Vector(0,3,4);
        Vector n = v.normalize();
        // ============ Equivalence Partitions Tests ============
        // TC01: Simple test
        assertEquals(1d, n.lengthSquared(), 0.00001, "wrong normalized vector length");
        assertThrows( IllegalArgumentException.class,
                () -> v.crossProduct(n) ,
                "normalized vector is not in the same direction");
        assertEquals( new Vector(0,0.6,0.8), n, "wrong normalized vector");
    }
    @Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);
// ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);
// TC01: Test that length of cross-product is proper (orthogonal vectors taken
// for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");
// TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");
// =============== Boundary Values Tests ==================
// TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");
    }
    @Test
    public void testLengthSquared(){
        assertEquals(14,(new Vector(1, 2, 3)).lengthSquared(),"wrong length Squared");
    }
    @Test
    public void testLength(){
        assertEquals(Math.sqrt(14),(new Vector(1, 2, 3)).length(),"wrong length");
    }
    @Test
    public void testAdd(){
        assertEquals(new Vector(3,3,3),new Vector(1,1,1).add(new Vector(2,2,2)),
                "wrong vector add");
    }
    @Test
    void testSubtract1() {
        assertEquals(new Vector(0,-1,4),new Vector(1,1,1).subtract(new Vector(1,2,-3)),"bachalom!!");
    }
    @Test
    void testSubtract2() {
        Vector v1= new Vector(1,1,1);
        assertThrows(IllegalArgumentException.class, () -> v1.subtract(v1),"Vector(0,0,0) is not not legal");
    }
    @Test
    void testDotProduct(){
        Vector v1= new Vector(1,1,1);
        Vector v2=new Vector(2,3,4);
        assertEquals(9,v1.dotProduct(v2),"dotProduct() wrong result length ");
    }

}