import junit.framework.TestCase;

/**
 * Created by ray on 2014-08-20.
 */
public class CollectorTest extends TestCase {
    public void testGeResult() {
        //arrange
        MapData data = mockData();

        //action
        Collector col = new Collector(data);
        String result = col.GetResult();

        //assert
        assertEquals("collector result", "7", result);
    }

    public void testGeResultImpossible() {
        //arragne
        MapData data = mockDataImpossible();

        //action
        Collector col = new Collector(data);
        String result = col.GetResult();

        //assert
        assertEquals("collector result", "Mission Impossible.", result);
    }

    private MapData mockData(){
        MapData data = new MapData();
        data.N = 4;
        data.M = 4;
        data.En = new Position(0, 2);
        data.Ex = new Position(3,2);
        data.Data = new long[4][4];
        data.Data[0][0] = -1;
        data.Data[0][1] = 1;
        data.Data[0][2] = 1;
        data.Data[0][3] = 2;

        data.Data[1][0] = 1;
        data.Data[1][1] = 1;
        data.Data[1][2] = 1;
        data.Data[1][3] = 1;

        data.Data[2][0] = 2;
        data.Data[2][1] = -1;
        data.Data[2][2] = -1;
        data.Data[2][3] = 1;

        data.Data[3][0] = 1;
        data.Data[3][1] = 1;
        data.Data[3][2] = 1;
        data.Data[3][3] = 1;

        return data;
    }

    private MapData mockDataImpossible(){
        MapData data = new MapData();
        data.N = 2;
        data.M = 3;
        data.En = new Position(0, 2);
        data.Ex = new Position(1,0);
        data.Data = new long[2][3];
        data.Data[0][0] = 2;
        data.Data[0][1] = -1;
        data.Data[0][2] = 5;

        data.Data[1][0] = 3;
        data.Data[1][1] = -1;
        data.Data[1][2] = 6;

        return data;
    }
}
