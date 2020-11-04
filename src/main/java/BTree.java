import java.util.ArrayList;
import java.util.List;

public class BTree {
    private int t;
    private List<Integer> nodes;

    BTree(int t){
        this.t = t;
        this.nodes = new ArrayList<>();
    }

    public boolean search(int key){
        return true;
    }

    public List<Integer> insert(Integer value){
        this.nodes.add(value);
        return this.nodes;
    }
}
