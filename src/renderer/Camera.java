package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;

import static primitives.Util.isZero;

/**
 * class camera
 */
public class Camera implements Cloneable  {

    private Point p0; // location of camera

    private Vector vUp;
    private Vector vTo;
    private Vector vRight;

    private double width=0.0;
    private double height=0.0;
    private double distance=0.0;

    private ImageWriter imageWriter;
    private RayTraceBase rayTracer;

    /**
     * empty constructor for camera
     */
    private Camera() { width = 0; height = 0; distance = 0; }

    public static Builder getBuilder() {
        return new Builder();
    }

    /**
     * Renders the image by iterating over all the pixels and coloring them according to the scene
     */
    public void renderImage() {
        for (int i = 0; i < imageWriter.getNy(); i++) {
            for (int j = 0; j < imageWriter.getNx(); j++) {
                // Assuming castRay needs pixel coordinates and other parameters
                castRay(imageWriter.getNx(), imageWriter.getNy(), j, i);
            }
        }
        // Ensure to remove or implement the following line if it's a placeholder
        // throw new UnsupportedOperationException();
    }

    private void castRay(int nX, int nY, int j, int i) {
        Ray ray=constructRay(nX, nY, j, i);
        Color color = rayTracer.traceRay(ray);
        imageWriter.writePixel(j,i,color);
    }

    /**
     * Writes the image to a file
     */
    public void writeToImage() {
        if (imageWriter == null) {
            throw new IllegalStateException("ImageWriter is not initialized");
        }

        imageWriter.writeToImage();
    }

    /**
     * Prints a grid of squares with the specified interval and color
     *
     * @param interval size of the grid squares
     * @param color color of the grid lines
     */
    public void printGrid(int interval, Color color) {
        for (int j = 0; j < imageWriter.getNx(); j++)
            for (int i = 0; i < imageWriter.getNy(); i++)
                if (isZero(j % interval) || isZero(i % interval))
                    imageWriter.writePixel(j, i, color);
    }

    public Ray constructRay(int nX, int nY, int j, int i) {
        if (nY == 0 || nX == 0) {
            throw new IllegalArgumentException("It is impossible to divide by 0");
        }

        Point Pc = p0.add(vTo.scale(distance));
        double Ry = height / nY;
        double Rx = width / nX;

        double Yi = -1 * (i - (nY - 1) / 2.0) * Ry;
        double Xj = -1*(j - (nX - 1) / 2.0) * Rx;

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



    public Point getLocation() {
        return p0;
    }

    public Vector getvTo() {
        return vTo;
    }

    public Vector getvRight() {
        return vRight;
    }

    public Vector getvUp() {
        return vUp;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getDistance() {
        return distance;
    }

    public static class Builder {
        private final Camera camera = new Camera();

        /**
         * Sets the location of the camera
         *
         * @param location the location point
         * @return this Builder instance
         */
        public Builder setLocation(Point location) {// מקבל נקודה ומציב במיקום של המצלמה
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
        public Builder setDirection(Vector Vto, Vector Vup) { //  מקבלת שני וקטורים, במידה ומקבילים זורק חריגה, אחרת מחשבים את הוקטור האנכי ומציבים
            if (Vto.crossProduct(Vup).length() == 0)
                throw new IllegalArgumentException("Vto and Vup are parallel");
            camera.vTo = Vto.normalize();
            camera.vUp = Vup.normalize();
            camera.vRight = Vto.crossProduct(Vup).normalize();
            return this;
        }

        /**
         * Sets the size of the view plane
         *
         * @param height the height of the view plane
         * @param width the width of the view plane
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
         * Sets the ImageWriter
         *
         * @param imageWriter the ImageWriter instance
         * @return this Builder instance
         */
        public Builder setImageWriter(ImageWriter imageWriter) {
            this.camera.imageWriter = imageWriter;
            return this;
        }

        /**
         * Sets the RayTracer
         *
         * @param rayTracer the RayTracer instance
         * @return this Builder instance
         */
        public Builder setRayTracer(SimpleRayTracer rayTracer) {
            this.camera.rayTracer = rayTracer;
            return this;
        }

        /**
         * Builds the Camera instance
         *
         * @return the built Camera instance
         */
        public Camera build() {
            String missingResource = "Missing Resource";
            if (camera.p0 == null)
                throw new MissingResourceException(missingResource, Camera.class.getSimpleName(), "location");
            if (camera.vTo == null || camera.vUp == null)
                throw new MissingResourceException(missingResource, Camera.class.getSimpleName(), "direction");
            if (camera.height == 0.0 || camera.width == 0.0)
                throw new MissingResourceException(missingResource, Camera.class.getSimpleName(), "vpSize");
            if (camera.distance == 0.0)
                throw new MissingResourceException(missingResource, Camera.class.getSimpleName(), "vpDistance");

            if (camera.vTo.crossProduct(camera.vUp).length() == 0)
                throw new IllegalArgumentException("Vto and Vup are parallel");
            if (camera.height < 0.0 || camera.width < 0.0)
                throw new IllegalArgumentException("Negative size");// checking the parameters himself
            if (camera.distance < 0.0)
                throw new IllegalArgumentException("Negative distance");

            if (camera.imageWriter == null)
                throw new MissingResourceException(missingResource, Camera.class.getName(), "imageWriter");

            if (camera.rayTracer == null)
                throw new MissingResourceException(missingResource, Camera.class.getName(), "rayTracer");


            try {
                return (Camera) camera.clone();
            }
            catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}