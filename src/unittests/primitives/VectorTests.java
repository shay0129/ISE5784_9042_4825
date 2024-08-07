/**
 * 
 */
package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;

import primitives.Point;
import primitives.Vector;
import primitives.Double3;

import org.junit.jupiter.api.Test;

/**
 * Testing {@link Vector} Class
 *
 * @author Shay and Asaf
 */
class VectorTests {

	/** A small constant for floating-point comparison precision. */
	private static final double DELTA = 0.00001;

	/**
	 * Test method for {@link primitives.Vector#Vector(primitives.Double3)}. This
	 * test checks the constructor that accepts the coordinate values. It verifies
	 * correct vector creation and ensures that zero vectors throw an exception.
	 */
	@Test
	void testVectorConstructorWithDouble3() {
		// ============Equivalence Partitions Tests ==============

		// Test case setup
		Double3 double3 = new Double3(1.0, 2.0, 3.0);

		// Create the vector
		Vector vector = new Vector(double3);
		Vector vector1 = new Vector(1.0, 2.0, 3.0);

		// Verify that the vector was created correctly
		assertEquals(vector1, vector, "wrong constructor vector");
		// =============== Boundary Values Tests ==================
		// TC11: test zero vector from constructor
		assertThrows(IllegalArgumentException.class, () -> new Vector(Double3.ZERO), //
				"ConstructorWithDouble3() should throw an exception for zero vector");
	}

	/**
	 * Test method for {@link primitives.Vector#Vector(double, double, double)}.
	 * This test checks the constructor that accepts individual x, y, and z
	 * coordinate values. It verifies correct vector creation and ensures that zero
	 * vectors throw an exception.
	 */
	@Test
	void testVectorConstructorWithComponents() {
		// ============Equivalence Partitions Tests ==============

		// Test case setup
		double x = 1.0;
		double y = 2.0;
		double z = 3.0;

		// Create the vector
		Vector vector = new Vector(x, y, z);
		Double3 d = new Double3(x, y, z);
		Vector vector1 = new Vector(d);

		// Verify that the vector was created correctly

		assertEquals(vector1, vector, "wrong constructor vector");
		// =============== Boundary Values Tests ==================
		// TC11: test zero vector from constructor
		assertThrows(IllegalArgumentException.class, () -> new Vector(Double3.ZERO), //
				"ConstructorWithComponents() should throw an exception for zero vector");
	}

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	void testAddVector() {
		// ============Equivalence Partitions Tests ==============
		Vector v1 = new Vector(1, 2, 3);
		Vector v2 = new Vector(2, 4, 6);
		Vector v3 = new Vector(-1, -2, -3);

		// Vector expected1 = new Vector(0,0,0);
		Vector expected = new Vector(3, 6, 9);

		// TC01: Test that add vector to vector and check that the result is proper.

		assertEquals(expected, v1.add(v2), "wrong add vector");
		assertEquals(v1, v3.add(v2), "wrong add vector");

		// =============== Boundary Values Tests ==================
		// TC11: test zero result from add opposite and equals vectors
		assertThrows(IllegalArgumentException.class, () -> v1.add(v3), //
				"addVector() for parallel vectors does not throw an exception");

	}

