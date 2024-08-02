package lighting;

import primitives.*;

/**
 * The AmbientLight class represents an ambient light source.
 * Ambient light is a non-directional light that affects all objects equally.
 */
public class AmbientLight extends Light {

    /** A constant representing no ambient light (black color, intensity 0) */
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, 0d);

    /**
     * Constructs an AmbientLight object with the specified color and intensity.
     *
     * @param IA the color of the ambient light
     * @param KA the intensity of the ambient light as a Double3
     */
    public AmbientLight(Color IA, Double3 KA) {
        super(IA.scale(KA));
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
}
