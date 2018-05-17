package house.general.algos;

import house.general.sorting.KSort;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KSortedMerge {

    class Node {
        int key;
        int k;

        public Node(int key, int k) {
            this.key = key;
            this.k = k;
        }
    }

    static public void print(int[] nums, int start, int end) {
        for (int i = start; i <= end; i++){
            System.out.printf("%d ", nums[i]);
        }
        System.out.println();
    }

    int[] kmerge(int[][] knums) {
        int k = knums.length;
        int[] indices = new int[k];
        int length = 0;
        for (int i = 0; i < k; i++) {
            length += knums[i].length;

        }

        int[] sorted = new int[length];

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.key - o2.key;
            }
        });

        for (int i = 0; i < indices.length; i++) {
            indices[i] = 0;
            pq.add(new Node(knums[i][0], i));
        }
        for (int i = 0; i < length; i++) {
            Node minNode = pq.poll();
            sorted[i] = minNode.key;
            indices[minNode.k] += 1;
            if (indices[minNode.k] < knums[minNode.k].length) {
                pq.add(new Node(knums[minNode.k][indices[minNode.k]], minNode.k));
            }
        }

        return sorted;
    }

    public static void main(String[] args) {
        int[][][] inputs = new int[][][]{{{1, 2}, {3, 4}}, {{1, 2}, {3}, {1, 1, 2}}};
        KSortedMerge algo = new KSortedMerge();
        for (int i = 0; i < inputs.length; i++) {
            int[] sorted = algo.kmerge(inputs[i]);
            System.out.printf("array = \n\n");
            print(sorted, 0, sorted.length-1);
        }
    }
}
