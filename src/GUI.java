/*class is intended to generate the UI/handle events for interactivity
Louis, M.
 */

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import java.util.ArrayList;

public class GUI extends javafx.scene.layout.Pane {
    public GUI(ArrayList<Coord> points) {
        //pane size
        this.setPrefSize(500, 500);
        ArrayList<Coord> maximals = new ArrayList<>();

        // Iterate through each point
        for (int i =0; i < points.size(); i++) {

            //add circles
            Coord point = points.get(i);
            Circle circle = new Circle(point.xCoord(), point.yCoord(), 5);
            this.getChildren().add(circle);

            //compare the current point with other points to determine if maximal
            for (int j=0 ; j < points.size(); j++) {
                //skip itself
                if (i != j){
                    Coord point2 = points.get(j);
                }
                //if point is maximal add it to list
                if (point.isLowerLeft(points.get(j))) {
                    maximals.add(point);
                }
            }
        }
        //add lines connecting maximal points
        for (int l = 0; l < maximals.size(); l++) {
            Coord sLine = maximals.get(l);
            Coord eLine = maximals.get(l+1);
            //add lines to pane
            Line line = new Line(sLine.xCoord(), sLine.yCoord(), eLine.xCoord(), eLine.yCoord());
            this.getChildren().add(line);
        }
    }

}
