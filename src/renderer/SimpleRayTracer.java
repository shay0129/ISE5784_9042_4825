package renderer;

import geometries.Intersectable;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * A simple ray tracer that computes the color of the closest point
 * that a ray intersects within the scene.
 *
 * @author Shay and Asaf
 */
public class SimpleRayTracer extends RayTracerBase {

    private static final double DELTA = 0.1;

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
        // Find the closest intersection point to the ray's start
        Intersectable.GeoPoint closestPoint = ray.findClosestGeoPoint(scene.geometries.findGeoIntersections(ray));
        if (closestPoint == null) {
            // If no intersection is found, return the background color of the scene
            return scene.background;
        }
        // Calculate and return the color of the closest intersection point
        return calcColor(closestPoint, ray);
    }

    /**
     * Calculates the color at a given point.
     * @param gp the point to calculate the color for
     * @param ray the ray that hit the point
     * @return the color at the given point
     */
    private Color calcColor(Intersectable.GeoPoint gp, Ray ray) {
        return scene.ambientLight.getIntensity()
                .add(gp.geometry.getEmission())
                .add(calcLocalEffects(gp, ray));
    }

    /**
     * Calculates the local effects of lighting at a given point.
     * @param gp the point to calculate the effects for
     * @param ray the ray that hit the point
     * @return the color of the local effects at the given point
     */
    private Color calcLocalEffects(Intersectable.GeoPoint gp, Ray ray) {
        Color color = Color.BLACK;
        Vector v = ray.getDirection().normalize();
        Vector n = gp.geometry.getNormal(gp.point).normalize();
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return color;

        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point).normalize();
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sign(nv)
                if (unshaded(gp, lightSource, l, n)) {
                    Color iL = lightSource.getIntensity(gp.point);
                    Double3 diffusive = calcDiffusive(material, nl);
                    Double3 specular = calcSpecular(material, n, l, nl, v);
                    color = color.add(iL.scale(diffusive.add(specular)));
                }
            }
        }
        return color;
    }

    /**
     * Checks if the point is unshaded (i.e., not in shadow).
     * @param gp the point to check
     * @param lightSource the light source
     * @param l the direction to the light source
     * @param n the normal at the point
     * @return true if the point is unshaded, false otherwise
     */
    private boolean unshaded(Intersectable.GeoPoint gp, LightSource lightSource, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(gp.point, lightDirection, n);
        double maxDistance = lightSource.getDistance(gp.point);
        List<Intersectable.GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) {
            return true;
        }
        for (Intersectable.GeoPoint intersection : intersections) {
            if (gp.point.distance(intersection.point) <= maxDistance) {
                return false;
            }
        }
        return true;
    }

    /**
     * Calculates the diffusive component of the lighting.
     * @param material the material of the geometry
     * @param nl the dot product of the normal and light direction
     * @return the diffusive component of the lighting
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material.getKd().scale(Math.abs(nl));
    }

    /**
     * Calculates the specular component of the lighting.
     * @param material the material of the geometry
     * @param n the normal vector at the point
     * @param l the light direction vector
     * @param nl the dot product of the normal and light direction
     * @param v the view direction vector
     * @return the specular component of the lighting
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(2 * nl)).normalize();
        double vr = alignZero(-v.dotProduct(r));
        if (vr <= 0) return Double3.ZERO;
        return material.getKs().scale(Math.pow(vr, material.getShininess()));
    }
}
