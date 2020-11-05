import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;


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

    @Test
    public void insertFiveNodesSortElements()
    {
        int t = 2;
        BTree tree = new BTree(t);

        BTree.BNode expected = new BTree.BNode();
        expected.setKeys(Arrays.asList(2, 3, 4));
        BTree.BNode expected_child = new BTree.BNode();
        expected_child.setKeys(Arrays.asList(5, 6));
        expected.add_child(expected_child);

        tree.insert(4);
        tree.insert(3);
        tree.insert(2);
        tree.insert(6);
        tree.insert(5);


        assertTrue(expected.getKeys().equals(tree.getNode().getKeys()) &&
                expected.getChildren().get(0).getKeys().equals(tree.getNode().getChildren().get(0).getKeys()));
    }

    @Test
    public void insertFiveNodesSortSearchElements()
    {
        int t = 2;
        BTree tree = new BTree(t);
        BTree.BNode test_node = new BTree.BNode();
        test_node.setKeys(Arrays.asList(3));
        BTree.BNode test_child_1 = new BTree.BNode();
        test_child_1.setKeys(new ArrayList<>(Arrays.asList(1, 2)));
        BTree.BNode test_child_2 = new BTree.BNode();
        test_child_2.setKeys(new ArrayList<>(Arrays.asList(4, 6)));
        test_node.add_child(test_child_1);
        test_node.add_child(test_child_2);
        tree.setNode(test_node);

        BTree.BNode expected = new BTree.BNode();
        expected.setKeys(Arrays.asList(3));
        BTree.BNode expected_child_1 = new BTree.BNode();
        expected_child_1.setKeys(new ArrayList<>(Arrays.asList(1, 2)));
        BTree.BNode expected_child_2 = new BTree.BNode();
        expected_child_2.setKeys(new ArrayList<>(Arrays.asList(4, 5, 6)));
        expected.add_child(expected_child_1);
        expected.add_child(expected_child_2);

        tree.insert(5);


        assertTrue(expected.getKeys().equals(tree.getNode().getKeys()) &&
                expected.getChildren().get(1).getKeys().equals(tree.getNode().getChildren().get(1).getKeys()));
    }
}
