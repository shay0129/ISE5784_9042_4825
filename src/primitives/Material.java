package primitives;

/**
 * Represents the material properties of a geometric object.
 * The material properties include diffuse reflection (kD), specular reflection (kS),
 * transmission coefficient (kT), reflection coefficient (kR), and shininess (nShininess).
 */
public class Material {

	public Double3 kD = Double3.ZERO;
	public Double3 kS = Double3.ZERO;
	public Double3 kT = Double3.ZERO;
	public Double3 kR = Double3.ZERO;
	public int nShininess = 0;

	/**
	 * Sets the reflection coefficient using a {@link Double3} object.
	 *
	 * @param kR the reflection coefficient
	 * @return the current Material object (for chaining method calls)
	 */
	public Material setkR(Double3 kR) {
		this.kR = kR;
		return this;
	}

	/**
	 * Sets the transmission coefficient using a {@link Double3} object.
	 *
	 * @param kT the transmission coefficient
	 * @return the current Material object (for chaining method calls)
	 */
	public Material setkT(Double3 kT) {
		this.kT = kT;
		return this;
	}

	/**
	 * Sets the reflection coefficient using a double value.
	 *
	 * @param kR the reflection coefficient
	 * @return the current Material object (for chaining method calls)
	 */
	public Material setkR(Double kR) {
		this.kR = new Double3(kR);
		return this;
	}

	/**
	 * Sets the transmission coefficient using a double value.
	 *
	 * @param kT the transmission coefficient
	 * @return the current Material object (for chaining method calls)
	 */
	public Material setkT(Double kT) {
		this.kT = new Double3(kT);
		return this;
	}

	/**
	 * Sets the diffuse reflection coefficient using a {@link Double3} object.
	 *
	 * @param kD the diffuse reflection coefficient
	 * @return the current Material object (for chaining method calls)
	 */
	public Material setKd(Double3 kD) {
		this.kD = kD;
		return this;
	}

	/**
	 * Sets the diffuse reflection coefficient using a double value.
	 *
	 * @param kD the diffuse reflection coefficient
	 * @return the current Material object (for chaining method calls)
	 */
	public Material setKd(Double kD) {
		this.kD = new Double3(kD);
		return this;
	}

	/**
	 * Sets the specular reflection coefficient using a {@link Double3} object.
	 *
	 * @param kS the specular reflection coefficient
	 * @return the current Material object (for chaining method calls)
	 */
	public Material setKs(Double3 kS) {
		this.kS = kS;
		return this;
	}

	/**
	 * Sets the specular reflection coefficient using a double value.
	 *
	 * @param kS the specular reflection coefficient
	 * @return the current Material object (for chaining method calls)
	 */
	public Material setKs(Double kS) {
		this.kS = new Double3(kS);
		return this;
	}

	/**
	 * Sets the shininess coefficient.
	 *
	 * @param nShininess the shininess coefficient
	 * @return the current Material object (for chaining method calls)
	 */
	public Material setShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}
}
