package house.general.algos;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class JobWithDeadlines {

    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        System.out.printf("Sorted interavals is \n");
        for (int i = 0; i < courses.length; i++) {
            System.out.printf("[%d, %d] ", courses[i][0], courses[i][1]);
        }
        System.out.println();
        int s = 0;
        int total = 1;
        int prevF = courses[0][0];
        for (int i = 1; i < courses.length; i++) {
            int start = prevF;
            int finish = prevF + courses[i][0];
            System.out.printf("(%d, %d) for course %d \n", start, finish, i);
            if (finish <= courses[i][1]) {
                prevF = finish;
                total += 1;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        JobWithDeadlines job = new JobWithDeadlines();
        int[][] one = new int[][]{{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}};
        int[][] two = new int[][] {{1, 2}, {2, 3}};
        int max = job.scheduleCourse(two);
        System.out.printf("max = %d \n", max);
    }
}
