package house.general;

public class PrintArray {
    static public void print(int[] nums, int start, int end) {
        for (int i = start; i <= end; i++){
            System.out.printf("%d ", nums[i]);
        }
        System.out.println();
    }
}
