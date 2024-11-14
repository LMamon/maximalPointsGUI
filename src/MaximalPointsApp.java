/*defines scene that conjavjavac tains the pane which is created with the initial set of
points that are read in from the points.txt file
Louis, M
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;

public class MaximalPointsApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ArrayList<Coord> initialPoints = readPointsFile();
        GUI guiPanel = new GUI(initialPoints);
        Scene scene = new Scene(guiPanel);

        primaryStage.setTitle("Maximal Visualizations");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ArrayList<Coord> readPointsFile() throws IOException {
        ArrayList<Coord> points = new ArrayList<>();
        //reader
        try (BufferedReader reader = new BufferedReader(new FileReader("src/points.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\s");
                points.add(new Coord(Double.parseDouble(tokens[0]), Double.parseDouble(tokens[1])));
            }
        } catch (IOException e) {
            System.err.println("Error reading file " + "points.txt");
        }
        return points;
        }

    public static void main(String[] args) {
        launch(args);
    }

}