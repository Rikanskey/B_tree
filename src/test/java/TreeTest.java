import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;


public class TreeTest extends Assert {

    @Test
    public void insertElementsTree(){
        int t = 2;
        BTree tree = new BTree(t);

        BTree expectedTree = new BTree(t);
        BTree.BNode expectedNode_root = new BTree.BNode(null);
        expectedNode_root.setKeys(new ArrayList<>(Arrays.asList(2, 4)));
        BTree.BNode expectedNode_1 = new BTree.BNode(expectedNode_root);
        expectedNode_1.setKeys(new ArrayList<>(Arrays.asList(1)));
        expectedNode_root.add_child(expectedNode_1);
        BTree.BNode expectedNode_2 = new BTree.BNode(expectedNode_root);
        expectedNode_2.setKeys(new ArrayList<>(Arrays.asList(3)));
        expectedNode_root.add_child(expectedNode_2);
        BTree.BNode expectedNode_3 = new BTree.BNode(expectedNode_root);
        expectedNode_3.setKeys(new ArrayList<>(Arrays.asList(5, 6)));
        expectedNode_root.add_child(expectedNode_3);
        expectedTree.setNode(expectedNode_root);

        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);

        assertEquals(expectedTree, tree);
    }

    @Test
    public void searchElement(){
        int t = 2;
        BTree tree = new BTree(t);

        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        boolean result = tree.search(6);

        assertFalse(result);
    }

    @Test
    public void deleteElementTree(){
        int t = 2;
        BTree tree = new BTree(t);

        BTree treeExpected = new BTree(t);
        BTree.BNode expectedNode = new BTree.BNode(null);
        expectedNode.setKeys(new ArrayList<>(Arrays.asList(2, 5)));
        BTree.BNode expectedFirstChild = new BTree.BNode(expectedNode);
        expectedFirstChild.setKeys(new ArrayList<>(Arrays.asList(1)));
        expectedNode.add_child(expectedFirstChild);
        BTree.BNode expectedSecondChild = new BTree.BNode(expectedNode);
        expectedSecondChild.setKeys(new ArrayList<>(Arrays.asList(4)));
        expectedNode.add_child(expectedSecondChild);
        BTree.BNode expectedThirdChild = new BTree.BNode(expectedNode);
        expectedThirdChild.setKeys(new ArrayList<>(Arrays.asList(6)));
        expectedNode.add_child(expectedThirdChild);
        treeExpected.setNode(expectedNode);

        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.delete(3);

        assertEquals(treeExpected, tree);
    }
}
