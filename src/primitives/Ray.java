package primitives;

/**
 * Ray class represents a directed line segment in Euclidean space.
 * It is defined by a starting point (head) and a direction vector.
 * @author Shay and Asaf
 */
public class Ray {
    private final Double3 head;
    private final Vector direction;

    /**
     * Constructor that creates a new ray given its head point and direction vector.
     * @param head The starting point of the ray.
     * @param dir The direction vector of the ray.
     */
    public Ray(Double3 head, Vector dir) {
        this.head = head;
        this.direction = dir.normalize();
    }

    /**
     * Checks if this ray is equal to another object.
     * @param o The object to compare with this ray.
     * @return true if the object is a ray and has the same head and direction, false otherwise.
     */
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;

        return head.equals(ray.head) && direction.equals(ray.direction);
    }

    /**
     * Returns a hash code value for the ray.
     * @return A hash code value for this ray.
     */
    @Override
    public int hashCode() {
        int result = head.hashCode();
        result = 31 * result + direction.hashCode();
        return result;
    }

    /**
     * Returns a string representation of the ray.
     * @return A string that describes the ray.
     */
    @Override
    public String toString() {
        return "Ray{" +
                "head=" + head +
                ", direction=" + direction +
                '}';
    }
}