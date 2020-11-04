public class BTree {
    private int t;
    private int[] nodes;

    BTree(int t){
        this.t = t;
        this.nodes = new int[2*this.t-1];
    }

    public boolean search(int key){
        return true;
    }

    public int[] insert(int value){
        for (int i=0; i<2*this.t-1; i++)
            if (this.nodes[i] == 0) {
                this.nodes[i] = value;
                break;
            }
        return this.nodes;
    }
}
