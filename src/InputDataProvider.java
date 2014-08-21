import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by ray on 2014-08-19.
 */
public class InputDataProvider  {
    private MapData[] _data; //map data
    private DataReader _reader;  //input file reader

    /**
     * load map data from file
     * @param fileName  full path
     */
    public InputDataProvider(String fileName) {
        //init data
        _data = null;
        try {
            loadData(fileName);
        }
        catch(Exception ex){}
    }

    /**
     * load data from file
     * @param fileName full path
     * @throws FileNotFoundException
     */
    private void loadData(String fileName) throws FileNotFoundException{
        //Scanner Example - read file line by line in Java using Scanner
        FileInputStream fis = new FileInputStream(fileName);
        Scanner scanner = new Scanner(fis);
        _reader = new DataReader(scanner);

        //test case number
        long[] line = _reader.readLine();
        int caseNumber = (int)line[0];
        _data = new MapData[caseNumber];

        //fill case one by one
        for(int i=0; i<caseNumber; i++){
            _data[i] = fillMapData();
        }
        scanner.close();
    }

    /**
     * fill single case from file
     * @return
     */
    private MapData fillMapData(){
        MapData data = new MapData();
        //size of data
        long[] line = _reader.readLine();
        data.N = (int) line[0];
        data.M = (int) line[1];
        data.Data = new long[data.N][data.M];

        //start and end position
        line = _reader.readLine();
        data.En = new Position((int) line[0],(int) line[1]);
        data.Ex = new Position((int) line[2], (int)line[3]);

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

    /**
     * read single line from file, then transform to long array
     * @return
     */
    public long[] readLine(){
        String line = _scanner.nextLine();
        String[] list = line.split(" ");
        long[] data = new long[list.length];
        for(int i=0; i<list.length; i++){
            data[i] = Long.parseLong(list[i]);
        }
        return data;
    }
}
