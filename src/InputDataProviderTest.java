import junit.framework.TestCase;
import java.io.File;
import java.net.URL;

/**
 * Created by ray on 2014-08-19.
 */
public class InputDataProviderTest extends TestCase {
    public void testGetMapDataSet() throws Exception {
        URL url = getClass().getResource("smallTestData.txt");
        InputDataProvider provider = new InputDataProvider(url.getPath());
        MapData[] data = provider.GetMapDataSet();

        assertEquals("test case number", 2, data.length);

    }
}
