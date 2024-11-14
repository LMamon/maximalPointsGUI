/*class intended to represent an immutable 2d point with x&y coordinates. Implements
comparison based on x coordinate and provides methods for access and comparison of points
Louis, M.
 */

import java.util.List;

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

    //returns lower point
    public boolean isLower(Coord point2) {
        return this.yCoord() < point2.yCoord() ||
                (this.yCoord() == point2.yCoord() && this.xCoord() < point2.xCoord());
    }

    @Override
    public int compareTo(Coord o) {
        return Double.compare(this.xCoord(), o.xCoord());
    }

    //sort list based on polarAngle
    public static List<Coord> sortPoints(List<Coord> points, Coord reference){
        points.sort((a, b) -> Double.compare(polarAngle(reference,a), polarAngle(reference, b)));
        return points;
    }
    //calculate polar angle
    private static double polarAngle(Coord origin, Coord point){
        return Math.atan2(point.y - origin.y, point.x - origin.x);
    }

    //returns cross product
    public static double crossProduct(Coord p1, Coord p2, Coord p3){
        return (p2.xCoord() - p1.xCoord()) * (p3.yCoord() - p1.yCoord() - (p2.yCoord() - p1.yCoord()) *
                (p3.xCoord() - p1.xCoord()));
    }

    //returns distance between 2points
    public static double distance(Coord p1, Coord p2){
        return Math.hypot(p2.xCoord() - p1.xCoord(), p2.yCoord() - p1.yCoord());
    }
}


