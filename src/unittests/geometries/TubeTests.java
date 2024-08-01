package unittests.geometries;

import geometries.Tube;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Testing Tubes
 * 
 * @author Shay and Asaf
 */
class TubeTests {

	/**
	 * Test method for Tube constructor.
	 * Tests various cases including valid tube creation and invalid cases.
	 * Tests the normal calculation at various points on the tube.
	 */
	@Test
	void testConstructor() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: Correct tube
		assertDoesNotThrow(() ->
						new Tube(1, new Ray(new Point(0, 0, 0),
								new Vector(0, 0, 1))),
				"Failed constructing a correct tube");

		// TC02: Zero radius
		assertThrows(IllegalArgumentException.class, ()
						-> new Tube(0, new Ray(new Point(0, 0, 0),
						new Vector(0, 0, 1))),
				"Failed constructing a tube with zero radius");

		// TC03: Negative radius
		assertThrows(IllegalArgumentException.class,
				() -> new Tube(-1, new Ray(new Point(0, 0, 0), new Vector(0, 0, 1))),
				"Failed constructing a tube with negative radius");
	}

	/**
	 * Test method for {@link Tube#getNormal(Point)}.
	 */
	@Test
	void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: Test for a general case
		double radius = 1;
		Ray axisRay = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)); // Tube along the Z-axis
		Tube tube = new Tube(radius, axisRay);
		// Choose a point on the tube
		Point testPoint = new Point(1, 0, 1); // Point on the surface of the tube
		// Calculate the vector from the center of the tube to the test point
		Vector expectedNormal = new Vector(1, 0, 0); // Normal vector pointing outwards
		// Get the normal vector at the test point
		Vector actualNormal = tube.getNormal(testPoint);
		Assertions.assertEquals(expectedNormal, actualNormal, "getNormal() does not return the correct normal vector");

		// Ensure the normal vector is normalized
		Assertions.assertEquals(1, actualNormal.length(), "Normal vector is not normalized");
	}
}
