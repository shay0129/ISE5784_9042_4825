package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class SimpleRayTracer extends RayTraceBase {

    public SimpleRayTracer(Scene s) {
        super(s);
    }

    /***
     *retrun the color of the closest point
     */
    public Color traceRay(Ray ray) {
        List<Point> intersections = this.scene.geometries.findIntersections(ray);
        Point closestPoint = ray.findClosestPoint(intersections);
        return calcColor(closestPoint);
    }

    /***
     * Get the color from ambient light and intensity
     * @param p
     * @return
     */
    private Color calcColor(Point p) {
        return this.scene.ambientLight.getIntensity();
    }
}