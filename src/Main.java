import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws FileNotFoundException  {
        IInputDataProvider provider = new InputDataProvider(args[0]);
        for(MapData data : provider.GetMapDataSet()){
            System.out.println((new Collector(data)).GetResult());
        }

    }

}
