package house.general.algos;

import java.util.Arrays;
import java.util.HashSet;

public class TwoElementSum {

    public boolean sumOfTwoElementsOn2(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean sumOfTwoElements(int[] nums, int target) {
        Arrays.sort(nums);
        for (int i = 0, j = nums.length - 1; i < j; ) {
            if (nums[i] + nums[j] > target) {
                j -= 1;
            } else if (nums[i] + nums[j] < target) {
                i += 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean sumOfTwoElements1(int[] nums, int target) {
        HashSet<Integer> hash = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (hash.contains(target - nums[i])) {
                return true;
            }
            hash.add(nums[i]);
        }
        return false;
    }
}
