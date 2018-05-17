package house.general.algos;

public class NDiceSumWays {

    static int getWays(int n, int a, int sum) {
        System.out.printf("n = %d sum = %d \n", n, sum);
        if (n == 1) {
            for (int i = 1; i <= a; i++) {
                if (i == sum){
                    System.out.printf("base choosing i = %d \n", i);
                    return 1;
                } else if (i > sum) {
                    return 0;
                }
            }
        }
        int ways = 0;
        for (int i = 1; i <= a; i++) {
            if (sum - i <= 0) {
                break;
            }
            System.out.printf("choosing i = %d \n", i);
            ways += getWays(n-1, a, sum - i);
        }

        return ways;
    }

    public static void main(String[] args) {
        int[][] inputs = {{2, 6, 1}, {2, 6, 2}, {2, 6, 3}};
        for (int i = 0; i < inputs.length; i++) {
            int ways = getWays(inputs[i][0], inputs[i][1], inputs[i][2]);
            System.out.printf("n = %d, a = %d, sum = %d, ways = %d \n", inputs[i][0], inputs[i][1], inputs[i][2], ways);
        }
    }
}
