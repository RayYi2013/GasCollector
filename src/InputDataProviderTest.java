import junit.framework.TestCase;
import java.io.File;
import java.net.URL;

/**
 * Created by ray on 2014-08-19.
 */
public class InputDataProviderTest extends TestCase {
    public void testGetMapDataSet() throws Exception {
        //arrange
        URL url = getClass().getResource("smallTestData.txt");
//        URL url = getClass().getResource("100.txt");
        String name =url.getPath();

        //action
        InputDataProvider provider = new InputDataProvider(name);
        MapData[] data = provider.GetMapDataSet();

        //assert
        assertEquals("test case number", 2, data.length);

    }
}
