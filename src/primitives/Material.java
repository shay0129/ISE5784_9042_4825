package primitives;

/**
 * Represents the material properties of a geometric object.
 * The material properties include diffuse reflection (kD), specular reflection (kS),
 * transmission coefficient (kT), reflection coefficient (kR), and shininess (nShininess).
 *
 * @autor Shay and Asaf
 */
public class Material {

	/** The diffuse reflection coefficient */
	public Double3 kD = Double3.ZERO;
	public Double3 kS = Double3.ZERO;
	public Double3 kT = Double3.ZERO;
	public Double3 kR = Double3.ZERO;
	/** The shininess coefficient */
	public int nShininess = 0;

	/**
	 * Sets the diffuse reflection coefficient using a {@link Double3} object.
	 *
	 * @param kd the diffuse reflection coefficient
	 * @return the current Material object (for chaining method calls)
	 */
	public Material setKd(Double3 kd) {
		this.kD = kd;
		return this;
	}

	/**
	 * Sets the diffuse reflection coefficient using a double value.
	 *
	 * @param kd the diffuse reflection coefficient
	 * @return the current Material object (for chaining method calls)
	 */
	public Material setKd(Double kd) {
		this.kD = new Double3(kd);
		return this;
	}

	/**
	 * Sets the specular reflection coefficient using a double value.
	 *
	 * @param ks the specular reflection coefficient
	 * @return the current Material object (for chaining method calls)
	 */
	public Material setKs(Double ks) {
		this.kS = new Double3(ks);
		return this;
	}

	/**
	 * Sets the specular reflection coefficient using a {@link Double3} object.
	 *
	 * @param ks the specular reflection coefficient
	 * @return the current Material object (for chaining method calls)
	 */
	public Material setKs(Double3 ks) {
		this.kS = ks;
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
