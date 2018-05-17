package house.general.sorting;

import java.util.PriorityQueue;

public class KSort {
    static final int k = 10;

    public static int[] kSort(int[] a) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for (int i = 0; i < k; i++) {
            queue.add(a[i]);
        }

        for (int i = 0; i < a.length; i++) {
            if (i + k < a.length) {
                queue.add(a[k + i]);
            }
            int min = queue.remove();
            a[i] = min;
        }
        return a;
    }
}
