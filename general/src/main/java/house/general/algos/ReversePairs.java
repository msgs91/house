package house.general.algos;

public class ReversePairs {
    public int reversePairs(int[] nums) {
        int numOfPairs = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if ((nums[j] > 0 &&  - nums[j] < nums[j]) || (nums[j] < 0 && Integer.MIN_VALUE - nums[j] > nums[j])){

                } else if (nums[i] - nums[j] >  nums[j]) {
                    numOfPairs += 1;
                }
            }
        }
        return numOfPairs;
    }

    public int reversePairs1(int[] nums, int start, int end) {
        int numOfPairs = 0;
//        print(nums, start, end);
        if (end-start+1 == 2) {
            if (((nums[start] > 0 && nums[end] > 0 || (nums[start] < 0 && nums[end] < 0)) && nums[start] - nums[end] > nums[end])
                    || (nums[start] > 0 && nums[end] < 0)) {
                numOfPairs += 1;

            }
            if (nums[end] > nums[start]) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
            }
//            System.out.printf("Pairs when 2 = %d \n", numOfPairs);
            return numOfPairs;
        } else if (end-start+1 == 1 || end-start+1 == 0) {
//            System.out.printf("Pairs when 1 = %d \n", numOfPairs);
            return numOfPairs;
        }
        int mid = (start + end) / 2;
        numOfPairs += reversePairs1(nums, start, mid);
        numOfPairs += reversePairs1(nums, mid+1, end);
        for (int i = start, j = mid+1; i <= mid && j <= end;) {
            if (((nums[i] > 0 && nums[j] > 0 || (nums[i] < 0 && nums[j] < 0)) && nums[i] - nums[j] > nums[j])
                    || (nums[i] > 0 && nums[j] < 0)) {
                numOfPairs += end - j + 1;
                i += 1;
            } else {
                j += 1;
            }
        }
        //merge
        int[] lower = new int[mid-start+1];
        int[] higher = new int[end-mid];
//        System.out.printf("Merging arrays\n");
        for (int i = 0; i < lower.length; i++) {
            lower[i] = nums[start+i];
        }
        for (int i = 0; i < higher.length; i++) {
            higher[i] = nums[mid+1+i];
        }
//        print(lower, 0, lower.length-1);
//        print(higher, 0, higher.length-1);
        for (int i = 0, j = 0, k = 0; i < lower.length || j < higher.length; k++) {
            if (i < lower.length && j < higher.length) {
                if (lower[i] > higher[j]) {
                    nums[start+k] = lower[i];
                    i++;
                } else {
                    nums[start+k] = higher[j];
                    j++;
                }
            } else if (i < lower.length){
                nums[start+k] = lower[i];
                i++;
            } else {
                nums[start+k] = higher[j];
                j++;
            }
        }

//        System.out.printf("Pairs after merging = %d \n", numOfPairs);
        return numOfPairs;
    }

    public void print(int[] nums, int start, int end) {
        for (int i = start; i <= end; i++){
            System.out.printf("%d ", nums[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ReversePairs algo = new ReversePairs();
        int[] num1 = new int[]{2, 4, 3, 5, 1};
        int[] num2 = new int[]{2147483647,2147483647,2147483647,2147483647,2147483647,2147483647};
        int[] num3 = new int[]{2147483647,2147483647,-2147483647,-2147483647,-2147483647,2147483647};

        int[][] inputs = new int[][]{num1, num2, num3};
        for (int[] input: inputs) {
            int ans = algo.reversePairs1(input, 0, input.length -1);
            System.out.printf("ans = %d\n", ans);
            for (int elem: input){
                System.out.printf("%d ", elem);
            }
            System.out.println();
        }
    }
}
