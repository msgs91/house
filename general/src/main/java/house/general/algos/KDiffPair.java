package house.general.algos;

import java.util.Arrays;
import java.util.HashMap;

class KDiffPair {

    public int findPairs(int[] nums, int k) {
        int numOfPairs = 0;
        HashMap<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!hash.containsKey(nums[i]) || (k == 0 && hash.get(nums[i]) <= 2)) {
                if (hash.containsKey(nums[i]+k) || hash.containsKey(nums[i]-k)) {
                    System.out.printf("i = %d \n", nums[i]);
                    numOfPairs += 1;
                }
                hash.put(nums[i], i);
            }
        }
        System.out.printf("pairs = %d \n", numOfPairs);
        return numOfPairs;
    }

    public static void main(String[] args) {
        KDiffPair kdf = new KDiffPair();
        kdf.findPairs(new int[]{1, 1, 1, 1}, 0);
    }
}