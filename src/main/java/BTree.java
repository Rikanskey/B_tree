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
        boolean flag = true;
        for (int i=0; i<this.nodes.length && flag; i++)
            if (this.nodes[i] == 0) {
                this.nodes[i] = value;
                flag = false;
            }
        if (flag){
            int[] new_nodes_array = new int[this.nodes.length+1];
            for (int i = 0; i < this.nodes.length; i++)
                new_nodes_array[i] = this.nodes[i];
            new_nodes_array[this.nodes.length] = value;
            this.nodes = new_nodes_array;
        }
        return this.nodes;
    }
}
