package agh.ii.prinjava.lab07.lst07_03;

import java.util.List;
import java.util.Optional;

/**
 * Streams/lambda expressions: {@code throw-catch} and functional programming mismatch
 * <p> Note: {@code throw-catch} is an echo of imperative roots of Java.
 * In modern/functional code use {@link Optional} instead.
 */
public class Main {

    public static void main(String[] args) {
        List<Integer> l1 = List.of(3, 2, 1, 0, -1, -2, -3);

        // Unchecked exceptions can be handle outside the stream
        try {
            l1.stream().forEach(e -> m1(e)); // e < 0 => IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException!");
        }
        // but checked exceptions cannot
//        try {
//            l1.stream().forEach(e -> m2(e)); // <- compilation error: Unhandled exception: java.lang.Exception
//        } catch (Exception e) {
//            System.out.println("IllegalArgumentException!");
//        }

        // handing the exception "inside the stream (lambda) context"
        l1.stream().forEach(e -> {
            try {
                m2(e);
            } catch (Exception ex) {
                System.out.println("Exception!");
            }
        });
    }

    /**
     * Auxiliary method 1
     */
    private static int m1(int n) {
        if (n < 0) throw new IllegalArgumentException();
        return 2 * n;
    }

    /**
     * Auxiliary method 2
     */
    private static int m2(int n) throws Exception {
        if (n < 0) throw new Exception("n < 0");
        return 2 * n;
    }
}
