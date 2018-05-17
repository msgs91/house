package house.general.algos;

public class MedianOfSortedArrays {

    static class Solution {

        public void print(int[] nums, int start, int end) {
            for (int i = start; i <= end; i++){
                System.out.printf("%d ", nums[i]);
            }
            System.out.println();
        }

        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int length = (nums1.length + nums2.length)/ 2;
            int[] sorted = new int[length+1];
            int i = 0;
            int j = 0;
            int k = 0;
            while (i < nums1.length &&j < nums2.length) {
                if (nums1[i] < nums2[j]) {
                    sorted[k] = nums1[i];
                    i += 1;
                } else {
                    sorted[k] = nums2[j];
                    j += 1;
                }
                k += 1;
            }
            while (i < nums1.length && k < sorted.length) {
                sorted[k] = nums1[i];
                k += 1;
                i += 1;
            }

            while (j < nums2.length && k < sorted.length) {
                sorted[k] = nums2[j];
                k += 1;
                j += 1;
            }

            print(sorted, 0, sorted.length-1);

            if ((nums1.length + nums2.length) % 2 == 0) {
                return (sorted[sorted.length-1] * 1.0 + sorted[sorted.length-2] * 1.0)/2;
            } else {
                return sorted[sorted.length-1];
            }

        }
    }

    public static void main(String[] args) {
        int[][][] inputs = new int[][][]{{{1, 2}, {3, 4}}, {{1, 2}, {}}};
        Solution algo = new MedianOfSortedArrays.Solution();
        for (int i = 0; i < inputs.length; i++) {
            double median = algo.findMedianSortedArrays(inputs[i][0], inputs[i][1]);
            System.out.printf("median = %f", median);
        }
    }
}
