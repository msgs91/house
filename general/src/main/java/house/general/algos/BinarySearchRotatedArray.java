package house.general.algos;

public class BinarySearchRotatedArray {

    static int findMin(int[] arr) {
        return findMin(arr, 0, arr.length-1);
    }

    //fails with duplicates
    static int findMin(int[] arr, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            if ((mid-1) >= 0 && arr[mid - 1] > arr[mid]) {
                return arr[mid];
            } else if ((mid+1) <= end && arr[mid + 1] < arr[mid]) {
                return arr[mid + 1];
            } else if (arr[end] >= arr[mid] && arr[mid] >= arr[start]) {
                return arr[start];
            } else if (arr[end] >= arr[mid]) {
                return findMin(arr, start, mid - 1);
            } else {
                return findMin(arr, mid + 1, end);
            }
        } else {
            return arr[start];
        }
    }

    public static void main(String[] args) {
        int[][] inputs = new int[][]{{10, 1, 10, 10, 10}};

        for (int i = 0; i < inputs.length; i++) {
            int min = findMin(inputs[i]);
            System.out.printf("min %d ", min);
        }
    }
}
