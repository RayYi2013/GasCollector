import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by ray on 2014-08-19.
 */
public class InputDataProvider implements IInputDataProvider {
    private MapData[] _data;
    private DataReader _reader;
    public InputDataProvider(String fileName) throws FileNotFoundException {
        loadData(fileName);
    }

    private void loadData(String fileName) throws FileNotFoundException{
        //Scanner Example - read file line by line in Java using Scanner
        FileInputStream fis = new FileInputStream(fileName);
        Scanner scanner = new Scanner(fis);
        _reader = new DataReader(scanner);

        //test case number
        int[] line = _reader.readLine();
        int caseNumber = line[0];
        _data = new MapData[caseNumber];

        for(int i=0; i<caseNumber; i++){
            _data[i] = fillMapData();
        }
        scanner.close();
    }

    private MapData fillMapData(){
        MapData data = new MapData();
        //size of data
        int[] line = _reader.readLine();
        data.N = line[0];
        data.M = line[1];
        data.Data = new int[data.N][data.M];

        //start and end position
        line = _reader.readLine();
        data.En = new Position(line[0],line[1]);
        data.Ex = new Position(line[2],line[3]);

        //fill data
        for(int i = 0; i< data.N; i++){
            line = _reader.readLine();
            for(int j=0; j<data.M; j++){
                data.Data[i][j] = line[j];
            }
        }
        return data;
    }

        /**
     * get map data set from provider
     * @return array of MapData
     */
    public MapData[] GetMapDataSet(){
        return _data;
    }
}

class DataReader{
    Scanner _scanner;
    public DataReader( Scanner scanner){
        _scanner = scanner;
    }

    public int[] readLine(){
        String line = _scanner.nextLine();
        String[] list = line.split(" ");
        int[] data = new int[list.length];
        for(int i=0; i<list.length; i++){
            data[i] = Integer.parseInt(list[i]);
        }
        return data;
    }
}
