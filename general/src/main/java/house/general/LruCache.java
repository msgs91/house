package house.general;

import java.util.HashMap;
import java.util.PriorityQueue;

public class LruCache<V> {

    class Node implements Comparable<Node> {
        String key;
        int lastAccessed;

        public Node(String key, int lastAccessed) {
            this.key = key;
            this.lastAccessed = lastAccessed;
        }

        @Override
        public boolean equals(Object other) {
            Node otherNode = (Node) other;
            return otherNode.key == this.key;
        }

        @Override
        public int compareTo(Node other) {
            if (this.lastAccessed > other.lastAccessed){
                return 1;
            } else if (this.lastAccessed < other.lastAccessed) {
                return -1;
            }
            return 0;
        }
    }

    HashMap<String, V> map;
    PriorityQueue<Node> heap = new PriorityQueue<>();

    int capacity = 3;
    int time;

    public LruCache() {
        map = new HashMap<>();
        time = 0;
    }

    public V get(String key) {
        V value = null;
        if (!map.containsKey(key)) {
            return null;
        } else {
            value = map.get(key);
        }
        heap.remove(new Node(key, time));
        heap.add(new Node(key, time));
        System.out.printf("Updating key %s timestamp to %d \n", key, time);
        time += 1;
        return value;
    }

    public void put(String key, V value) {
        if (map.size() == capacity) {
            evictAKey();
        }
        heap.add(new Node(key, time));
        map.put(key, value);
        System.out.printf("Inserting key %s timestamp to %d \n", key, time);
        time += 1;
    }

    private void evictAKey() {
        Node node = heap.remove();
        System.out.printf("Evicting key %s with timestamp %d \n", node.key, node.lastAccessed);
        map.remove(node.key);
    }

    public static void main(String[] args) {
        LruCache<String> cache = new LruCache<>();
        cache.put("1", "A");
        cache.put("2", "B");
        cache.put("3", "C");
        cache.get("1");
        cache.put("4", "D");
        cache.get("1");
        cache.put("5", "E");
        String s = cache.get("2");
        if (s == null) {
            System.out.printf("Key 2 doesnt exists");
        }


    }



}
