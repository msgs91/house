package house.general.algos;

import java.util.HashMap;

public class FourSumZeroCount {

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int n = A.length;
        HashMap<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int d = (A[i] + B[j]);
                int count = 1;
                if (hash.containsKey(d)) {
                    count = hash.get(d) + 1;
                }
                hash.put(d, count);
            }
        }

        int numOfTuples = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = C[i] + D[j];
                if (hash.containsKey(-1 * sum)) {
                    numOfTuples += hash.get(-1 * sum);
                }
            }
        }
        System.out.printf("%d \n", numOfTuples);
        return numOfTuples;
    }

    public static void main(String[] args) {
        FourSumZeroCount algo = new FourSumZeroCount();
        int[] A = new int[]{1, 2};
        int[] B = new int[]{-2, -1};
        int[] C = new int[]{-1, 2};
        int[] D = new int[]{0, 2};
        algo.fourSumCount(A, B, C, D);
    }
}
