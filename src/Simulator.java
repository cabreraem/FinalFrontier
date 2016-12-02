import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static javafx.application.Application.launch;
import static javax.print.attribute.standard.Chromaticity.COLOR;

/**
 * Created by emiliacabrera on 9/27/16.
 */
public class Simulator extends Application {

    private static Universe world;
    private Stage primaryStage;

    public static int getFileSize(String fileName)throws IOException{
        Scanner input = new Scanner(new FileReader(fileName));
        int size=0;
        while(input.hasNextLine()){
            size++;
            input.nextLine();
        }

        input.close();
        return size;
    }

    public static String[] readFile(String fileName)throws IOException{
        int size = getFileSize(fileName);
        String[] list = new String[size];

        Scanner input = new Scanner(new FileReader(fileName));
        int i = 0;
        String line;
        while(input.hasNextLine()){
            line = input.nextLine();
            list[i] = line;
            i++;
        }

        input.close();
        return list;
    }


    //initializes universe if given bodies with only 4 variables (mass, axis, velocity, radius)
    public static void createWorld(String fileName)throws IOException{
        ArrayList<Body> planets = new ArrayList<>();

        Body planet;

        String[] lines = readFile(fileName);
        List<String> bodyParts;
        List<Double> stats;

        Double radius = 0.0;

        for(String line: lines){
            bodyParts = Arrays.asList(line.split(","));

            stats = new ArrayList();

            for(String s: bodyParts){
                Double temp = Double.parseDouble(s);
                stats.add(temp);
            }

            planet = new Body(stats.get(0), stats.get(1), stats.get(2), stats.get(3));
            planets.add(planet);

            radius = 2*stats.get(2);
        }

        world = new Universe(radius, planets);


    }


    public static void main (String[] args)throws Exception {

        String fileName = "planets.txt";

        createWorld(fileName);

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("The Final Frontier");


        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        primaryStage.setScene(scene);

        Group circles = new Group();

        Circle circle= new Circle();

        double radius = world.getBodies().get(0).getRadius()/1000;
        double sunDist = radius + 400;
        circle.setCenterX(400);
        circle.setCenterY(300);
        circle.setRadius(radius);
        circle.setFill(Color.YELLOW);
        circles.getChildren().add(circle);

        Double distance;

        for(int i =1; i< world.getBodies().size(); i++){
            Body temp = world.getBodies().get(i);

            circle= new Circle();

            radius = Math.log(temp.getRadius())/Math.log(2);

            distance = sunDist + Math.log(temp.getAxis())/Math.log(2);

            circle.setCenterX(distance);
            circle.setCenterY(300);
            circle.setRadius(radius);
            circle.setFill(Color.ORANGERED);

            circles.getChildren().add(circle);
        }

        root.getChildren().add(circles);
        primaryStage.show();
    }
}
