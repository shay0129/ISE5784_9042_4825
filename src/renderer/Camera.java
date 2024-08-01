package renderer;

import primitives.*;

import java.util.MissingResourceException;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * class camera
 */
public class Camera implements Cloneable {

    private Point p0; // location of camera

    private Vector vUp;
    private Vector vTo;
    private Vector vRight;

    private double width = 0.0;
    private double height = 0.0;
    private double distance = 0.0;

    ImageWriter imageWriter;
    RayTracerBase rayTracer;

    /**
     * empty constructor for camera
     */
    private Camera() {
        width = 0;
        height = 0;
        distance = 0;
    }

    /**
     * Returns a new Builder object to construct a Camera.
     *
     * @return a new Builder object
     */
    public static Builder getBuilder() {
        return new Builder();
    }

    /**
     * Gets the location of the camera.
     *
     * @return the location of the camera
     */
    public Point getLocation() {
        return p0;
    }

    /**
     * Gets the right direction vector of the camera.
     *
     * @return the right direction vector of the camera
     */
    public Vector getVRight() {
        return vRight;
    }

    /**
     * Gets the up direction vector of the camera.
     *
     * @return the up direction vector of the camera
     */
    public Vector getVUp() {
        return vUp;
    }

    /**
     * Gets the direction vector towards which the camera is facing.
     *
     * @return the direction vector towards which the camera is facing
     */
    public Vector getVTo() {
        return vTo;
    }

    /**
     * Gets the height of the viewport.
     *
     * @return the height of the viewport
     */
    public double getHeight() {
        return height;
    }

    /**
     * Gets the width of the viewport.
     *
     * @return the width of the viewport
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets the distance from the camera to the viewport.
     *
     * @return the distance from the camera to the viewport
     */
    public double getDistance() {
        return distance;
    }


