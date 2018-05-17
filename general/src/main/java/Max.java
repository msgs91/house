import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by user on 14/05/17.
 */
class Max {

    public static int findMax(List<Integer> list) {
        int max = -1;
        for(int a : list){
            if(a > max)
                max = a;
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();


        while(t-- > 0) {
            int n = scanner.nextInt();
            List<Integer> courses = new ArrayList<Integer>(n);
            for(int i=0; i<n; i++){
                courses.add(scanner.nextInt());
            }
            System.out.println(n - findMax(courses));
        }

        scanner.close();
    }
}
