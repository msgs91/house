package house.general.sorting;

public class BubbleSort {
    public static int[] sort(int[] arr) {
        int n = arr.length;
        int lastIndex = n - 1;
        while(lastIndex > 0){
            for(int i = 0; i <= lastIndex - 1; i++) {
                if (arr[i+1] < arr[i]) {
                    int temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                }
            }
            lastIndex -= 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 1};
        int[] sorted = sort(arr);
        for(int num: sorted){
            System.out.println(num);
        }

    }
}
