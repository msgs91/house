package house.general.algos;

public class LIS {

    static int max(int a, int b) {
        if (a > b) return a;
        return b;
    }

    static int LIS(int[] a, int i, int prev) {
        if (i == a.length) return 0;

        if (a[i] > prev) {
            return max(1 + LIS(a, i+1, a[i]), LIS(a, i+1, prev));
        } else {
            return LIS(a, i+1, prev);
        }
    }

    static int LIS(int[] a) {
        return LIS(a, 0, Integer.MIN_VALUE);
    }

    public static void main(String[] args) {
        int[][] inputs = new int[][]{{}, {1}, {1, 2}, {4, 3, 2, 1}, {23, 24, 25, 1, 3, 4 , 6, 5, 9}};

        for (int i = 0; i < inputs.length; i++) {
            int length = LIS(inputs[i]);
            System.out.printf("length = %d \n", length);
        }
    }
}
