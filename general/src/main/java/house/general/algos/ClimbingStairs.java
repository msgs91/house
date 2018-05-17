package house.general.algos;

public class ClimbingStairs {

    static int getWays(int n, int[] table) {
        if (n <= 1) return 1;
        int ways = -1;
        if (table[n-1] != -1 && table[n-2] != -1) {
            ways = table[n-1] + table[n-2];
        } else if (table[n-1] != -1) {
            ways = table[n-1] + getWays(n-2, table);
        } else {
            ways = getWays(n-1, table) + getWays(n-2, table);
        }
        return ways;
    }
    static int ways(int n) {
        int[] table = new int[n+1];
        for (int i = 0; i < table.length; i++) {
            table[i] = -1;
        }
        return getWays(n, table);
    }

    public static void main(String[] args) {
        int[] inputs = new int[]{0, 1, 2, 3, 10};

        for (int i = 0; i < inputs.length; i++) {
            int ans = ways(inputs[i]);
            System.out.printf("n = %d, ans = %d \n", inputs[i], ans);
        }
    }
}
