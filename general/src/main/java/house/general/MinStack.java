package house.general;

import java.util.Stack;

public class MinStack {

    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();

    public MinStack() {

    }

    public void push(int x) {
        stack.push(x);

        if (!minStack.isEmpty() && x <= minStack.peek()) {
            minStack.push(x);
        } else if (minStack.isEmpty()) {
            minStack.push(x);
        }
    }

    public void pop() {
        try {
            int popped = stack.pop();
            if (popped == minStack.peek()) {
                minStack.pop();
            }
        } catch (Exception e) {

        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
