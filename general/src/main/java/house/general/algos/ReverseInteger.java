package house.general.algos;

public class ReverseInteger {
    public int reverse(int x) {
        boolean signed = false;
        if (x < 0) {
            x = x * -1;
            signed = true;
        }

        int reverse = 0;
        System.out.printf("hello %s \n", Integer.toBinaryString(1 << 31));
        while (x > 0) {
            reverse = reverse * 10 + x % 10;
            if ((reverse ^ x) < 0) {
                x = 0;
                reverse = 0;
            }
            x = x / 10;
        }
        if (signed) {
            reverse *= -1;
        }
        return reverse;
    }

    public static void main(String[] args) {
        System.out.println(1999999999);
        System.out.println(new ReverseInteger().reverse(1999999999));
    }
}
