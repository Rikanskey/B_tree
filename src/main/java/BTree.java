import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BTree {
    private static int t;
    private BNode node;

    public static class BNode{
        private List<Integer> keys;
        private List<BNode> children;
        private BNode parent;

        BNode(BNode parent) {
            keys = new ArrayList<>();
            children = new ArrayList<>();
            this.parent = parent;
        }

        @Override
        public int hashCode() {
            int sum = 0;
            for (int i = 0; i < children.size(); i++)
                sum += children.get(i).hashCode();
            for (int i = 0; i < keys.size(); i++)
                sum += keys.get(i);
            return sum;
        }

        public List<BNode> getChildren() {
            return children;
        }

        public List<Integer> getKeys() {
            return keys;
        }

        public BNode getParent() {
            return parent;
        }

        public void setParent(BNode parent) {
            this.parent = parent;
        }


        public void setChildren(List<BNode> children) {
            this.children = children;
        }

        public void setKeys(List<Integer> keys) {
            this.keys = keys;
        }

        public void add_child(BNode child){
            children.add(child);
            child.setParent(this);
        }

        public void insert_key(Integer value){
            keys.add(value);
            Collections.sort(keys);
        }

        public void restruct(){
            if (keys.size() != 2*t-1)
                return;
            BNode first_child = new BNode(this);
            BNode second_child = new BNode(this);
            for (int i = 0; i < children.size()/2; i++){
                first_child.add_child(children.get(i));
                second_child.add_child(children.get(i+children.size()/2));
            }
            for (int i = 0; i < (keys.size()-1)/2; i++) {
                first_child.insert_key(keys.get(i));
                second_child.insert_key(keys.get((keys.size()+1)/2));
            }
            if (parent == null){
                Integer key = keys.get(t-1);
                keys = new ArrayList<>();
                keys.add(key);
                children.add(first_child);
                children.add(second_child);
            }
            else {
                Integer key = keys.get((keys.size()+1)/2 - 1);
                parent.insert_key(key);
                parent.restruct();
                parent.add_child(first_child);
                parent.add_child(second_child);
                parent.getChildren().remove(this);
            }
        }

        public boolean add_key(Integer value) {
            if (keys.size() == 0 && parent == null){
                keys.add(value);
                return true;
            }
            boolean flag = false;
            if (children.size() == 0) {
                for (int i = 0; i < keys.size() && !flag; i++) {
                    if (value < keys.get(i)) {
                        insert_key(value);
                        flag = true;
                    }
                }
                if (!flag && keys.size() < 2*t-1) {
                    insert_key(value);
                    flag = true;
                }
                restruct();
            }
            else {
                for (int i = 0; i < children.size() && !flag; i++) {
                    if (children.get(i).getKeys().get(children.get(i).getKeys().size()-1) > value)
                        flag = children.get(i).add_key(value);
                }
            }

            if (parent == null && !flag) {
                BNode child = children.get(children.size() - 1);

                while (child.getChildren().size() > 0)
                    child = child.getChildren().get(child.getChildren().size() - 1);
                child.insert_key(value);
                child.restruct();
            }
            return flag;
        }
    }

    BTree(int t){
        this.t = t;
        this.node = new BNode(null);
    }

    @Override
    public int hashCode() {
        return node.hashCode()+t;
    }

    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }

    public BNode getNode() {
        return node;
    }

    public void setNode(BNode node) {
        this.node = node;
    }

    public boolean search(int key) {
        BNode search_node = node;
        boolean flag = false;
        int index_node = 0;
        if (search_node.getKeys().get(search_node.getKeys().size() - 1) >= key) {
            for (int i = 0; i < search_node.getKeys().size() && !flag; i++)
                if (key == search_node.getKeys().get(i))
                    flag = true;
        }
            while (!flag) {
                for (BNode child : search_node.getChildren()) {
                    if (child.getKeys().get(child.getKeys().size() - 1)
                            >= key) {
                        for (Integer value : child.getKeys()) {
                            if (key == value) {
                                flag = true;
                                return flag;
                            }
                        }
                        index_node = search_node.getChildren().indexOf(child);
                        break;
                    }
                }
                    if (search_node.getChildren().size() != 0)
                        search_node = search_node.getChildren().get(index_node);
                    else
                        break;
            }
        return flag;
    }

    public BNode insert(Integer value){
        this.node.add_key(value);
        return this.node;
    }
}

