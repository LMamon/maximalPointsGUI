/*class intended to represent an immutable 2d point with x&y coordinates. Implements
comparison based on x coordinate and provides methods for access and comparison
Louis, M.
 */

public class Coord implements Comparable<Coord> {
    final double x, y;

    public Coord(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double xCoord(){
        return this.x;
    }
    public double yCoord(){
        return this.y;
    }

    public boolean isLowerLeft(Coord point2) {
        return point2.x < this.x && point2.y < this.y;
    }

    @Override
    public int compareTo(Coord o) {
        return Double.compare(this.x, o.x);
    }
}
