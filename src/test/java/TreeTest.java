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
    public void insertFiveNodesElements()
    {
        int t = 2;
        BTree tree = new BTree(t);

        BTree.BNode expected = new BTree.BNode();
        expected.setKeys(Arrays.asList(2, 3, 4));
        BTree.BNode expected_child = new BTree.BNode();
        expected_child.setKeys(Arrays.asList(5, 6));
        expected.add_child(expected_child);

        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);

        assertTrue(expected.getKeys().equals(tree.getNode().getKeys()) &&
                expected.getChildren().get(0).getKeys().equals(tree.getNode().getChildren().get(0).getKeys()));
    }
}
