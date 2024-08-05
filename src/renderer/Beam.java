package renderer;

import java.util.ArrayList;
import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import primitives.Color;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

/**
 * Beam class uses SimpleRayTracer to implement super-sampling.
 */
public class Beam {
    private final SimpleRayTracer rayTracer;
    private final Scene scene;
    private final Camera camera;

    /**
     * Constructor for Beam.
     *
     * @param scene  The scene to be used for tracing rays.
     * @param camera The camera object with view parameters.
     */
    public Beam(Scene scene, Camera camera) {
        this.camera = camera;
        // Store the scene and initialize the ray tracer
        this.scene = scene;
        this.rayTracer = new SimpleRayTracer(scene);
    }

    /**
     * Constructs a beam of rays for a specific pixel in the view plane.
     *
     * @param nX              Number of columns (pixels) in the view plane.
     * @param nY              Number of rows (pixels) in the view plane.
     * @param j               Column index of the pixel.
     * @param i               Row index of the pixel.
     * @param numRaysPerPixel Number of rays per pixel (squared root of desired rays, e.g., 4 means 16 rays).
     * @return List of rays.
     */
    public List<Ray> constructRaysBeam(int nX, int nY, int numRaysPerPixel) {
        List<Ray> rays = new ArrayList<>();
        double viewPlaneWidth = camera.getViewPlaneWidth();
        double viewPlaneHeight = camera.getViewPlaneHeight();
        double pixelWidth = viewPlaneWidth / nX;
        double pixelHeight = viewPlaneHeight / nY;

        // Number of rays per side
        int numRaysPerSide = (int) Math.sqrt(numRaysPerPixel); // Example: 4 for 16 rays
        double subPixelWidth = pixelWidth / numRaysPerSide;
        double subPixelHeight = pixelHeight / numRaysPerSide;

        // Loop through all pixels
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                Point pixelCenter = camera.getPixelCenter(nX, nY, j, i);

                // Generate rays for this pixel
                for (int subI = 0; subI < numRaysPerSide; subI++) {
                    for (int subJ = 0; subJ < numRaysPerSide; subJ++) {
                        double xShift = (subJ + 0.5) * subPixelWidth - pixelWidth / 2;
                        double yShift = pixelHeight / 2 - (subI + 0.5) * subPixelHeight;
                        Point subPixelCenter = pixelCenter.add(camera.getVRight().scale(xShift))
                                .add(camera.getVUp().scale(-yShift));
                        rays.add(camera.constructRayThroughPixel(subPixelCenter));
                    }
                }
            }
        }

        System.out.println("Constructed rays count: " + rays.size());
        return rays;
    }
    /**
     * Trace rays using the constructed beam.
     *
     * @param ray The primary ray.
     * @return The color of the pixel.
     */
    public Color traceRay(Ray ray) {
        // Find the closest intersection point for the primary ray
        GeoPoint closestPoint = rayTracer.findClosestIntersection(ray);

        if (closestPoint == null) {
            return scene.getBackground();
        }

        // Configuration for number of rays and supersampling
        int raysPerPixel = 16; // Example: 16 rays per pixel (4x4 grid)
        int numRaysPerSide = (int) Math.sqrt(raysPerPixel);

        // Pixel coordinates from the primary ray
        int nX = (int) camera.getViewPlaneWidth();
        int nY = (int) camera.getViewPlaneHeight();
        Point p = ray.getPoint(camera.getViewPlaneDistance());
        int j = (int) Math.round((p.getX() + camera.getViewPlaneWidth() / 2) * nX / camera.getViewPlaneWidth());
        int i = (int) Math.round((p.getY() + camera.getViewPlaneHeight() / 2) * nY / camera.getViewPlaneHeight());

        // Generate all rays for the pixel
        List<Ray> rays = constructRaysBeam(nX, nY, raysPerPixel);

        // Aggregate color from all rays
        Color colorSum = Color.BLACK;
        for (Ray sampleRay : rays) {
            GeoPoint samplePoint = rayTracer.findClosestIntersection(sampleRay);
            if (samplePoint != null) {
                colorSum = colorSum.add(rayTracer.getColor(samplePoint, sampleRay));
            }
        }

        // Average the color
        return colorSum.reduce(raysPerPixel);
    }

}
