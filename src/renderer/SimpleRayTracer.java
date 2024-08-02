package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.*;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * SimpleRayTracer is a basic implementation of a ray tracer for rendering a scene.
 */
public class SimpleRayTracer extends RayTracerBase {

    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final Double3 INITIAL_K = Double3.ONE;

    /**
     * Constructs a SimpleRayTracer with the specified scene.
     *
     * @param scene the scene to be rendered
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }


    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
    }

    /**
     * Calculates the color at a given intersection point using local and global lighting effects.
     *
     * @param geoPoint the intersection point
     * @param ray      the ray that intersects the point
     * @return the color at the intersection point
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * Recursively calculates the color at an intersection point considering local and global effects.
     *
     * @param geoPoint the intersection point
     * @param ray      the ray that intersects the point
     * @param level    the current recursion level
     * @param k        the accumulated transparency factor
     * @return the color at the intersection point
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(geoPoint, ray, k);
        return level == 1 ? color : color.add(calcGlobalEffects(geoPoint, ray, level, k));
    }

    /**
     * Calculates the global reflection and refraction effects recursively.
     *
     * @param geoPoint the intersection point
     * @param ray      the ray that intersects the point
     * @param level    the current recursion level
     * @param k        the accumulated transparency factor
     * @return the color contribution from global effects
     */
    private Color calcGlobalEffects(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Material material = geoPoint.geometry.getMaterial();
        return calcGlobalEffects(constructReflectedRay(geoPoint, ray), material.kR, level, k)
                .add(calcGlobalEffects(constructRefractedRay(geoPoint, ray), material.kT, level, k));
    }

    /**
     * Calculates the global reflection and refraction effects for a given ray direction.
     *
     * @param ray the ray direction to trace
     * @param kx  the transparency factor of the material
     * @param level the current recursion level
     * @param k the accumulated transparency factor
     * @return the color contribution from global effects
     */
    private Color calcGlobalEffects(Ray ray, Double3 kx, int level, Double3 k) {
        Double3 kkr = kx.product(k);
        if (kkr.lowerThan(MIN_CALC_COLOR_K)) {
            return Color.BLACK;
        }

        GeoPoint geoPoint = ray.findClosestGeoPoint(scene.geometries.findGeoIntersections(ray));
        return geoPoint == null ? scene.background : calcColor(geoPoint, ray, level - 1, kkr).scale(kkr);
    }

    /**
     * Constructs a refracted ray from an intersection point.
     *
     * @param geoPoint the intersection point
     * @param ray      the incident ray
     * @return the refracted ray
     */
    private Ray constructRefractedRay(GeoPoint geoPoint, Ray ray) {
        return new Ray(geoPoint.point, ray.getDirection(), geoPoint.geometry.getNormal(geoPoint.point));
    }

    /**
     * Constructs a reflected ray from an intersection point.
     *
     * @param geoPoint the intersection point
     * @param ray      the incident ray
     * @return the reflected ray
     */
    private Ray constructReflectedRay(GeoPoint geoPoint, Ray ray) {
        Vector v = ray.getDirection();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        double vn = alignZero(v.dotProduct(n));
        Vector r = v.subtract(n.scale(2 * vn));
        return new Ray(geoPoint.point, r, n);
    }

    /**
     * Calculates the local lighting effects at a given intersection point.
     *
     * @param gp the intersection point
     * @param ray the ray that intersects the point
     * @param k the accumulated transparency factor
     * @return the color contribution from local lighting effects
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
        Vector n = gp.geometry.getNormal(gp.point);
        Vector v = ray.getDirection();
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        Material material = gp.geometry.getMaterial();
        Color color = gp.geometry.getEmission();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if ((nl * nv > 0)) { // sign(nl) == sign(nv)
                Double3 ktr = transparency(gp, lightSource, l, n);
                if (ktr.product(k).greaterThan(MIN_CALC_COLOR_K)){
                    Color lightIntensity = lightSource.getIntensity(gp.point).scale(ktr);
                    color = color.add(calcDiffuse(material, nl, lightIntensity))
                            .add(calcSpecular(material, n, l, nl, v, lightIntensity));

                }
            }
        }
        return color;
    }

    /**
     * Calculates the diffuse reflection component of the lighting.
     *
     * @param mat the material of the intersected geometry
     * @param nl  the dot product of the normal and light direction vectors
     * @param lightIntensity the intensity of the light source
     * @return the diffuse reflection component
     */
    private Color calcDiffuse(Material mat, double nl, Color lightIntensity) {
        return lightIntensity.scale(mat.kD.scale(Math.abs(nl)));
    }

    /**
     * Calculates the specular reflection component of the lighting.
     *
     * @param mat the material of the intersected geometry
     * @param n   the normal vector at the intersection point
     * @param l   the light direction vector
     * @param nl  the dot product of the normal and light direction vectors
     * @param v   the view direction vector
     * @param lightIntensity the intensity of the light source
     * @return the specular reflection component
     */
    private Color calcSpecular(Material mat, Vector n, Vector l, double nl, Vector v, Color lightIntensity) {
        Vector r = l.subtract(n.scale(2 * nl));
        double vr = alignZero(v.dotProduct(r));
        if (vr <= 0) return Color.BLACK;
        return lightIntensity.scale(mat.kS.scale(Math.pow(vr, mat.nShininess)));
    }

    /**
     * Finds the closest intersection of a ray with objects in the scene.
     *
     * @param ray the ray to trace
     * @return the closest intersection point, or null if no intersection found
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        return intersections == null ? null : ray.findClosestGeoPoint(intersections);
    }

    /**
     * Calculates the transparency factor for shadows and reflections.
     *
     * @param geoPoint   the intersection point
     * @param ls         the light source
     * @param l          the light direction vector
     * @param n          the normal vector at the intersection point
     * @return the transparency factor
     */
    private Double3 transparency(GeoPoint geoPoint, LightSource ls, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geoPoint.point, lightDirection, n);
        double lightDistance = ls.getDistance(geoPoint.point);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, lightDistance);
        if (intersections == null) return Double3.ONE;

        Double3 ktr = Double3.ONE;
        for (GeoPoint gp : intersections) {
            ktr = ktr.product(gp.geometry.getMaterial().kT);
            if (ktr.lowerThan(MIN_CALC_COLOR_K)) return Double3.ZERO;
        }
        return ktr;
    }
}
