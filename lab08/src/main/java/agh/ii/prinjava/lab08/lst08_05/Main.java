package agh.ii.prinjava.lab08.lst08_05;

import java.util.List;
import java.util.StringJoiner;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Parallel streams
 */
public class Main {
    private static final List<Long> LIST1 =
            Stream.iterate(1L, n -> n <= 2048, n -> n + 1).collect(Collectors.toList());

    private static void demo1() {
        System.out.println("demo1...");
        Stream.of(1, 2, 3, 4, 5)
                .forEach(e -> System.out.println("Thread " + Thread.currentThread().getName() + ": " + e));
    }

    private static void demo2() {
        System.out.println("\ndemo2...");
        IntStream.range(0, 8)
                .parallel()
                .forEach(e -> System.out.println("Thread " + Thread.currentThread().getName() + ": " + e));
    }

    /**
     * forEachOrdered
     */
    private static void demo3() {
        System.out.println("\ndemo3...");
        IntStream.range(0, 8)
                .parallel()
                .forEachOrdered(e -> System.out.println("Thread " + Thread.currentThread().getName() + ": " + e));
    }

    private static void demo4() {
        System.out.println("\ndemo4...");

        UnaryOperator<Integer> uOp = e -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            return 10 * e;
        };

        List<Integer> l1 = IntStream.range(0, 8).boxed().collect(Collectors.toList());

        System.out.println("Sequential processing o list " + l1 + " is about to begin...");
        long t1 = System.currentTimeMillis();
        final List<Integer> ml1 = l1.stream()
                .map(uOp)
                .collect(Collectors.toList());
        long t2 = System.currentTimeMillis();
        System.out.println("sequential dt = " + (t2 - t1));

        System.out.println("\nParallel processing o list " + l1 + " is about to begin...");
        t1 = System.currentTimeMillis();
        final List<Integer> ml2 = l1.parallelStream()
                .map(uOp)
                .collect(Collectors.toList());
        t2 = System.currentTimeMillis();
        System.out.println("parallel dt = " + (t2 - t1));
    }

    private static void demo5() {
        System.out.println("\ndemo5...");
        long t1 = System.currentTimeMillis();
        long sum1 = LIST1.stream()
                .reduce(0L,
                        (acc, e) -> acc + e, // <- accumulator
                        (res1, res2) -> null); // <- the combiner/merger is not used in sequential streams
        long t2 = System.currentTimeMillis();

        System.out.println("sum1: " + sum1 + " calculated in " + (t2 - t1) + " milliseconds");

        t1 = System.currentTimeMillis();
        long sum2 = LIST1.parallelStream()
                .reduce(0L,
                        (acc, e) -> acc + e,  // <- accumulator
                        (res1, res2) -> res1 + res2); // <- combiner/merger (in parallel streams the combiner is necessary)
        t2 = System.currentTimeMillis();
        System.out.println("\nsum2: " + sum2 + " calculated in " + (t2 - t1) + " milliseconds");
    }

    /**
     * Inefficient way of reduction (consider using {@link StringBuilder} or {@link Collectors#joining} instead)
     */
    private static void demo6() {
        System.out.println("\ndemo6...");

        String s1 = LIST1.parallelStream()
                .filter(e -> e <= 100)
                .map(Object::toString)
                .reduce("",
                        (acc, e) -> acc + " " + e,  // <- accumulator
                        (res1, res2) -> res1 + res2); // combiner/merger

        System.out.println("\ns1:" + s1);
    }

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
        demo4();
        demo5();
        demo6();
    }
}
