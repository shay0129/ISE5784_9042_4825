package unittests.renderer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import renderer.Camera;
import renderer.Beam;
import java.util.List;

import org.junit.jupiter.api.Test;

import primitives.*;
import renderer.ImageWriter;
import renderer.RayTracerBase;
import scene.Scene;

class BeamTests {
    // Create an instance of Scene (assuming you have a way to set it up)
    Scene testScene = new Scene("test scene");

    // Create an instance of ImageWriter
    ImageWriter imageWriter = new ImageWriter("BeamTestImage", 800, 600);

    // Initialize the Camera.Builder
    Camera.Builder cameraBuilder = Camera.getBuilder()
            .setLocation(new Point(0, 0, 0))
            .setDirection(new Vector(0, 0, -1), new Vector(0, -1, 0))
            .setVpDistance(10)
            .setVpSize(8, 8)
            .setImageWriter(imageWriter)
            .setRayTracer(new RayTracerBase(testScene) { // Pass the Scene object
                @Override
                public Color traceRay(Ray ray) {
                    // Replace with actual logic or return a default color
                    return new Color(0, 0, 0); // Black color for default
                }
            });

    // Build the camera instance
    Camera camera = cameraBuilder.build();

    @Test
    void testConstructRaysBeam() {
        final String badRay = "Bad ray";
        int expectedRays; // Each pixel has 16x16 rays
        List<Ray> rays;


        // Test Case 1: 8x8 Grid with 4 rays per pixel (16 rays per pixel)
        Beam beam1 = new Beam(testScene, cameraBuilder.build());
        rays = beam1.constructRaysBeam(8, 8, 16);
        int raysPerPixel = 4 * 4; // 16 rays per pixel
        expectedRays = 8 * 8 * raysPerPixel; // Total rays for the 8x8 grid
        assertEquals(expectedRays, rays.size(), "Should generate " + expectedRays + " rays for an 8x8 grid with 4 rays per pixel");

        // Check rays are properly distributed
        for (Ray ray : rays) {
            assertTrue(ray.getDirection().length() > 0, badRay);
        }


        /*
        // Test Case 2: 6x6 Grid with 9 rays per pixel (81 rays per pixel)
        Beam beam2 = new Beam(testScene, cameraBuilder.build());
        rays = beam2.constructRaysBeam(6, 6, 9);
        expectedRays = 6 * 6 * 9 * 9; // Each pixel has 9x9 rays
        assertEquals(expectedRays, rays.size(), "Should generate " + expectedRays + " rays for a 6x6 grid with 9 rays per pixel");
        /*
        // Edge Case: 1x1 Grid with 1 ray per pixel
        Beam beam3 = new Beam(testScene, cameraBuilder.build());
        rays = beam3.constructRaysBeam(1, 1, 1, 1, 1);
        assertEquals(1, rays.size(), "Should generate 1 ray for a 1x1 grid");

        // Edge Case: Large grid
        Beam beam4 = new Beam(testScene, cameraBuilder.build());
        rays = beam4.constructRaysBeam(100, 100, 2, 2, 2);
        expectedRays = 100 * 100 * 2 * 2; // Each pixel has 2x2 rays
        assertEquals(expectedRays, rays.size(), "Should generate " + expectedRays + " rays for a 100x100 grid with 2 rays per pixel");

        // Edge Case: Zero rays per pixel
        assertThrows(IllegalArgumentException.class, () -> {
            beam4.constructRaysBeam(8, 8, 1, 1, 0);
        }, "Should throw IllegalArgumentException for zero rays per pixel");

        // Edge Case: Negative grid size
        assertThrows(IllegalArgumentException.class, () -> {
            beam4.constructRaysBeam(-1, -1, 1, 1, 1);
        }, "Should throw IllegalArgumentException for negative grid size");

         */
    }


}