    /**
     * Prints a grid on the image with the specified interval and color.
     *
     * @param interval the interval for grid lines
     * @param color    the color of the grid lines
     * @return this Camera instance
     * @throws MissingResourceException if the ImageWriter is missing
     */
    public Camera printGrid(int interval, Color color) {
        if (imageWriter == null) {
            throw new MissingResourceException("ImageWriter", "ImageWriter", "ImageWriter is missing");
        }
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(j, i, color);
                }
            }
        }
        return this;
    }

    /**
     * Writes the rendered image to the output using the ImageWriter.
     *
     * @throws MissingResourceException if the ImageWriter is missing
     */
    public void writeToImage() {
        if (imageWriter == null) {
            throw new IllegalStateException("ImageWriter is not initialized");
        }

        imageWriter.writeToImage();
    }

    /**
     * Casts a ray through a specific pixel in the image, computes the color of the
     * pixel based on the ray-tracing algorithm, and writes the color to the
     * corresponding pixel in the image.
     *
     * @param nX     The width of the image.
     * @param nY     The height of the image.
     * @param column The column index of the pixel.
     * @param row    The row index of the pixel.
     */
    public Ray constructRay(int nX, int nY, int column, int row) {
        if (nY == 0 || nX == 0) {
            throw new IllegalArgumentException("It is impossible to divide by 0");
        }

        Point Pc = p0.add(vTo.scale(distance));
        double Ry = height / nY;
        double Rx = width / nX;

        double Yi = -1 * (row - (nY - 1) / 2.0) * Ry;
        double Xj = -1*(column - (nX - 1) / 2.0) * Rx;

        Point Pij = Pc;
        if (!isZero(Xj)) {
            Pij = Pij.add(vRight.scale(Xj));
        }
        if (!isZero(Yi)) {
            Pij = Pij.add(vUp.scale(Yi));
        }

        Vector direction = Pij.subtract(p0).normalize();

        return new Ray(p0, direction);
    }

    /**
     * Casts a ray through the pixel at the given coordinates (i, j) and writes the result to the image.
     *
     * @param nX total number of pixels in width
     * @param nY total number of pixels in height
     * @param j  column index of the pixel (from left to right)
     * @param i  row index of the pixel (from top to bottom)
     * @throws MissingResourceException if the RayTracer is missing
     */
    public void castRay(int nX, int nY, int j, int i) {
        if (rayTracer == null) {
            throw new MissingResourceException("RayTracer", "RayTracer", "RayTracer is missing");
        }
        Ray ray = constructRay(nX, nY, j, i);
        Color color = rayTracer.traceRay(ray);
        imageWriter.writePixel(j, i, color);
    }

    /**
     * Renders the image by iterating over all the pixels and coloring them according to the scene
     */
    public Camera renderImage() {
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nX; ++i)
            for (int j = 0; j < nY; ++j)
                castRay(nX, nY, j, i);
        return this;

    }

    @Override
    public Camera clone() {
        try {
            return (Camera) super.clone();
        } catch (CloneNotSupportedException e) {
            return null; // This should not happen since Camera implements Cloneable
        }
    }

    public static class Builder {
        private final Camera camera = new Camera();

        /**
         * Sets the location of the camera
         *
         * @param location the location point
         * @return this Builder instance
         */
        public Builder setLocation(Point location) {
            if (location == null) {
                throw new IllegalArgumentException("Location cannot be null");
            }
            camera.p0 = location;
            return this;
        }

        /**
         * Sets the direction of the camera
         *
         * @param vTo the direction vector
         * @param vUp the up vector
         * @return this Builder instance
         */
        public Builder setDirection(Vector vTo, Vector vUp) { //  מקבלת שני וקטורים, במידה ומקבילים זורק חריגה, אחרת מחשבים את הוקטור האנכי ומציבים
            if (vTo.crossProduct(vUp).length() == 0)
                throw new IllegalArgumentException("vTo and vUp are parallel");
            if (vTo == null || vUp == null)
                throw new IllegalArgumentException("Direction vectors cannot be null");
            if (!isZero(vTo.dotProduct(vUp)))
                throw new IllegalArgumentException("Direction vectors must be orthogonal");

            camera.vTo = vTo.normalize();
            camera.vUp = vUp.normalize();
            camera.vRight = vTo.crossProduct(vUp).normalize();
            return this;
        }

        /**
         * Sets the size of the view plane
         *
         * @param height the height of the view plane
         * @param width  the width of the view plane
         * @return this Builder instance
         */
        public Builder setVpSize(double height, double width) {
            if (height < 0 || width < 0)
                throw new IllegalArgumentException("The height and width of the view plane must be positive");
            camera.height = height;
            camera.width = width;
            return this;
        }

        /**
         * Sets the distance of the view plane
         *
         * @param distance the distance from the view plane
         * @return this Builder instance
         */
        public Builder setVpDistance(double distance) {
            if (distance < 0)
                throw new IllegalArgumentException("The distance from the view plane must be positive");
            camera.distance = distance;
            return this;
        }

        /**
         * Builds and returns the Camera object.
         *
         * @return the constructed Camera object
         * @throws MissingResourceException if any required resources are missing
         * @throws IllegalArgumentException if width, height, or distance are non-positive or if direction vectors are not orthogonal
         */
        public Camera build() {
            if (camera.p0 == null) {
                throw new MissingResourceException("Location", "Point", "Location point is missing");
            }
            if (camera.vTo == null || camera.vUp == null || camera.vRight == null) {
                throw new MissingResourceException("Direction vectors", "Vector", "Direction vectors are missing");
            }
            if (alignZero(camera.width) <= 0 || alignZero(camera.height) <= 0 || alignZero(camera.distance) <= 0) {
                throw new IllegalArgumentException("Width, height, and distance must be positive");
            }
            if (!isZero(camera.vRight.dotProduct(camera.vTo))) {
                throw new IllegalArgumentException("Direction vectors must be orthogonal");
            }
            camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();
            return (Camera) camera.clone();
        }

        /**
         * Sets the ImageWriter for the camera.
         *
         * @param imageWriter the ImageWriter to set
         * @return the Builder instance
         */
        public Builder setImageWriter(ImageWriter imageWriter) {
            camera.imageWriter = imageWriter;
            return this;
        }

        /**
         * Sets the RayTracer for the camera.
         *
         * @param rayTracer the RayTracer to set
         * @return the Builder instance
         */
        public Builder setRayTracer(RayTracerBase rayTracer) {
            camera.rayTracer = rayTracer;
            return this;
        }
    }
}
