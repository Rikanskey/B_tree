import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BTree {
    private static int t;
    private BNode node;

    public static class BNode{
        private List<Integer> keys;
        private List<BNode> children;

        BNode(){
            keys = new ArrayList<>();
            children = new ArrayList<>();
        }

        public List<BNode> getChildren() {
            return children;
        }

        public List<Integer> getKeys() {
            return keys;
        }

        public void setChildren(List<BNode> children) {
            this.children = children;
        }

        public void setKeys(List<Integer> keys) {
            this.keys = keys;
        }

        public void add_child(BNode child){
            children.add(child);
        }

        public boolean add_key(Integer value) {
            boolean flag = false;
            if (children.size() == 0) {
                for (int i = 0; i < keys.size() && !flag; i++) {
                    if (value < keys.get(i)) {
                        keys.add(value);
                        Collections.sort(keys);
                        flag = true;
                    }
                }
            }
            for (int i = 0; i < children.size() && !flag; i++) {
                flag = children.get(i).add_key(value);
            }
            return flag;
        }
    }

    BTree(int t){
        this.t = t;
        this.node = new BNode();
    }

    public BNode getNode() {
        return node;
    }

    public void setNode(BNode node) {
        this.node = node;
    }

    public boolean search(int key){
        return true;
    }

    public BNode insert(Integer value){
        this.node.add_key(value);
        return this.node;
    }
}
