package house.general.graph;

import house.general.CircularQueue;
import house.general.Heap;

import java.util.*;

public class Graph {

    class Node {
        int vertex;
        int weight = 0;
        Node next;

        public Node(int vertex) {
            this.vertex = vertex;
        }

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
    public static final int WHITE = 0;
    public static final int GREY = 1;
    public static final int BLACK = 2;

    Node[] adjList;
    int[] color;
    int[] pred;
    int[] dist;

    public Graph(int numOfVertices, int[][] edgeList) {
        adjList = new Node[numOfVertices];
        for (int i = 0; i < edgeList.length; i++) {
            int u = edgeList[i][0];
            int v = edgeList[i][1];
            if (adjList[u] == null) {
                adjList[u] = new Node(v);
            } else {
                Node node = new Node(v);
                node.next = adjList[u];
                adjList[u] = node;
            }
        }
    }

    public void topoSort(int u, int[] color, LinkedList<Integer> result) {
        color[u] = GREY;
        Node adjNode = adjList[u];
        while (adjNode != null) {
            int v = adjNode.vertex;
            if (color[v] == WHITE) {
                topoSort(v, color, result);
            }
            adjNode = adjNode.next;
        }
        color[u] = BLACK;
        result.addFirst(u);

    }

    public void topoSort() {
        for (int i = 0; i < adjList.length; i++) {
            color[i] = WHITE;
        }

        LinkedList<Integer> result = new LinkedList<>();

        for (int i = 0; i < adjList.length; i++) {
            if (color[i] == WHITE) {
                topoSort(i, color, result);
            }
        }
    }


    public void bfs() {
        for (int i = 0; i < adjList.length; i++) {
            color[i] = WHITE;
            pred[i] = -1;
        }
        CircularQueue<Integer> queue = new CircularQueue<>(100);
        queue.put(0);
        color[0] = GREY;
        dist[0] = 0;
        while (!queue.isEmpty()) {
            int i = queue.get();
            Node adjNode = adjList[i];
            while (adjNode != null) {
                if (color[adjNode.vertex] == WHITE) {
                    queue.put(adjNode.vertex);
                    color[adjNode.vertex] = GREY;
                    pred[adjNode.vertex] = i;
                    dist[adjNode.vertex] = dist[i] + 1;

                }
                adjNode = adjNode.next;
            }
            color[i] = BLACK;
        }
    }

    public void dfs(int u, int[] color, int[] pred) {
        color[u] = GREY;
        Node adjNode = adjList[u];
        while (adjNode != null) {
            if (color[adjNode.vertex] == WHITE) {
                pred[adjNode.vertex] = u;
                dfs(adjNode.vertex, color, pred);
            }
            adjNode = adjNode.next;
        }
        color[u] = BLACK;
    }

    public void dijikstra() {
        int[] d = new int[adjList.length];
        for (int i = 0; i < d.length; i++) {
            d[i] = Integer.MAX_VALUE;
        }
        d[0] = 0;
        class Vertex {
            int d;
            int i;

            public Vertex(int i, int d) {
                this.i = i;
                this.d = d;
            }
        }

        PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                if (o1.d < o2.d) return -1;
                else return 1;
            }
        });
        queue.add(new Vertex(0, d[0]));
        while (!queue.isEmpty()) {
            Vertex u = queue.remove();
            Node head = adjList[u.i];
            while (head != null) {
                if (d[head.vertex] > d[u.i] + head.weight) {
                    d[head.vertex] = d[u.i] + head.weight;
                    queue.add(new Vertex(head.vertex, d[head.vertex]));
                }
            }
        }
    }

    public void bellmanFord() {
        int v = adjList.length;
        for (int i = 1; i <= v - 1; i++) {
            for (int j = 0; j < v; j++) {
                Node head = adjList[j];
                while (head != null) {
                    if (true) {
//                        d[head.vertex] = d[j] + head.weight;
                    }
                    head = head.next;
                }
            }
        }
    }

}
