package house.general.tree;

public class BST {

    class Node {
        Node left;
        Node right;
        int key;
        int count;

        public Node(int key, int count) {
            this.key = key;
            this.count = 1;
        }

    }

    Node root = null;

    public Node insert(int key) {
        return insert(root, key);
    }

    Node insert(Node node, int key) {
        if (node == null) {
            node = new Node(key, 1);
            if (root == null) {
                root = node;
            }
        } else if (key < node.key) {
            node.left = insert(node.left, key);
            node.count += 1;
        } else {
            node.right = insert(node.right, key);
            node.count += 1;
        }
        return node;
    }

    Node pred(Node node) {
        //or firsst right parent
        //while am the left child keep going
        //end with right child ..his parent is pred
        if (node.left != null) {
            return max(node.left);
        }

        Node ptr = root;
        Node prev = null;
        Node pred = null;
        while (ptr != null && ptr.key != node.key) {
            if (ptr.key < node.key) {
                ptr = ptr.left;
                pred = prev;
            } else {
                prev = ptr;
                ptr = ptr.right;
            }
        }
        pred = prev;

        return pred;
    }

    Node succ(Node node) {
        //or first left parent
        //while am the right child keep going
        //end up with left child and his parent is the succ
        if (node.right != null) {
            min(node.right);
        }

        Node ptr = root;
        Node prev = null;
        Node succ = null;
        while (ptr != null && ptr.key != node.key) {
            if (node.key < ptr.key) {
                prev = ptr;
                ptr = ptr.left;
            } else if (node.key > ptr.key) {
                succ = prev;
                ptr = ptr.right;
            }
        }
        succ = prev;
        return succ;
    }

    Node delete(int key, Node current, Node parent) {
        if (current == null) return null;
        if (current.key == key) {
            if (current.left == null && current.right == null) {
                return null;
            } else if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            } else {
                Node succ = succ(current);
                delete(succ.key, current.right, current);
                current.key = succ.key;
                return current;
            }
        } else if (key < current.key) {
            current.left = delete(key, current.left, current);
        } else {
            current.right = delete(key, current.right, current);
        }
        return current;
    }

    void delete(int key) {
        root = delete(key, root, null);
    }

    Node max(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    Node min(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }



}
