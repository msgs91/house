package house.general.algos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class SimpleInteravalScheduling {

    class Interaval {
        int start;
        int end;
    }

    public static int maxInteravals(ArrayList<Interaval> interavals) {
        Collections.sort(interavals, new Comparator<Interaval>() {
            @Override
            public int compare(Interaval o1, Interaval o2) {
                return o1.end - o2.end;
            }
        });

        Interaval prevInteraval = interavals.get(0);
        String s = "abcd";
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        sb.toString();
        int max = 1;
        for (int i = 1; i < interavals.size(); i++) {
            if (interavals.get(i).start > prevInteraval.end) {
                max += 1;
                prevInteraval = interavals.get(i);
            }
        }
        return max;
    }
}
