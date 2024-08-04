package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * Abstract base class for ray tracing algorithms.
 * Provides the foundation for tracing rays in a given scene and determining the color at intersection points.
 * Subclasses must implement the specific ray tracing logic.
 *
 * @autor Shay and Asaf
 */
public abstract class RayTracerBase {
	/** The scene to be rendered by the ray tracer */
	protected Scene scene;

	/**
	 * Constructs a RayTracerBase with the given scene.
	 *
	 * @param scene the scene to be rendered by this ray tracer
	 */
	public RayTracerBase(Scene scene) {
		this.scene = scene;
	}

	/**
	 * Traces a ray and returns the color at the intersection point.
	 * This method must be implemented by subclasses to provide the specific ray tracing logic.
	 *
	 * @param ray the ray to be traced
	 * @return the color at the intersection point
	 */
	public abstract Color traceRay(Ray ray);
}
