package house.general.algos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class CourseSchedule {

    class Range {
        int start;
        int end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

    }

    boolean overlap(Range range1, Range range2) {
        if (range1.start > range2.end || range2.start > range1.end) {
            return false;
        } else {
            return true;
        }
    }

    public int maxCourses(int[][] courses) {

        if (courses.length == 0) return 0;

        ArrayList<Range> ranges = new ArrayList<>(courses.length);

        for (int i = 0; i < courses.length; i++) {
            ranges.add(new Range(courses[i][0], courses[i][1]));
        }

        Collections.sort(ranges, new Comparator<Range>() {
            @Override
            public int compare(Range o1, Range o2) {
                return o1.end - o2.end;
            }
        });

        int maxCourses = 1;
        Range prev = ranges.get(0);
        for (int i = 1; i < ranges.size(); i++) {
            if (!overlap(ranges.get(i), prev)) {
                prev = ranges.get(i);
                maxCourses += 1;
            }
        }

        return 1;

    }
}
