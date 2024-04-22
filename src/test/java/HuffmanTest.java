import org.example.HuffmanCompression;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HuffmanTest {
    HuffmanCompression h;
    @Before
    public void init() {
        h= new HuffmanCompression();
    }
    @Test
    public void frequencyTableTest() {
        int[] frequencyTable= h.frequencyTable("aaabbg".toCharArray());
        Assert.assertEquals(h.CHARACTER_LIMIT, frequencyTable.length); //create 256 length char array to keep track of all chars
        Assert.assertEquals(frequencyTable[97], 3);
        Assert.assertEquals(frequencyTable[98],2);
        Assert.assertEquals(frequencyTable[103],1);

    }
}
