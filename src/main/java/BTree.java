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
            return sum+children.size();
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

        public void remove(){
            parent.delete_child(this);
            parent = null;
            keys = null;
        }

        public void delete_child(BNode child){
            children.remove(child);
        }

        public void insert_key(Integer value){
            keys.add(value);
            Collections.sort(keys);
        }

        public void delete_key(Integer value){
            keys.remove(value);
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

    public void delete_node(BNode bnode, Integer key){
        Integer left_right = 0;
        boolean flag = false;
        if (bnode.getChildren().size() == 0){
            BNode bNodeNeighbor = bnode;
            Integer key_del = -1;
            for (int i = 1; i < bnode.getParent().getKeys().size() && key_del == -1; i++) {
                if (key > bnode.getParent().getKeys().get(i))
                    key_del = bnode.getParent().getKeys().get(i);
            }
            if (bnode.getParent().getChildren().indexOf(bnode) != 0) {
                    bNodeNeighbor = bnode.getParent().getChildren().get(bnode.getParent().getChildren().indexOf(bnode) - 1);
                    if (bNodeNeighbor.getKeys().size() > t-1) {
                        bnode.getParent().delete_key(key_del);
                        bnode.insert_key(key_del);
                        bnode.getParent().insert_key(bNodeNeighbor.getKeys().get(bNodeNeighbor.getKeys().size()-1));
                        bNodeNeighbor.delete_key(bNodeNeighbor.getKeys().get(bNodeNeighbor.getKeys().size()-1));
                        flag = true;
                    }
                    else
                        left_right = -1;
            }
            else if (bnode.getParent().getChildren().indexOf(bnode) != bnode.getParent().getChildren().size()-1 && !flag) {
                bNodeNeighbor = bnode.getParent().getChildren().get(bnode.getParent().getChildren().indexOf(bnode) + 1);
                if (bNodeNeighbor.getKeys().size() > t-1) {
                    bnode.getParent().delete_key(key_del);
                    bnode.insert_key(key_del);
                    bnode.getParent().insert_key(bNodeNeighbor.getKeys().get(0));
                    bNodeNeighbor.delete_key(bNodeNeighbor.getKeys().get(0));
                    flag = true;
                }
                else
                    left_right = 1;
            }
            else if (!flag) {
                    for (Integer value : bnode.keys) {
                        bnode.getParent().getChildren().get(bnode.getParent().getChildren().indexOf(bnode) + left_right).insert_key(value);
                    }
                    bnode.getParent().getChildren().get(bnode.getParent().getChildren().indexOf(bnode) + left_right).insert_key(key_del);
                    bnode.getParent().delete_key(key_del);
                    bnode.remove();
            }
        }
        else{
            BNode right_child = bnode;
            BNode left_child = bnode;
            for (int i = 0; i < bnode.getChildren().size(); i++){
                if (key > bnode.getChildren().get(i).getKeys().get(0)) {
                    right_child = bnode.getChildren().get(i);
                    left_child = bnode.getChildren().get(i - 1);
                    break;
                }
            }
            if (left_child.getKeys().size() > t-1){
                Integer child_key = left_child.getKeys().get(left_child.getKeys().size()-1);
                bnode.insert_key(child_key);
                left_child.delete_key(child_key);
                delete_node(left_child,child_key);
            }
            else if (right_child.getKeys().size() > t-1){
                Integer child_key = right_child.getKeys().get(0);
                bnode.insert_key(child_key);
                right_child.delete_key(child_key);
                delete_node(right_child, child_key);
            }
            else {
                for (Integer value: right_child.getKeys()){
                    left_child.insert_key(value);
                }
                right_child.remove();
                delete_node(left_child, key);
            }
        }
    }

    public void delete(Integer key){
        BNode search_node = node;
        int index_node = 0;
        if (search_node.getKeys().get(search_node.getKeys().size() - 1) >= key) {
            for (int i = 0; i < search_node.getKeys().size(); i++)
                if (key.equals(search_node.getKeys().get(i))){
                    search_node.delete_key(key);
                    if (search_node.getKeys().size() <= t-1)
                        delete_node(search_node, key);
                    return;
                }

        }
        while (true) {
            for (BNode child : search_node.getChildren()) {
                if (child.getKeys().get(child.getKeys().size() - 1)
                        >= key) {
                    for (Integer value : child.getKeys()) {
                        if (key.equals(value)) {
                            child.delete_key(key);
                            if (child.getKeys().size() <= t-1)
                                delete_node(child, key);
                            return;
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
    }

    public BNode insert(Integer value){
        this.node.add_key(value);
        return this.node;
    }
}

