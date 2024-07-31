package renderer;

import primitives.Ray;
import scene.Scene;

/**
 * Abstract base class for ray tracing algorithms.
 *
 * @author Shay and Asaf
 */
public abstract class RayTraceBase {

    protected Scene scene;

    /**
     * Constructor for RayTraceBase.
     *
     * @param scene the scene to be used for ray tracing. This represents the environment
     *              in which rays are traced and should contain all the relevant
     *              objects and lights.
     */
    public RayTraceBase(Scene scene) {
        this.scene=scene;
    }

    /**
     * Abstract method for tracing a ray.
     *
     * @param ray the ray to be traced. This ray will be used to compute the color of the
     *            pixel it represents in the rendered image.
     * @return the color computed for the given ray. This represents the color that the
     *         ray intersects with in the scene.
     */
    public abstract primitives.Color traceRay(Ray ray);
}