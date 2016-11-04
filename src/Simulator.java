import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by emiliacabrera on 9/27/16.
 */
public class Simulator {

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


    public static void main (String[] args)throws IOException {

        ArrayList<Body> planets = new ArrayList<>();

        String fileName = "planets.txt";

        String[] lines = readFile(fileName);

        String line;

        List<String> bodyParts;



        for(int i=0; i<lines.length; i++){
            line = lines[i];

            bodyParts = Arrays.asList(line.split(","));
            int j = bodyParts.size();


        }

    }
}
