package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

import java.util.ArrayList;
import java.util.List;

public class TestArrayDequeEC {
    private String generateMsg(List<String> failSeq) {
        StringBuilder result = new StringBuilder();
        for (String elem : failSeq) {
            result.append(elem);
            result.append("\n");
        }
        return result.toString();
    }

    @Test
    public void firstTest() {
        ArrayDequeSolution<Integer> deque1 = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> deque2 = new StudentArrayDeque<>();
        List<String> failSeq = new ArrayList<>();
        int n = 5000;
        for (int i = 0; i < n; ++i) {
            int operationNumber = StdRandom.uniform(0, 5);
            switch (operationNumber) {
                case 0:
                    failSeq.add("addFirst(" + i + ")");
                    deque1.addFirst(i);
                    deque2.addFirst(i);
                    break;
                case 1:
                    failSeq.add("addLast(" + i + ")");
                    deque1.addLast(i);
                    deque2.addLast(i);
                    break;
                case 2:
                    failSeq.add("size()");
                    assertEquals(generateMsg(failSeq), deque1.size(), deque2.size());
                    break;
                case 3:
                    if (!deque1.isEmpty() && !deque2.isEmpty()) {
                        failSeq.add("removeLast()");
                        assertEquals(generateMsg(failSeq), deque1.removeLast(), deque2.removeLast());
                    }
                    break;
                case 4:
                    if (!deque1.isEmpty() && !deque2.isEmpty()) {
                        failSeq.add("removeFirst()");
                        assertEquals(generateMsg(failSeq), deque1.removeFirst(), deque2.removeFirst());
                    }
                    break;
            }
        }
    }
}
