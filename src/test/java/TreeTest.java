import org.junit.Assert;
import org.junit.Test;

public class TreeTest extends Assert {

    @Test
    public void searchTest(){
        int t = 3;
        boolean result;
        boolean expected = true;
        BTree tree = new BTree(t);
        result = tree.search(6);
        assertEquals(expected, result);

    }

}
