package house.general.algos;

import java.util.Arrays;

public class BinarySearchArray {

    static int find(int[] arr, int target) {
        Arrays.sort(arr);
        return find(arr, target, 0, arr.length-1);
    }

    static int find(int[] arr, int target, int start, int end) {
        if (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (target < arr[mid]) {
                return find(arr, target, start, mid-1);
            } else {
                return find(arr, target, mid+1, end);
            }
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        int[][] inputs = new int[][]{{2, 3, 5, 6, 8, 9, 11}};

        for (int i = 0; i < inputs.length; i++) {
            int target = 12;
            int index = find(inputs[i], target);
            System.out.printf("target %d found @ %d", target, index);
        }
    }
}
