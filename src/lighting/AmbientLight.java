package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * Represents ambient light in a scene. Ambient light is a type of light that is uniformly distributed
 * across all directions and does not have a specific source.
 *
 * @author Shay and Asaf
 */
public class AmbientLight {
    /** Constant for no ambient light. */
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    /** Intensity of the ambient light. */
    private final Color intensity;

    /** Attenuation factors for the ambient light. */
    private final Double3 Ka;

    /**
     * Constructs an ambient light with the given color and attenuation factor.
     *
     * @param iA the color of the ambient light
     * @param kA the attenuation factor for the ambient light
     */
    public AmbientLight(Color iA, double kA) {
        this.intensity = iA.scale(kA);
        this.Ka = new Double3(kA, kA, kA); // Create a Double3 instance with uniform scaling
    }

    /**
     * Constructs an ambient light with the given color and attenuation factors.
     *
     * @param iA the color of the ambient light
     * @param kA the attenuation factors for the ambient light
     */
    public AmbientLight(Color iA, Double3 kA) {
        this.intensity = iA.scale(kA);
        this.Ka = kA;
    }

    /**
     * Returns the intensity of the ambient light.
     *
     * @return the intensity of the ambient light
     */
    public Color getIntensity() {
        return intensity;
    }
}
