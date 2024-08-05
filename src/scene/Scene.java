package scene;

import primitives.Color;
import lighting.AmbientLight;
import geometries.Geometries;
import lighting.LightSource;

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
	public Color background = Color.BLACK;;

	/** The ambient light of the scene */
	public AmbientLight ambientLight = AmbientLight.NONE;

	/** The geometries in the scene */
	public Geometries geometries = new Geometries();

	/** The lights in the scene */
	public List<LightSource> lights = new LinkedList<>();

	/**
	 * Constructs a Scene object with the given name.
	 *
	 * @param name The name of the scene.
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
		this.background = backColor;
		return this;
	}

	public Color getBackground() {
		return background;
	}

	/**
	 * Sets the ambient light of the scene.
	 * @param ambientLight The new ambient light.
	 * @return This Scene object.
	 */
	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
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
	 * @param lights the list of lights to set
	 * @return this Scene object
	 */
	public Scene setLights(List<LightSource> lights) {
		this.lights = lights;
		return this;
	}
}
