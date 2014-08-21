import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws FileNotFoundException  {
        //get input file's full path
        String name = "";
        try {
            URL url = Main.class.getResource(args[0]);
            name = url.getPath();
        }
        catch (Exception ex){
            System.out.println("Could not find input file.");
            return;
        }

        InputDataProvider provider = new InputDataProvider(name);
        MapData[] dataSet = provider.GetMapDataSet();
        if(dataSet != null){
            for(MapData data : dataSet ){
                System.out.println((new Collector(data)).GetResult());
            }
        }
        else{
            System.out.println("Content of input file is wrong!");
        }

    }

}
