package house.general.algos;

public class DecodeStringWays {

    static class Solution {
        public int numDecodings(String s) {
            if (s.length() == 0) return 0;
            int[] ways = new int[s.length() + 1];
            ways[0] = 1;
            if (ways.length > 0) {
                if (s.charAt(0) != '0')
                    ways[1] = 1;
                else
                    ways[1] = 0;
            }

            for (int i = 2, j = 1; i < ways.length && j < s.length(); i++, j++) {
                if (s.charAt(0) != '0') {
                    ways[i] = ways[i - 1];
                } else {
                    ways[i] = 0;
                }
                String lastTwo = new StringBuilder().append(s.charAt(j-1)).append(s.charAt(j)).toString();
                int alphabet = Integer.valueOf(lastTwo);
                if (alphabet <= 26 && i > 0) {
                    ways[i] += ways[i-2];
                }
                if (ways[i] == 0) {
                    return 0;
                }
            }
            return ways[ways.length-1];
        }
    }

    public static void main(String[] args) {
        Solution algo = new DecodeStringWays.Solution();
        String[] inputs = new String[]{"", "1", "12", "123", "321", "130", "10"};

        for (int i = 0; i < inputs.length; i++) {
            int ways = algo.numDecodings(inputs[i]);
            System.out.printf("ways = %d \n", ways);
        }
    }
}
