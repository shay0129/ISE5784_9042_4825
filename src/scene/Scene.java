package scene;

import geometries.Geometries;
import geometries.Intersectable;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * Scene class
 * Plain Data Structure - access to all its attributes are public
 *
 * @author Shay and Asaf
 */
public class Scene {

    /** The name of the scene */
    public final String name;

    /** The background color of the scene */
    public Color background = Color.BLACK;;

    /** The ambient light of the scene */
    public AmbientLight ambientLight = AmbientLight.NONE;

    /** The geometries in the scene */
    public Geometries geometries = new Geometries();

    /** The lights in the scene */
    public List<LightSource> lights = new LinkedList<>();

    /***
     * Constructor for Scene's name
     * @param name
     */
    public Scene(String name) {
        this.name = name;
    }
    /***
     * Sets the background color
     * @param backColor
     * @return
     */
    public Scene setBackground( Color backColor) {
        this.background = backColor;
        return this;
    }

    /**
     * sets the Ambient light to the color and scale given
     * @param color
     * @param ka
     * @return
     */
    public Scene setAmbientLight(Color color, double ka) {
        this.ambientLight = new AmbientLight(color, ka);
        return this;
    }

    public Scene setAmbientLight(AmbientLight amb) {
        this.ambientLight = amb;
        return this;
    }
    /**
     * adds a geometry to the list
     *
     * @param geometries the Geometry you want to add to the scene
     * @return the scene
     */
    public Scene setGeometries(Geometries... geometries) {
        for (Intersectable g : geometries) {
            this.geometries.add(g);
        }
        return this;
    }

    /**
     * Updates the lights in the scene.
     *
     * @param lights The new list of lights.
     * @return This Scene object.
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

}