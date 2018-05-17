package house.general.algos;

import house.general.tree.Tree;

import javax.management.ObjectName;
import java.util.*;

public class ThreeElementSumEqualsZero {
    class Triplet {
        int i, j, k;
        public Triplet(int i, int j, int k) {
            this.i = i;
            this.j = j;
            this.k = k;
        }

        @Override
        public boolean equals(Object other) {
            Triplet second = (Triplet) other;
            if (this.i == second.i && this.j == second.j && this.k == second.k)
                return true;
            return false;
        }

        @Override
        public int hashCode() {
            int[] nums = {i, j, k};
            Arrays.sort(nums);
            int result = nums[0];
            result = 31 * result + nums[1];
            result = 31 * result + nums[2];
            System.out.println(result);
            return result;
        }
    }
    public List<List<Integer>> threeSum(int[] nums) {
        HashMap<Integer, TreeSet<Integer>> hash = new HashMap<>();
        TreeSet<Triplet> index = new TreeSet<>(new Comparator<Triplet>() {
            @Override
            public int compare(Triplet o1, Triplet o2) {
                if (o1.i < o2.i) {
                    return -1;
                } else if (o1.i > o2.i) {
                    return 1;
                } else if (o1.i == o2.i && o1.j < o2.j) {
                    return -1;
                } else if (o1.j > o2.j) {
                    return 1;
                } else if (o1.i == o2.i && o1.j == o2.j && o1.k < o2.k) {
                    return -1;
                } else if (o1.k > o2.k) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        List<List<Integer>> triplets = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            int negative = nums[i] * -1;
            if (hash.containsKey(negative)) {
                TreeSet<Integer> set = hash.get(negative);
                set.add(i);
                hash.put(negative, set);
            } else {
                TreeSet<Integer> set = new TreeSet<Integer>();
                set.add(i);
                hash.put(nums[i] * -1, set);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                if (hash.containsKey(sum)) {
                    TreeSet<Integer> set = hash.get(sum);
                    Set<Integer> ks = new TreeSet(set);
                    if(set.contains(i) && set.contains(j) && set.size() >= 2) {
                        ks.remove(i);
                        ks.remove(j);
                    } else if ((set.contains(i) || set.contains(j)) && set.size() >= 1){
                        if (set.contains(i))
                            ks.remove(i);
                        else ks.remove(j);
                    } else {

                    }
                    for (int k: ks){
                        if (!index.contains(new Triplet(i, j, k))) {
                            List<Integer> triplet = new ArrayList();
                            triplet.add(nums[i]);
                            triplet.add(nums[j]);
                            triplet.add(nums[k]);
                            triplets.add(triplet);

                            index.add(new Triplet(i, j, k));
                        }
                    }
                }
            }
        }
        return triplets;
    }

    public List<List<Integer>> threeSum1(int[] nums) {
        class Triples {
            int i, j, k;
            int hash = -1;
            public Triples(int i, int j, int k) {
                this.i = i;
                this.j = j;
                this.k = k;
            }

            @Override
            public int hashCode() {
                if (hash == -1) {
                    int[] nums = {i, j, k};
                    Arrays.sort(nums);
                    hash = (int) nums[0];
                    hash = 31 * hash + nums[1];
                    hash = 31 * hash + nums[2];
                    System.out.printf("hashcode for i=%d, j=%d, k=%d is %d", i, j, k, hash);
                    System.out.println(hash);

                }
                return hash;
            }

            public List<Integer> toLinkedList(int[] nums) {
                List<Integer> list = new LinkedList<>();
                list.add(nums[i]);
                list.add(nums[j]);
                list.add(nums[k]);
                return list;
            }
        }
        Arrays.sort(nums);
        Set<Triples> result = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1, k = nums.length-1; j < k; ) {
                if (j != i && k != i) {
                    if (nums[j] + nums[k] == -1 * nums[i]) {
                        Triples triple = new Triples(i, j, k);
//                        if (!result.contains(triple))
                        result.add(triple);
                        break;
                    } else if (nums[j] + nums[k] < nums[i] * -1) {
                        j++;
                    } else {
                        k--;
                    }
                }
            }
        }
        List<List<Integer>> ans = new LinkedList<>();

        for (Triples t: result) {
            ans.add(t.toLinkedList(nums));
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{-1, 0, 1, 2, -1, -4};
        int[] nums2 = new int[]{1, 2, 3, 6};
        int[] nums3 = new int[]{1, 2, 3, 5};
        int[] nums4 = new int[]{1, 2};
        int[] nums5 = new int[]{0, 0, 0, 0};

        int[][] inputs = new int[][]{nums1, nums2, nums3, nums4, nums5};

        ThreeElementSumEqualsZero algo = new ThreeElementSumEqualsZero();
//        for (int[] input: inputs) {
//            List<List<Integer>> ans = algo.threeSum(input);
//            System.out.printf("\n\n");
//            for (List<Integer> list: ans) {
//                System.out.printf("[");
//                for (int i : list) {
//                    System.out.printf("%d ", i);
//                }
//                System.out.printf("]\n");
//            }
//        }
        System.out.printf("\n\n===============\n\n");

        for (int[] input: inputs) {
            List<List<Integer>> ans = algo.threeSum1(input);
            System.out.printf("\n\n");
            for (List<Integer> list: ans) {
                System.out.printf("[");
                for (int i : list) {
                    System.out.printf("%d ", i);
                }
                System.out.printf("]\n");
            }
        }
    }
}
