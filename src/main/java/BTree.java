import java.util.ArrayList;
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

        public boolean add_key(Integer value){
            if (2*t-1 == keys.size()){
                boolean flag = false;
                for (int i = 0; i < children.size() && !flag; i++)
                    flag = children.get(i).add_key(value);
                if (!flag){
                    BNode child = new BNode();
                    child.add_key(value);
                    children.add(child);
                    return true;
                }
            }
           else {
                keys.add(value);
                return true;
           }
           return true;
        }


    }

    BTree(int t){
        this.t = t;
        this.node = new BNode();
    }

    public BNode getNode() {
        return node;
    }

    public boolean search(int key){
        return true;
    }

    public BNode insert(Integer value){
        this.node.add_key(value);
        return this.node;
    }
}
