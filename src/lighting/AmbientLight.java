package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * The AmbientLight class represents an ambient light source.
 * Ambient light is a non-directional light that affects all objects equally.
 * It is a constant light that is not affected by the distance or direction of the light source.
 *
 * @author Shay and Asaf
 */
public class AmbientLight extends Light {

	/**
	 * A constant instance of AmbientLight with no light (black color).
	 * Useful as a default or placeholder value.
	 */
	public static final AmbientLight NONE = new AmbientLight(new Color(java.awt.Color.BLACK), Double3.ZERO);

	/**
	 * Constructs an AmbientLight with a specified intensity and attenuation factor (kA).
	 *
	 * @param intensity The intensity of the ambient light.
	 * @param kA The attenuation factor as a Double3 object.
	 */
	public AmbientLight(Color intensity, Double3 kA) {
		super(intensity.scale(kA));
	}

	/**
	 * Constructs an AmbientLight with a specified intensity and attenuation factor (kA).
	 *
	 * @param intensity The intensity of the ambient light.
	 * @param kA The attenuation factor as a double.
	 */
	public AmbientLight(Color intensity, double kA) {
		super(intensity.scale(kA));
	}
}
