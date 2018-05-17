package house.general.tree;

public class Tree {

    class Node {
        int key;
        Node left;
        Node right;
    }

    public static void main(String[] args) {
        int[] inserts = {15, 6, 18, 3, 7, 17, 20, 2, 4, 13, 9};
        int i = 10;
        short a = -19;
        short b = (short) (a >>> 2);
        short x = (short) (b & 1);
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(b));
    }
}
