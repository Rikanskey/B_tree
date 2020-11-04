import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TreeTest extends Assert {

    @Test
    public void searchTest(){
        int t = 2;
        boolean result;
        boolean expected = true;
        BTree tree = new BTree(t);
        result = tree.search(6);
        assertEquals(expected, result);
    }

    @Test
    public void insertTest(){
        int t = 2;
        BTree tree = new BTree(t);
        int[] result = tree.insert(2);
        int[] expected = {2};
        assertArrayEquals(result, expected);
    }
}
