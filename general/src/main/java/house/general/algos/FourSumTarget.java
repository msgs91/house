package house.general.algos;

import scala.Int;

import java.util.*;

public class FourSumTarget {

    class Ij {
        int i;
        int j;

        public Ij(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Ij ij = (Ij) o;

            if (i != ij.i) return false;
            return j == ij.j;

        }

        @Override
        public int hashCode() {
            int result = i;
            result = 31 * result + j;
            return result;
        }
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        HashMap<Integer, Set<Ij>> hash = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                Ij ij = new Ij(i, j);
                Set<Ij> temp;
                if (hash.containsKey(sum)) {
                    temp = hash.get(sum);
                } else {
                    temp = new HashSet<>();
                }
                temp.add(ij);
                hash.put(sum, temp);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                int sum = target - nums[i] + nums[j];
                if (hash.containsKey(sum)) {
                    Ij currentIj = new Ij(i, j);
                    Set<Ij> ijs = hash.get(sum);
                    for (Ij ij: ijs) {
                        if (currentIj.i != ij.i && currentIj.j != ij.j && currentIj.j != ij.i && currentIj.i != ij.j) {
                            List<Integer> fours = new ArrayList<>();
                            fours.add(nums[i]);
                            fours.add(nums[j]);
                            fours.add(nums[ij.i]);
                            fours.add(nums[ij.j]);
                            result.add(fours);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < result.size(); i++) {
            System.out.println();
            for (int j = 0; j < result.get(i).size(); j++) {
                System.out.printf("%d ", result.get(i).get(j));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FourSumTarget algo = new FourSumTarget();
        int[] nums1 = new int[]{1, 0, -1, 0, -2, 2};
        int[][] inputs = new int[][]{nums1};
        int target = 0;
        for (int[] input : inputs) {
            algo.fourSum(input, target);
        }

    }
}
