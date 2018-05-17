package house.general;

import javafx.util.Pair;

public class UnionFind {
    class Node {
        int parent;
        int size;

        Node(int parent, int size) {
            this.parent = parent;
            this.size = size;
        }
    }
    Node[] arr;

    public UnionFind(int n) {
        this.arr = new Node[n];
        //initialize
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Node(i, 1);
        }
    }

    public void union(int i, int j) {
        Node rootI = findRootAndSize(i);
        Node rootJ = findRootAndSize(j);

        if (rootI.parent != rootJ.parent) {
            if (rootI.size < rootJ.size) {
                rootI.parent = rootJ.parent;
            } else {
                rootJ.parent = rootI.parent;
            }
        }
    }

    public int find(int i, int j) {
        int parentI = (findRootAndSize(i)).parent;
        int parentJ = findRootAndSize(j).parent;
        if (parentI == parentJ) {
            return parentI;
        } else {
            return -1;
        }
    }

    private Node findRootAndSize(int i) {
        while (i > 0 && i < arr.length && arr[i].parent != i) {
            i = arr[i].parent;
        }
        return arr[i];
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10);
        System.out.print(uf.find(0, 1));
        System.out.print(uf.find(4, 5));
        uf.union(1, 6);
        uf.union(3, 1);
        uf.union(4, 5);
        uf.union(1, 4);
        System.out.print(uf.find(1, 4));

    }
}
