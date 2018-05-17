package house.general.math;

public class Modulo {

    public static void main(String[] args) {
        int[] arr = {0, 1, 10, 11, -1, -2, -10, -11, -12};
        for(int i: arr) {
            int remainder = i % 10;
            int modulo = (remainder >= 0) ? remainder : remainder + 10;
            System.out.printf("%d modulo 10 is %d\n", i, modulo);
        }
    }
}
