
package primitives;

import static primitives.Util.isZero;

/**
 * This class represents a triad of three double values and provides operations
 * such as addition, subtraction, scaling, and comparison.
 * It serves as a base for primitive classes that rely on three numerical values.
 *
 * @author Shay and Asaf
 */
public class Double3 {
	/** First number */
	final double d1;
	/** Second number */
	final double d2;
	/** Third number */
	final double d3;

	/** Zero triad (0,0,0) */
	public static final Double3 ZERO = new Double3(0, 0, 0);

	/** One's triad (1,1,1) */
	public static final Double3 ONE = new Double3(1, 1, 1);

	/**
	 * Constructs a Double3 with the specified three values.
	 *
	 * @param d1 first number value
	 * @param d2 second number value
	 * @param d3 third number value
	 */
	public Double3(double d1, double d2, double d3) {
		this.d1 = d1;
		this.d2 = d2;
		this.d3 = d3;
	}

	/**
	 * Constructs a Double3 with the same value for all three components.
	 *
	 * @param value the value for all components
	 */
	public Double3(double value) {
		this.d1 = value;
		this.d2 = value;
		this.d3 = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		return (obj instanceof Double3 other)
				&& isZero(d1 - other.d1)
				&& isZero(d2 - other.d2)
				&& isZero(d3 - other.d3);
	}

	@Override
	public int hashCode() {
		return (int) Math.round(d1 + d2 + d3);
	}

	@Override
	public String toString() {
		return "(" + d1 + "," + d2 + "," + d3 + ")";
	}

	/**
	 * Adds two Double3 objects component-wise.
	 *
	 * @param rhs the right-hand side operand for addition
	 * @return a new Double3 representing the result
	 */
	public Double3 add(Double3 rhs) {
		return new Double3(d1 + rhs.d1, d2 + rhs.d2, d3 + rhs.d3);
	}

	/**
	 * Subtracts two Double3 objects component-wise.
	 *
	 * @param rhs the right-hand side operand for subtraction
	 * @return a new Double3 representing the result
	 */
	public Double3 subtract(Double3 rhs) {
		return new Double3(d1 - rhs.d1, d2 - rhs.d2, d3 - rhs.d3);
	}

	/**
	 * Multiplies each component by a scalar value.
	 *
	 * @param rhs the scalar to multiply by
	 * @return a new Double3 representing the scaled values
	 */
	public Double3 scale(double rhs) {
		return new Double3(d1 * rhs, d2 * rhs, d3 * rhs);
	}

	/**
	 * Divides each component by a scalar value.
	 *
	 * @param rhs the scalar to divide by
	 * @return a new Double3 representing the reduced values
	 */
	public Double3 reduce(double rhs) {
		return new Double3(d1 / rhs, d2 / rhs, d3 / rhs);
	}

	/**
	 * Multiplies two Double3 objects component-wise.
	 *
	 * @param rhs the right-hand side operand for multiplication
	 * @return a new Double3 representing the product
	 */
	public Double3 product(Double3 rhs) {
		return new Double3(d1 * rhs.d1, d2 * rhs.d2, d3 * rhs.d3);
	}

	/**
	 * Checks if all components are less than a given value.
	 *
	 * @param k the value to compare against
	 * @return true if all components are less than k, false otherwise
	 */
	public boolean lowerThan(double k) {
		return d1 < k && d2 < k && d3 < k;
	}

	/**
	 * Checks if all components are less than the corresponding components of another Double3.
	 *
	 * @param other the other Double3 to compare against
	 * @return true if all components are less, false otherwise
	 */
	public boolean lowerThan(Double3 other) {
		return d1 < other.d1 && d2 < other.d2 && d3 < other.d3;
	}

	/**
	 * Checks if all components are greater than a given value.
	 *
	 * @param k the value to compare against
	 * @return true if all components are greater than k, false otherwise
	 */
	public boolean greaterThan(double k) {
		return d1 > k && d2 > k && d3 > k;
	}

	/**
	 * Checks if all components are greater than the corresponding components of another Double3.
	 *
	 * @param other the other Double3 to compare against
	 * @return true if all components are greater, false otherwise
	 */
	public boolean greaterThan(Double3 other) {
		return d1 > other.d1 && d2 > other.d2 && d3 > other.d3;
	}
}
