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
        //find leftmost point
        Coord sPoint = Coord.leftMost(points);

        //sort points and find maximals
        maximals = Coord.findMaximals(points);
        updatePane();
    }

    private void updatePane() {
        getChildren().clear();
        //generate points
        for (Coord point : points) {
            Circle circle = new Circle(point.xCoord(), point.yCoord(), 5);
            getChildren().add(circle);
        }

        if (maximals.size() > 1) {
            for (int l = 0; l < maximals.size() -1; l++) {
                Coord sLine = maximals.get(l);
                Coord eLine = maximals.get((l + 1) % maximals.size());
                //add lines to pane
                Line line = new Line(sLine.xCoord(), sLine.yCoord(), eLine.xCoord(), eLine.yCoord());
                getChildren().add(line);
            }
        }
    }
}
