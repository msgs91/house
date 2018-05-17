package house.general.tree;

public class Trie {

    class Node {
        boolean exists;
        Node[] ptrs = new Node[26];

        public Node(boolean exists) {
            this.exists = exists;
        }
    }

    Node root = new Node(false);

    public void insert(String word) {
        insert(word, 0, root);
    }

    private void insert(String word, int pos, Node node) {
        if (pos == word.length()) {
            node.exists = true;
            return;
        }
        char current = word.charAt(pos);
        if (node.ptrs[current] == null) {
            node.ptrs[current] = new Node(false);
        }
        insert(word, pos + 1, node.ptrs[current]);
    }

    public boolean exists(String word) {
        return exists(word, 0, root);
    }

    private boolean exists(String word, int pos, Node node) {
        if (pos == word.length()){
            if (node.exists){
                return true;
            } else {
                return false;
            }
        }
        char current = word.charAt(pos);
        if (node.ptrs[current] == null) {
            return false;
        } else {
            return exists(word, pos + 1, node.ptrs[current]);
        }

    }
}