	/**
	 * Test method for {@link primitives.Vector#scale(int)}.
	 */
	@Test
	void testScale() {
		// ============Equivalence Partitions Tests ==============
		Vector v = new Vector(1, 2, 3);
		int scalar = 2;
		int scalar1 = -2;
		int scalar2 = 0;

		Vector expected = new Vector(2, 4, 6);
		Vector expected1 = new Vector(-2, -4, -6);
		// Vector v2 = new Vector(0, 0, 0);
		// TC01: Test that multiply a vector by a scalar and check that the result is
		// correct.
		assertEquals(expected, v.scale(scalar), "wrong scaled vector");
		// TC10: Test that multiply a vector by a negative scalar and check that the
		// result is correct.
		assertEquals(expected1, v.scale(scalar1), "wrong scaled vector");
		// =============== Boundary Values Tests ==================
		// TC11: Test for scaling a zero vector
		assertThrows(IllegalArgumentException.class, () -> v.scale(scalar2), //
				"scale() with scalar 0 does not throw an exception");
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	void testDotProduct() {
		// ============Equivalence Partitions Tests ==============
		Vector v1 = new Vector(1, 2, 3);
		Vector v2 = new Vector(2, -3, 6);
		Vector v4 = new Vector(1, 0, 0);
		Vector v5 = new Vector(-1, -1, 1);

		double expected = 14;
		double expected1 = 0;
		double expected2 = 1;

		// TC001: Checks the correctness and result of the inner multiplication
		// operation
		// (dot product) between two vectors
		assertEquals(expected, v1.dotProduct(v2), DELTA, "wrong dot product value");

		// =============== Boundary Values Tests ==================
		// TC010: Checks the correctness and result of the A unit vector with a
		// different
		// vector multiplication operation (dot product) between two vectors
		assertEquals(expected2, v1.dotProduct(v4), DELTA, "wrong dot product value");

		// TC011: Checks the correctness and result of the A vertical vector with a
		// different vector multiplication operation (dot product) between two vectors
		assertEquals(expected1, v1.dotProduct(v5), DELTA, "wrong dot product value");

		// TC100: Checks the correctness and result of the dot product for vectors with
		// a sharp angle between them
		Vector vSharp1 = new Vector(Math.sqrt(3) / 2, 0.5, 0); // cos(30 degrees) = sqrt(3)/2, sin(30 degrees) = 0.5
		Vector vSharp2 = new Vector(1, 0, 0);
		double expectedSharp = Math.sqrt(3) / 2; // cos(30 degrees) * |v1| * |v2| = sqrt(3)/2 * 1 * 1
		assertEquals(expectedSharp, vSharp1.dotProduct(vSharp2), DELTA,
				"wrong dot product value for 30 degrees sharp angle");

		// TC101: Checks the correctness and result of the dot product for vectors with
		// an obtuse angle of 150 degrees between them
		Vector vObtuse1 = new Vector(-Math.sqrt(3) / 2, 0.5, 0); // cos(150 degrees) = -sqrt(3)/2, sin(150 degrees) =
																	// 0.5
		Vector vObtuse2 = new Vector(1, 0, 0);
		double expectedObtuse = -Math.sqrt(3) / 2; // cos(150 degrees) * |v1| * |v2| = -sqrt(3)/2 * 1 * 1
		assertEquals(expectedObtuse, vObtuse1.dotProduct(vObtuse2), DELTA,
				"wrong dot product value for 150 degrees obtuse angle");

	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	void testCrossProduct() {
		Vector v1 = new Vector(1, 2, 3);
		Vector v2 = new Vector(-2, -4, -6);
		Vector v3 = new Vector(0, 3, -2);
		Vector v4 = new Vector(2, 4, 6);
		// ============ Equivalence Partitions Tests ==============
		Vector vr = v1.crossProduct(v3);
		// TC01: Test that length of cross-product is proper (orthogonal vectors taken
		// for simplicity)
		// assertEquals(v1.length() * v3.length(), vr.length(), "crossProduct() wrong
		// result length");
		// Test cross-product result orthogonality to its operands
		assertEquals(0, vr.dotProduct(v1), "crossProduct() result is not orthogonal to 1st operand");
		assertEquals(0, vr.dotProduct(v3), "crossProduct() result is not orthogonal to 2nd operand");

		// =============== Boundary Values Tests ==================
		// TC10: Test for the inner multiplication operation (dot product) between two
		// vectors with same directions.
		assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v4),
				"crossProduct() with same directions does not throw an exception");
		// TC11: Test for the inner multiplication operation (dot product) between two
		// vectors with opposite directions and equals.
		assertThrows(IllegalArgumentException.class, () -> v4.crossProduct(v2),
				"crossProduct() with same directions does not throw an exception");
		// TC100: Test for the inner multiplication operation (dot product) between two
		// vectors with opposite directions.
		assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v2),
				"crossProduct() with same directions does not throw an exception");
		// TC101: Test for the inner multiplication operation (dot product) between two
		// same vectors.
		assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v1),
				"crossProduct() with same directions does not throw an exception");
	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test

	void testLengthSquared() {
		// ============Equivalence Partitions Tests ==============
		Vector v1 = new Vector(1, 2, 3);

		double expected1 = 14;
		// TC01: Test for a vector with positive components
		assertEquals(expected1, v1.lengthSquared(), DELTA, "wrong squared length value for positive components");
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	void testLength() {
		// ============Equivalence Partitions Tests ==============
		Vector v = new Vector(1, 2, 3);
		double expected = Math.sqrt(14);
		// TC01: Checks the correctness and the result step length vector.
		assertEquals(expected, v.length(), DELTA, "wrong length value");
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	void testNormalize() {
		Vector v = new Vector(0, 3, 4);
		Vector n = v.normalize();
		// ============Equivalence Partitions Tests ==============
		// TC01: Simple test
		assertEquals(1d, n.lengthSquared(), 0.00001, "wrong normalized vector length");
		assertThrows(IllegalArgumentException.class, () -> v.crossProduct(n),
				"normalized vector is not in the same direction");
		assertEquals(new Vector(0, 0.6, 0.8), n, "wrong normalized vector");
		// =============== Boundary Values Tests ==================
		// TC02: Test for a vector which is already a unit vector
		Vector unitVector = new Vector(1, 0, 0);
		Vector nUnitVector = unitVector.normalize();
		assertEquals(unitVector, nUnitVector, "normalized vector is not the same as the original unit vector");

	}

	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	void testSubtractVector() {
		// ============Equivalence Partitions Tests ==============
		Vector v1 = new Vector(1, 2, 3);
		Vector v2 = new Vector(2, 4, 6);

		Vector expected1 = new Vector(-1, -2, -3);
		Vector expected2 = new Vector(1, 2, 3);
		// Vector expected3 = new Vector(0,0,0);

		// TC01: Test subtracting a vector from another vector and check that the result
		// is proper.
		assertEquals(expected1, v1.subtract(v2), "wrong subtracted vector");
		assertEquals(expected2, v2.subtract(v1), "wrong subtracted vector");

		// =============== Boundary Values Tests ==================
		// TC11: Test subtracting the same vector (result should be a zero vector)
		assertThrows(IllegalArgumentException.class, () -> v1.subtract(v1), //
				"subtract() with the same vector  does not throw an exception");
	}

}
