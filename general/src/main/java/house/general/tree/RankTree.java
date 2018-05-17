package house.general.tree;

public class RankTree {

    static class Node {
        int key;
        int count;

        Node left;
        Node right;

        Node(int key) {
            this.key = key;
            this.count = 0;
            this.left = null;
            this.right = null;
        }
    }

    Node root = null;

    RankTree() {

    }

    public void insert(int key) {
        root = insert(root, key);
    }

    Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }

        if (key < node.key) {
            node.count += 1;
            node.left = insert(node.left, key);
        } else {
            node.right = insert(node.right, key);
        }

        return node;
    }

    int getRank(int key) {
        return getRank(root, key);
    }

    int getRank(Node node, int key) {
        if (node == null) {
            return -1;
        }

        if (key == node.key) {
            return node.count;
        }

        if (key < node.key) {
            return getRank(node.left, key);
        } else {
            int rank = getRank(node.right, key);
            if (rank != -1) {
                return node.count + 1 + rank;
            }
            return rank;
        }
    }





}
