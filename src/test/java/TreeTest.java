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

    @Test
    public void insertThreeElements(){
        int t = 2;
        BTree tree = new BTree(t);
        int[] expected = {2, 3, 4};
        tree.insert(2);
        tree.insert(3);
        int[] result = tree.insert(4);
        assertArrayEquals(expected, result);
    }

    @Test
    public void insertFifeElements(){
        int t = 2;
        BTree tree = new BTree(t);
        int[] expected = {2, 3, 4, 5, 6};
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        int[] result = tree.insert(6);
        assertArrayEquals(expected, result);
    }
}
