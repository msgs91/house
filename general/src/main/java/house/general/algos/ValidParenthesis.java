package house.general.algos;

import java.util.Stack;

public class ValidParenthesis {
    public boolean checkValidString(String s) {
        int starsUsedAsClose = 0;
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else if (c == '*') {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                    starsUsedAsClose += 1;
                } else {
                    stack.push(c);
                }
            } else if (c == ')') {
                if (starsUsedAsClose > 0){
                    starsUsedAsClose -= 1;
                } else if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    return false;
                }

            }
        }
        if (!stack.isEmpty() && stack.peek() == 'C') {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        String s1 = "";
        String s2 = "()";
        String s3 = "(*)(*)";
        String s4 = "(*))";
        String s5 = "*))";
        String s6 =
                "(())((())()()(*)(*()(())())())()()((()())((()))(*";
        String[] inputs = new String[]{s1, s2, s3, s4, s5, s6};

        ValidParenthesis algo = new ValidParenthesis();
        for (int i = 0; i < inputs.length; i++){
            System.out.println(algo.checkValidString(inputs[i]));

        }

    }
}
