/*class intended to represent an immutable 2d point with x&y coordinates. Implements
comparison based on x coordinate and provides methods for access and comparison of points
Louis, M.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Coord implements Comparable<Coord> {
    final double x, y;
    //constructor
    public Coord(double x, double y) {
        this.x = x;
        this.y = y;
    }
    //getters
    public double xCoord(){
        return this.x;
    }
    public double yCoord(){
        return this.y;
    }

    //returns lower point
    public boolean isLower(Coord point2) {
        return point2.xCoord() < this.xCoord() && point2.yCoord() < this.yCoord();
    }

    @Override
    public int compareTo(Coord o) {
        return Double.compare(this.xCoord(), o.xCoord());
    }

    public static Coord leftMost(List<Coord> points) {
        Coord p1 = points.get(0);
        for (Coord point : points) { //if point is left most assign it to p1
            if (point.xCoord() < p1.xCoord() || (point.xCoord() == p1.xCoord() && point.yCoord() < p1.yCoord())) {
                p1 = point;
            }
        }
        return p1;
    }
    //calculate maximal points
    public static ArrayList<Coord> findMaximals(ArrayList<Coord> points) {
        ArrayList<Coord> maximals = new ArrayList<>();
        //sort based on x
        points.sort((p1, p2) -> Double.compare(p2.xCoord(), p1.xCoord()));
        //minY to track lowest y
        double minY = Double.POSITIVE_INFINITY;
        for (Coord p : points) {
            if (p.yCoord() < minY) { //if current point has lower y than previous minimum add to maximals
                maximals.add(p);
                minY = p.yCoord();
            }
        }
        //added maximals from highest x to lowest x, reverse the list to sort
        Collections.reverse(maximals);
        return maximals;
    }

    //returns distance between 2points
    public static double distance(Coord p1, Coord p2){
        return Math.hypot(p2.xCoord() - p1.xCoord(), p2.yCoord() - p1.yCoord());
    }
}
