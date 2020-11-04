import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
    public void insertFifeListElements(){
        int t = 2;
        BTree tree = new BTree(t);
        List<Integer> expected = Arrays.asList(2, 3, 4, 5, 6);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        List<Integer> result = tree.insert(6);
        assertEquals(expected, result);
    }
}
