package scene;

import geometries.Geometries;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import primitives.Color;
import primitives.Point;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Parses an XML file to create a {@link Scene} object.
 * The XML file should define the scene's background color, ambient light, and geometries (spheres and triangles).
 */
public class SceneXMLParser {

    /**
     * Loads a scene from the specified XML file.
     *
     * @param filePath the path to the XML file
     * @return a {@link Scene} object initialized with data from the XML file, or {@code null} if an error occurs
     */
    public static Scene loadSceneFromFile(String filePath) {
        try {
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            String sceneName = "Scene from XML";
            Scene scene = new Scene(sceneName);

            // Parse background color
            Element sceneElement = (Element) doc.getElementsByTagName("scene").item(0);
            if (sceneElement != null) {
                String backgroundColor = sceneElement.getAttribute("background-color");
                scene.setBackground(parseColor(backgroundColor));
            } else {
                System.err.println("Scene element not found in the XML.");
                return null;
            }

            // Parse ambient light
            Element ambientLightElement = (Element) doc.getElementsByTagName("ambient-light").item(0);
            if (ambientLightElement != null) {
                String ambientLightColor = ambientLightElement.getAttribute("color");
                AmbientLight ambientLight = new AmbientLight(parseColor(ambientLightColor), 1.0);
                scene.setAmbientLight(ambientLight);
            } else {
                System.err.println("Ambient light element not found in the XML.");
                return null;
            }

            // Parse geometries
            Geometries geometries = new Geometries();
            NodeList spheresList = doc.getElementsByTagName("sphere");
            for (int i = 0; i < spheresList.getLength(); i++) {
                Element sphereElement = (Element) spheresList.item(i);
                Point center = parsePoint(sphereElement.getAttribute("center"));
                double radius = Double.parseDouble(sphereElement.getAttribute("radius"));
                geometries.add(new Sphere(center, radius));
            }

            NodeList trianglesList = doc.getElementsByTagName("triangle");
            for (int i = 0; i < trianglesList.getLength(); i++) {
                Element triangleElement = (Element) trianglesList.item(i);
                Point p0 = parsePoint(triangleElement.getAttribute("p0"));
                Point p1 = parsePoint(triangleElement.getAttribute("p1"));
                Point p2 = parsePoint(triangleElement.getAttribute("p2"));
                geometries.add(new Triangle(p0, p1, p2));
            }

            scene.setGeometries(geometries);

            return scene;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Parses a color string in the format "R G B" into a {@link Color} object.
     *
     * @param colorStr the color string
     * @return a {@link Color} object representing the parsed color
     * @throws IllegalArgumentException if the color string is invalid
     */
    private static Color parseColor(String colorStr) {
        if (colorStr == null || colorStr.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid color string: " + colorStr);
        }
        String[] rgb = colorStr.trim().split(" ");
        if (rgb.length != 3) {
            throw new IllegalArgumentException("Invalid color format: " + colorStr);
        }
        return new Color(
                Double.parseDouble(rgb[0]),
                Double.parseDouble(rgb[1]),
                Double.parseDouble(rgb[2])
        );
    }

    /**
     * Parses a point string in the format "X Y Z" into a {@link Point} object.
     *
     * @param pointStr the point string
     * @return a {@link Point} object representing the parsed point
     * @throws IllegalArgumentException if the point string is invalid
     */
    private static Point parsePoint(String pointStr) {
        if (pointStr == null || pointStr.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid point string: " + pointStr);
        }
        String[] coords = pointStr.trim().split(" ");
        if (coords.length != 3) {
            throw new IllegalArgumentException("Invalid point format: " + pointStr);
        }
        return new Point(
                Double.parseDouble(coords[0]),
                Double.parseDouble(coords[1]),
                Double.parseDouble(coords[2])
        );
    }
}
