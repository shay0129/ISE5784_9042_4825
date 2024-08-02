package lighting;

import primitives.*;

/**
 * The AmbientLight class represents an ambient light source.
 * Ambient light is a non-directional light that affects all objects equally.
 * It is a constant light that is not affected by the distance or direction of the light source.
 *
 * @author Shay and Asaf
 */
public class AmbientLight extends Light {

    /** A constant representing no ambient light (black color, intensity 0) */
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, 0d);

    /**
     * Constructs an AmbientLight object with the specified color and intensity.
     *
     * @param iA the color of the ambient light
     * @param kA the intensity of the ambient light as a Double3
     */
    public AmbientLight(Color iA, Double3 kA) {
        super(iA.scale(kA));
    }

    /**
     * Constructs an AmbientLight object with the specified color and intensity.
     *
     * @param IA the color of the ambient light
     * @param KA the intensity of the ambient light as a Double
     */
    public AmbientLight(Color IA, Double KA) {
        super(IA.scale(KA));
    }

    /**
     * Gets the intensity of the ambient light.
     *
     * @return the intensity of the ambient light
     */
    public Color getIntensity() {
        return super.getIntensity();
    }
}
