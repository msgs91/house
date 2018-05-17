package house.general.algos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CourseScheduleDeadline {

    class Course {
        int length;
        int deadline;

        public Course(int length, int deadline) {
            this.length = length;
            this.deadline = deadline;
        }
    }

    class Range {
        int start;
        int end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

    }

    int time = 0;

    public int maxCourses(int[][] courses) {
        if (courses.length == 0) return 0;

        ArrayList<Course> courseTimings = new ArrayList<>(courses.length);

        for (int i = 0; i < courses.length; i++) {
            courseTimings.add(new Course(courses[i][0], courses[i][1]));
        }

        Collections.sort(courseTimings, new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                return o1.deadline - o2.deadline;
            }
        });

        int totalTime = 100;
        int startTime = 0;
        int count = 0;

        for (Course course : courseTimings) {
            int finishTime = startTime + course.length;

            if (finishTime < totalTime && finishTime < course.deadline) {
                count += 1;
                startTime = finishTime;
            }
        }

        return count;

    }
}
