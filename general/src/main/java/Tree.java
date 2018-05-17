import java.util.Stack;

/**
 * Created by user on 15/05/17.
 */

class Node {
    public static boolean RED  = true;
    public static boolean BLACK = false;

    public Node left, right;
    public int data;
    public boolean color = RED;

    public Node(int value) {
        this.data = value;
        this.left = null;
        this.right = null;
    }
}

class Tree {
    Node root;

    Node A;
    Node B;
    Node C;
    Node D;
    Node E;
    Node F;
    Node G;
    Node H;
    Node I;
    Node J;
    Node K;
    Node L;
    Node M;


    public Tree() {
        A = new Node('A');
        B = new Node('B');
        C = new Node('C');
        D = new Node('D');
        E = new Node('E');
        F = new Node('F');
        G = new Node('G');
        H = new Node('H');
        I = new Node('I');
        J = new Node('J');
        K = new Node('K');
        L = new Node('L');
        M = new Node('M');

        root = A;
        A.left = B;
        A.right = C;
        B.left = G;
        B.right = H;
        H.left = J;
        H.right = I;
        J.left = K;
        K.left = L;
        L.right = M;
        C.right = D;
        D.right = E;
        E.left = F;
    }

    public void insert(int value) {
        Node temp = new Node(value);
        if(root == null) {
            root = temp;
            return;
        }
        Node node = root;
        Node prev = root;
        while(node != null){
            prev = node;
            if(value > node.data) node = node.right;
            else node = node.left;
        }
        if(value > prev.data) prev.right = temp;
        else prev.left = temp;

    }

    Node convertToDoublyLinkedList(Node node, Node pred){
        if(node == null) return null;
        Node left = convertToDoublyLinkedList(node.left, pred);
        if(left != null){
            left.right = node;
            node.left = left;
        } else if(pred != null){
            pred.right = node;
            node.left = pred;
        }
        Node right = convertToDoublyLinkedList(node.right, node);
        if(right == null) return node;
        else return right;
    }

    void inorderIterative(Node node){
        if(node == null) return;
        Stack<Node> s = new Stack<Node>();
        s.push(node);
        while(!s.isEmpty()){
            Node temp = s.peek();
            while(temp.left != null){
                s.push(temp.left);
                temp = temp.left;
            }
            while(!s.isEmpty() && s.peek().right == null){
                Node x = s.pop();
                System.out.print((char)x.data);
            }
            if(!s.isEmpty()){
                Node top = s.pop();
                System.out.print((char)top.data);
                s.push(top.right);
            }

        }
    }

     Node insert(Node node, int value){
        if(node == null) return new Node(value);

        if(value > node.data) node.right = insert(node.right, value);
        else node.left = insert(node.left, value);

        return node;
    }

    Node lca(Node node, Node first, Node second){
        if(node == null || node == first || node == second) return node;
        else {
            Node left = lca(node.left, first, second);
            Node right = lca(node.right, first, second);

            if(left == null && right == null) return null;
            else if(left == null) return right;
            else if(right == null) return left;
            else return node;
        }
    }

    int max(int a, int b) {
        if(a > b) return a;
        return b;
    }

    int min(int a, int b){
        if(a < b) return a;
        return b;
    }

    int height(Node node){
        if(node == null) return 1;
        return max(height(node.left), height(node.right));
    }

    int radius(Node node){
        if(node == null) return 0;
        return min(height(node.left), height(node.right));
    }

    void inorder(Node node){
        if(node == null) return;
        inorder(node.left);
        System.out.print((char)node.data);
        inorder(node.right);
    }

    void postorder(Node node){
        if(node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print((char)node.data);
    }

    void preorder(Node node){
        if(node == null) return;
        System.out.print((char)node.data);
        preorder(node.left);
        preorder(node.right);
    }


    Node search(int value){
        Node temp = root;
        while(temp != null){
            if(value > temp.data) temp = temp.right;
            else if(value < temp.data) temp = temp.left;
            else return temp;
        }
        return temp;
    }

    Node delete(int value){
        Node temp = root;
        Node prev = null;
        boolean rightChild = true;
        while(temp != null && value != temp.data){
            prev = temp;
            if(value > temp.data) {
                temp = temp.right;
                rightChild = true;
            }
            else if(value < temp.data) {
                temp = temp.left;
                rightChild = false;
            }
        }
        if (temp == null) return null;

        if(temp.left == null && temp.right == null){
            if(rightChild) prev.right = null;
            else prev.left = null;
        } else if(temp.left == null && temp.right != null){
            if(rightChild) prev.right = temp.right;
            else prev.left = temp.right;
        } else if(temp.left != null && temp.right == null){
            if(rightChild) prev.right = temp.left;
            else prev.left = temp.left;
        } else {
            if(rightChild) prev.right = temp.right;
            else prev.left = temp.right;
            Node right = temp.right;
            while(right.left != null){
                right = right.left;
            }
            right.left = temp.left;
        }

        return temp;

    }

    public static void main(String[] args) {
        Tree tree = new Tree();
        Node head = new Node(1);
        tree.inorder(tree.root);
        System.out.println();
        tree.convertToDoublyLinkedList(tree.root, head);
        Node temp = head;
        while(temp != null){
            System.out.print((char)temp.data);
            temp = temp.right;
        }
    }
}


