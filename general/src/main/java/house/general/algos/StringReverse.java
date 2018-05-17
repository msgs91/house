package house.general.algos;

/**
 * Created by ganapathys on 01/04/18.
 */
public class StringReverse {

    public String reverse(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length-i-1; i++) {
            char temp = chars[i];
            chars[i] = chars[chars.length-i-1];
            chars[chars.length-i-1] = temp;
        }

        return new String(chars);
    }

    public static void main(String[] args) {
        StringReverse algo = new StringReverse();
        System.out.println(algo.reverse("scala"));
    }
}
