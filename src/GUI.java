/*class is intended to generate the UI/handle events for interactivity
Louis, M.
 */
import javafx.scene.layout.Pane;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import java.util.ArrayList;
import java.util.List;

public class GUI extends Pane {
    ArrayList<Coord> points = new ArrayList<>();
    ArrayList<Coord> maximals = new ArrayList<>();

    //Constructor
    public GUI(ArrayList<Coord> initialPoints) {
        this.setPrefSize(500, 500);    //pane size
        points.addAll(initialPoints);
        recomputeMaximals();
        updatePane();
        mousePoints();
    }

    private void mousePoints() {
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    //add point

                    Coord point = new Coord(event.getX(), event.getY());
                    points.add(point);
                } else if (event.getButton() == MouseButton.SECONDARY) {
                    //remove point
                    Coord nPoint = null;
                    double minDist = Double.MAX_VALUE;
                    for (Coord point : points) {
                        double dist = Coord.distance(new Coord(event.getX(), event.getY()), point);
                        if (dist < minDist) {
                            minDist = dist;
                            nPoint = point;
                        }
                    }
                    if (nPoint != null) {
                        points.remove(nPoint);
                    }
                }
                recomputeMaximals();
                updatePane();
            }
        });
    }

    private void recomputeMaximals() {
        maximals.clear();

        //find lowest point
        Coord sPoint = points.get(0);
        for (Coord point : points) {
            if (point.isLower(sPoint)) {
                sPoint = point;
            }
        }
        //sort points by starting point
        List<Coord> sorted = Coord.sortPoints(new ArrayList<>(points), sPoint);
        maximals.add(sPoint);
        Coord currPoint = sPoint;

        //iterate clockwise through points
        while (true) {
            Coord nxPoint = null;
            for (Coord point : sorted) {
                if (point == currPoint) continue;
                if(nxPoint == null) {
                    nxPoint = point;
                } else {
                    double cProduct = Coord.crossProduct(currPoint, nxPoint, point);
                    if (cProduct < 0 ||
                            (cProduct == 0 && Coord.distance(currPoint, point) > Coord.distance(currPoint, nxPoint))) {
                        nxPoint = point;
                    }
                }
            }
            if (nxPoint == sPoint) {
                break;
            } else {
                maximals.add(nxPoint);
                currPoint = nxPoint;
            }
        }
        }


    private void updatePane() {
        getChildren().clear();
        //generate points
        for (Coord point : points) {
            Circle circle = new Circle(point.xCoord(), point.yCoord(), 15);
            getChildren().add(circle);
        }

        if (maximals.size() > 1) {
            for (int l = 0; l < maximals.size(); l++) {
                Coord sLine = maximals.get(l);
                Coord eLine = maximals.get((l + 1) % maximals.size());
                //add lines to pane
                Line line = new Line(sLine.xCoord(), sLine.yCoord(), eLine.xCoord(), eLine.yCoord());
                getChildren().add(line);
            }
        }
    }

}