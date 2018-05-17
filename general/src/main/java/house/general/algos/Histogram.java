package house.general.algos;

import java.util.Stack;

public class Histogram {

    public int largestRectangleArea2(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        if (heights.length == 0) return 0;
        stack.push(0);
        int max = heights[0];
        int i;
        for (i = 1; i < heights.length; i++) {
            if (heights[i] > heights[stack.peek()]) {
                stack.push(i);
                System.out.printf("Pushing index %d\n", i);

            } else {
                int top = stack.pop();
                int span = 0;
                System.out.printf("Poping index %d\n", top);

                while(!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                    span += top - stack.peek();
                    if (heights[top] * span > max) {
                        max = heights[top] * span;
                    }
                    top = stack.pop();
                    System.out.printf("Poping index %d\n", top);

                }
                if (heights[i] <= heights[top]) {
                    span += top + 1;
                    System.out.printf("last index %d\n", top);

                    if (heights[top] * span > max) {
                        max = heights[top] * span;
                    }
                } else {
                    stack.push(top);
                }
                System.out.printf("Pushing index %d\n", i);
                stack.push(i);
            }
        }

        int top = stack.pop();
        System.out.printf("Poping index %d\n", top);

        int span = 0;
        while(!stack.isEmpty()) {
            span += top - stack.peek();
            if (heights[top] * span > max) {
                max = heights[top] * span;
            }
            top = stack.pop();
            System.out.printf("Poping index %d\n", top);

        }
        span += top+1;
        System.out.printf("last index %d\n", top);

        if (heights[top] * span > max) {
            max = heights[top] * span;
        }

        return max;
    }

    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        int max = heights[0] * 1;

        for (int i = 1; i < heights.length || !stack.isEmpty(); i++) {
            if (i < heights.length && heights[i] > heights[stack.peek()]) {
                stack.push(i);
                System.out.printf("Pushing index %d\n", i);

            } else {
                int top = stack.pop();
                System.out.printf("Poping index %d\n", top);

                int numOfItems = 0;
                while (!stack.isEmpty() && (i >= heights.length || heights[i] <= heights[top])) {
                    numOfItems += top - stack.peek();
                    System.out.printf("Prev index %d\n", stack.peek());


                    if (heights[top] * numOfItems > max) {
                        max = heights[top] * numOfItems;
                    }
                    top = stack.pop();
                    System.out.printf("Poping index %d\n", top);
                }
                if ((i < heights.length && heights[i] <= heights[top]) || i >= heights.length) {
//                    System.out.printf("Poping final index %d\n", top);

                    numOfItems += (top - 0);
                    if (heights[top] * numOfItems > max) {
                        max = heights[top] * numOfItems;
                    }
                } else {
                    stack.push(top);
                    System.out.printf("Pushing back index %d\n", top);
                }

                if (i < heights.length) {
                    stack.push(i);
                    System.out.printf("Pushing index %d\n", i);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] inputs = new int[][]{{1, 2, 3, 4, 3, 4, 5, 2, 2}, {1, 1, 1, 1}, {12, 3, 5, 6, 2, 3}, {2, 1, 2}};
        Histogram algo = new Histogram();
        for (int i = 0; i < inputs.length; i++) {
            int max = algo.largestRectangleArea2(inputs[i]);
            System.out.printf("max = %d \n", max);
        }
    }
}
