package house.general.algos;

import java.util.HashMap;

public class ThreeElementSumEqualsFourth {

    public boolean bruteForce(int[] nums) {
        HashMap<Integer, Integer> hash = new HashMap<>();
        int size = nums.length;
        print(nums);

        for (int i = 0; i < size; i++) {
            hash.put(nums[i], i);
        }

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                for (int k = j + 1; k < size; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if(hash.containsKey(sum)) {
                        int index = hash.get(sum);
                        if (index != i && index != j && index != k) {
                            System.out.printf("true\n");
                            return true;
                        }
                    }
                }
            }
        }
        System.out.printf("false\n");
        return false;
    }

    public boolean solution1(int[] nums) {
        return false;
    }

    void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%d ", arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, -2, 3};
        int[] nums2 = new int[]{1, 2, 3, 6};
        int[] nums3 = new int[]{1, 2, 3, 5};
        int[] nums4 = new int[]{1, 2};
        int[] nums5 = new int[]{1, 2, 3};

        ThreeElementSumEqualsFourth algo = new ThreeElementSumEqualsFourth();
        algo.bruteForce(nums1);
        algo.bruteForce(nums2);
        algo.bruteForce(nums3);
        algo.bruteForce(nums4);
        algo.bruteForce(nums5);
    }
}
