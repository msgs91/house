package house.general.algos;

import house.general.PrintArray;

public class ProductOfArrayExceptSelf {
    static class Solution {
        public int[] productExceptSelf(int[] nums) {
            int[] forwardProducts = new int[nums.length];
            int[] reverseProducts = new int[nums.length];

            for (int i = 0; i < forwardProducts.length; i++){
                forwardProducts[i] = 1;
                reverseProducts[i] = 1;
            }

            for (int i = 1; i < nums.length; i++) {
                forwardProducts[i] = forwardProducts[i] * forwardProducts[i-1] * nums[i-1];
            }

            int lastIndex = nums.length - 1;
            for (int i = lastIndex - 1; i >= 0; i--) {
                reverseProducts[i] = reverseProducts[i] * reverseProducts[i+1] * nums[i+1];
            }

            for (int i = 0; i < forwardProducts.length; i++) {
                forwardProducts[i] *= reverseProducts[i];
            }

            return forwardProducts;
        }
    }

    public static void main(String[] args) {
        int[][] inputs = new int[][]{{1, 2, 3, 4}};
        Solution algo = new ProductOfArrayExceptSelf.Solution();
        for (int i = 0; i < inputs.length; i++) {
            int[] products = algo.productExceptSelf(inputs[i]);
            PrintArray.print(products, 0, products.length-1);
        }
    }
}
