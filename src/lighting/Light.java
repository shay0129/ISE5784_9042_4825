package lighting;

import primitives.Color;

/**
 * Abstract class representing a light source. This class defines common
 * properties and behavior for all light sources.
 */
public abstract class Light {
	/**
	 * The intensity (color) of the light source. This field is protected to allow
	 * access within subclasses.
	 */
	protected Color intensity;

	/**
	 * Constructs a new Light with the given intensity.
	 *
	 * @param intensity The intensity (color) of the light source.
	 */
	protected Light(Color intensity) {
		this.intensity = intensity;
	}

	/**
	 * Returns the intensity (color) of the light source.
	 *
	 * @return The intensity (color) of the light source.
	 */
	public Color getIntensity() {
		return intensity;
	}
}
