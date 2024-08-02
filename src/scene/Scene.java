package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a 3D scene containing geometries, lights, and other elements.
 * This class is a plain data structure with public access to all its attributes.
 * It provides methods to set and update the scene's attributes.
 *
 * @autor Shay and Asaf
 */
public class Scene {

    /** The name of the scene */
    public final String name;

    /** The background color of the scene */
    public Color background = Color.BLACK;

    /** The ambient light of the scene */
    public AmbientLight ambientLight = AmbientLight.NONE;

    /** The geometries in the scene */
    public Geometries geometries = new Geometries();

    /** The lights in the scene */
    public List<LightSource> lights = new LinkedList<>();

    /**
     * Constructs a Scene with the given name.
     *
     * @param name the name of the scene
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * Sets the background color of the scene.
     * If the provided color is null, the background color is not changed.
     *
     * @param backColor the background color to set; if null, the background color remains unchanged
     * @return this Scene object for method chaining
     */
    public Scene setBackground(Color backColor) {
        if (backColor != null) {
            this.background = backColor;
        }
        return this;
    }

    /**
     * Sets the ambient light of the scene.
     *
     * @param color the color of the ambient light
     * @param ka the scale factor of the ambient light
     * @return this Scene object
     */
    public Scene setAmbientLight(Color color, double ka) {
        if (ka < 0 || ka > 1) throw new IllegalArgumentException("Ambient light scale factor must be between 0 and 1");

        this.ambientLight = new AmbientLight(color, ka);
        return this;
    }

    /**
     * Sets the ambient light of the scene.
     *
     * @param amb the AmbientLight object to set
     * @return this Scene object
     */
    public Scene setAmbientLight(AmbientLight amb) {
        this.ambientLight = amb;
        return this;
    }

    /**
     * Setter for the geometries.
     * @param geometries the geometries
     * @return the scene
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * Sets the lights in the scene.
     *
     * @param lights the list of lights to set
     * @return this Scene object
     */
    public Scene setLights(List<LightSource> lights) {
        if (lights != null) {
            this.lights = lights;
        }
        return this;
    }
}
