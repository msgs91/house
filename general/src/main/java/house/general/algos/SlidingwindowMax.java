package house.general.algos;

import scala.Int;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ganapathys on 13/04/18.
 */
public class SlidingwindowMax {
    static class Solution {
        class Pair {
            int index;
            int num;

            public Pair(int index, int num) {
                this.index = index;
                this.num = num;
            }
        }

        public int[] maxSlidingWindow1(int[] nums, int k) {
            int[] maxes = new int[nums.length-k+1];

            if (nums.length == 0 || k <= 0) return new int[]{};

            Deque<Pair> queue = new LinkedList<>();
            queue.add(new Pair(0, nums[0]));

            for (int i = 1; i < nums.length; i++) {
                if (i+1 > k) {
                    int max = queue.peek().num;
                    maxes[i-k] = max;
                    if (i-k == queue.peek().index) {
                        queue.remove();
                    }
                }
                while (!queue.isEmpty() && nums[i] > queue.peekLast().num) {
                    queue.removeLast();
                }
                queue.add(new Pair(i, nums[i]));
            }
            maxes[nums.length-k] = queue.peek().num;
            return maxes;
        }


        public int[] maxSlidingWindow(int[] nums, int k) {

            LinkedList<Pair> queue = new LinkedList<>();
            queue.add(new Pair(0, nums[0]));

            for (Pair pair : queue) {
                System.out.printf("%d %d -> ", pair.index, pair.num);
            }
            System.out.println();

            for (int i = 1; i < k; i++) {
                Iterator<Pair> iterator = queue.listIterator();
                int index = 0;
                int next = Integer.MIN_VALUE;
                while (iterator.hasNext() && nums[i] < next) {
                    Pair pair = iterator.next();
                    next = pair.num;
                }
                System.out.printf("next = %d \n", next);

                if (!queue.isEmpty() && nums[i] < next) {
                    iterator.remove();
                } else {
//                    iterator.next();
                }

                while (iterator.hasNext()) {
                    Pair pair = iterator.next();
                    System.out.printf("Removing %d %d \n", pair.index, pair.num);
                    iterator.remove();
                }
                System.out.printf("adding %d %d \n", i, nums[i]);

                queue.addLast(new Pair(i, nums[i]));

                for (Pair pair : queue) {
                    System.out.printf("%d %d -> ", pair.index, pair.num);
                }
                System.out.println();

            }
            System.out.println();

            for (Pair pair : queue) {
                System.out.printf("%d %d -> ", pair.index, pair.num);
            }
            System.out.println();

            for (int i = k; i < nums.length; i++) {
                if (i-k == queue.getFirst().index) {
                    Pair pair = queue.removeFirst();
                    System.out.printf("Removing %d %d \n", pair.index, pair.num);
                }
                Iterator<Pair> iterator = queue.listIterator();
                int index = 0;
                int next = Integer.MIN_VALUE;
                while (iterator.hasNext() &&  next < nums[i]) {
                    Pair pair = iterator.next();
                    next = pair.num;
                }

                System.out.printf("next = %d \n", next);

                if (!queue.isEmpty() && next < nums[i]) {
                    iterator.remove();
                } else {
//                    iterator.next();
                }
                System.out.printf("adding %d %d \n", i, nums[i]);

                queue.addLast(new Pair(i, nums[i]));
                System.out.println();

                for (Pair pair : queue) {
                    System.out.printf("%d %d -> ", pair.index, pair.num);
                }
                System.out.println();
            }
            return new int[0];
        }
    }

    public static void main(String[] args) {
        int[][] inputs = new int[][]{{13, 6, 7, 8, 9, 12, 11, 10}, {9, 8, 7, 6, 5, 4, 3, 2, 1}};
        Solution algo = new SlidingwindowMax.Solution();
        for (int i = 0; i < inputs.length; i++) {
            int[] maxes = algo.maxSlidingWindow1(inputs[i], 3);
            for (int j = 0; j < maxes.length; j++) {
                System.out.printf("%d ", maxes[j]);
            }
            System.out.println();
//            System.out.printf("max = %d \n", maxes);
        }
    }
}
