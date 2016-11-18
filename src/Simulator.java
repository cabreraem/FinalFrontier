import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static javafx.application.Application.launch;

/**
 * Created by emiliacabrera on 9/27/16.
 */
public class Simulator {

    public Universe world;

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
    public static createWorld(String fileName)throws IOException{
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
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        primaryStage.setScene(scene);

        for(int i =0; i< world.getBodies().size(); i++){

        }
        primaryStage.show();
    }
}
