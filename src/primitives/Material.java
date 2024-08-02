package primitives;

/**
 * A class representing Material properties.
 * Used for calculating light reflection and refraction.
 *
 * @autor Shay and Asaf
 */
public class Material {
	private Double3 kD = Double3.ZERO;
	private Double3 kS = Double3.ZERO;
	private int shininess = 0;

	/**
	 * Default constructor for Material.
	 */
	public Material() {
	}

	/**
	 * Getter for the diffuse coefficient.
	 * @return the diffuse coefficient
	 */
	public Double3 getKd() {
		return kD;
	}

	/**
	 * Getter for the specular coefficient.
	 * @return the specular coefficient
	 */
	public Double3 getKs() {
		return kS;
	}

	/**
	 * Getter for the shininess level.
	 * @return the shininess level
	 */
	public int getShininess() {
		return shininess;
	}

	/**
	 * Setter for the diffuse coefficient.
	 * 
	 * @param kD The diffuse coefficient to set.
	 * @return This Material object.
	 */
	public Material setKd(double kD) {
		this.kD = new Double3(kD);
		return this;
	}

	/**
	 * Setter for the diffuse coefficient.
	 * 
	 * @param kD The diffuse coefficient to set.
	 * @return This Material object.
	 */
	public Material setKd(Double3 kD) {
		this.kD = kD;
		return this;
	}

	/**
	 * Setter for the specular coefficient.
	 * 
	 * @param kS The specular coefficient to set.
	 * @return This Material object.
	 */
	public Material setKs(double kS) {
		this.kS = new Double3(kS);
		return this;
	}

	/**
	 * Setter for the specular coefficient.
	 * 
	 * @param kS The specular coefficient to set.
	 * @return This Material object.
	 */
	public Material setKs(Double3 kS) {
		this.kS = kS;
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
