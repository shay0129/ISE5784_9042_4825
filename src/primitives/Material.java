package primitives;

/**
 * Represents the material properties of a geometric object.
 * The material properties include diffuse reflection (kD), specular reflection (kS),
 * transmission coefficient (kT), reflection coefficient (kR), and shininess (nShininess).
 *
 * @autor Shay and Asaf
 */
public class Material {
	/**
	 * The ambient coefficient of the material.
	 */
	public double kA;

	/**
	 * kD is the diffuse reflection coefficient.
	 * It defines how much the material reflects diffuse light.
	 */
	public Double3 kD = Double3.ZERO;

	/**
	 * kS is the specular reflection coefficient.
	 * It defines how much the material reflects specular light.
	 */
	public Double3 kS = Double3.ZERO;

	/**
	 * kT is the transparency coefficient.
	 * It defines how much the material allows light to pass through.
	 */
	public Double3 kT = Double3.ZERO;

	/**
	 * kR is the reflection coefficient.
	 * It defines how much the material reflects light as a mirror.
	 */
	public Double3 kR = Double3.ZERO;

	/**
	 * The shininess of the material.
	 * It defines the sharpness of the specular highlight.
	 */
	public int shininess = 0;


	/**
	 * Setter for the diffuse coefficient.
	 * 
	 * @param kD The diffuse coefficient to set.
	 * @return This Material object.
	 */
	public Material setKD(double kD) {
		this.kD = new Double3(kD);
		return this;
	}

	/**
	 * Setter for the diffuse coefficient.
	 * 
	 * @param kD The diffuse coefficient to set.
	 * @return This Material object.
	 */
	public Material setKD(Double3 kD) {
		this.kD = kD;
		return this;
	}

	/**
	 * Setter for the specular coefficient.
	 * 
	 * @param kS The specular coefficient to set.
	 * @return This Material object.
	 */
	public Material setKS(double kS) {
		this.kS = new Double3(kS);
		return this;
	}

	/**
	 * Setter for the specular coefficient.
	 * 
	 * @param kS The specular coefficient to set.
	 * @return This Material object.
	 */
	public Material setKS(Double3 kS) {
		this.kS = kS;
		return this;
	}

	/**
	 * Setter for the transparency coefficient.
	 *
	 * @param kT The transparency coefficient to set.
	 * @return This Material object.
	 */
	public Material setKT(double kT) {
		this.kT = new Double3(kT);
		return this;
	}

	/**
	 * Setter for the transparency coefficient.
	 *
	 * @param kT The transparency coefficient to set.
	 * @return This Material object.
	 */
	public Material setKT(Double3 kT) {
		this.kT = kT;
		return this;
	}

	/**
	 * Setter for the reflection coefficient.
	 *
	 * @param kR The reflection coefficient to set.
	 * @return This Material object.
	 */
	public Material setKR(double kR) {
		this.kR = new Double3(kR);
		return this;
	}

	/**
	 * Setter for the reflection coefficient.
	 *
	 * @param kR The reflection coefficient to set.
	 * @return This Material object.
	 */
	public Material setKR(Double3 kR) {
		this.kR = kR;
		return this;
	}

	/**
	 * Setter for the shininess exponent.
	 * 
	 * @param shininess The shininess exponent to set.
	 * @return This Material object.
	 */
	public Material setShininess(int shininess) {
		this.shininess = shininess;
		return this;
	}
}
