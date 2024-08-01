package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * A simple ray tracer that computes the color of the closest point
 * that a ray intersects within the scene.
 *
 * @author Shay and Asaf
 */
public class SimpleRayTracer extends RayTracerBase {

    /**
     * Constructs a SimpleRayTracer with the specified scene.
     *
     * @param s the scene to be used for ray tracing. This scene contains all
     *          the geometries, lights, and other elements needed for the
     *          ray tracing calculations.
     */
    public SimpleRayTracer(Scene s) {
        super(s);
    }

    /**
     * Computes the color of the closest point that the given ray intersects.
     *
     * @param ray the ray to be traced. This ray is used to find intersections
     *            with the geometries in the scene.
     * @return the color of the closest point that the ray intersects. If no
     *         intersection is found, a default color is returned.
     */
    @Override
    public Color traceRay(Ray ray) {
        List<Point> intersections = this.scene.geometries.findIntersections(ray);
        Point closestPoint = ray.findClosestPoint(intersections);
        return calcColor(closestPoint);
    }

    /**
     * Calculates the color based on the ambient light intensity.
     *
     * @param p the point at which the color is to be calculated. This method
     *          currently only considers ambient light for color calculation.
     * @return the color derived from the scene's ambient light intensity.
     */
    private Color calcColor(Point p) {
        return this.scene.ambientLight.getIntensity();
    }
}
