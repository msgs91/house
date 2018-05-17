package house.general.algos;

public class FirstMissingPositive {
    static class Solution {
        public int firstMissingPositive(int[] nums) {
            boolean[] visited = new boolean[nums.length];

            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];
                if (num-1 < nums.length && num-1 >= 0) {
                    visited[num-1] = true;
                }
            }

            int i;
            for (i = 0; i < nums.length && visited[i]; i++) {}
            return i+1;
        }
    }

    public static void main(String[] args) {
        int[][] inputs = new int[][]{{1, 2, 3, 4}, {1, 2, 0}, {3, 4, -1, 1}, {}};
        Solution algo = new FirstMissingPositive.Solution();
        for (int i = 0; i < inputs.length; i++) {
            int firstMissingPositive = algo.firstMissingPositive(inputs[i]);
            System.out.printf("ans = %d \n", firstMissingPositive);
        }
    }
}
