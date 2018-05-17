package house.general.sorting;

import java.util.PriorityQueue;

public class CountingSort {
    static final int k = 10;
    public static int[] sort(int[] a) {
        int[] c = new int[k];
        int[] b = new int[a.length];
        for (int i = 0; i < c.length; i++) {
            c[i] = 0;
        }

        for (int i = 0; i < a.length; i++) {
            c[a[i]] += 1;
        }

        for (int i = 1; i < c.length; i++) {
            //no of elements <= i
            c[i] += c[i-1];
        }

        for (int i = a.length - 1; i >= 0; i--) {
            b[c[a[i]]] = a[i];
            c[a[i]] -= 1;
        }
        return b;
    }


}
