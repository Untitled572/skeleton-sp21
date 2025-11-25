package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> Bl = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                Bl.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int sizeb = Bl.size();
                assertEquals(size, sizeb);
            } else if (operationNumber == 2 && L.size() > 0) {
                int res = L.getLast();
                int resb = Bl.getLast();
                assertEquals(res, resb);
            } else if (operationNumber == 3 && L.size() > 0) {
                int res = L.removeLast();
                int size = L.size();
                int resb = Bl.removeLast();
                int sizeb = Bl.size();
                assertEquals(res, resb);
                assertEquals(size, sizeb);
            }
        }
    }
}
