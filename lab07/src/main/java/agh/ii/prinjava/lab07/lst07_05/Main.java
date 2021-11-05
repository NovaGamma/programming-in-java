package agh.ii.prinjava.lab07.lst07_05;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.LongSummaryStatistics;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * {@link IntStream}, {@link LongStream}, and {@link DoubleStream} are special type of streams for processing
 * a sequence of primitive int, long, and double values.
 *
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/IntStream.html">IntStream</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/LongStream.html">LongStream</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/DoubleStream.html">DoubleStream</a>
 */
public class Main {

    /**
     * IntStream
     */
    private static void demo1() {
        System.out.println("demo1...");
        int[] ints = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("ints: " + Arrays.toString(ints));
        System.out.println("Sum of ints: " + IntStream.of(ints).sum());
        IntStream.of(ints).average().ifPresent(avg -> System.out.println("The average of ints: " + avg));

        IntSummaryStatistics stats = IntStream.of(ints).summaryStatistics();
        streamSummary(stats.getCount(), stats.getMin(), stats.getMax(), stats.getSum(), stats.getAverage());

        System.out.println("---");
        IntStream.of(ints)
                .filter(e -> e % 2 == 0)
                .mapToDouble(e -> Math.pow(2.5, e))
                .peek(System.out::println)
                .mapToInt(e -> (int) e)
                .forEach(System.out::println);
    }

    /**
     * LongStream
     */
    private static void demo2() {
        System.out.println("\ndemo2...");
        long[] longs = {1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L};
        System.out.println("longs: " + Arrays.toString(longs));
        System.out.println("Sum of longs: " + LongStream.of(longs).sum());
        LongStream.of(longs).average().ifPresent(avg -> System.out.println("The average of longs: " + avg));

        LongSummaryStatistics stats = LongStream.of(longs).summaryStatistics();
        streamSummary(stats.getCount(), stats.getMin(), stats.getMax(), stats.getSum(), stats.getAverage());
    }

    /**
     * DoubleStream
     */
    private static void demo3() {
        System.out.println("\ndemo3...");
        double[] doubles = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0};
        System.out.println("doubles: " + Arrays.toString(doubles));
        System.out.println("Sum of doubles: " + DoubleStream.of(doubles).sum());
        DoubleStream.of(doubles).average().ifPresent(avg -> System.out.println("The average of doubles: " + avg));

        DoubleSummaryStatistics stats = DoubleStream.of(doubles).summaryStatistics();
        streamSummary(stats.getCount(), stats.getMin(), stats.getMax(), stats.getSum(), stats.getAverage());
    }

    /**
     * Auxiliary method (prints out the stream summary)
     */
    private static <T> void streamSummary(T count, T min, T max, T sum, double avg) {
        System.out.printf("---\nThe summary of the stream: \n%-10s%10s\n%-10s%10s\n%-10s%10s\n%-10s%10s\n%-10s%10.2f\n",
                " Count:", count,
                " Max:", max,
                " Min:", min,
                " Sum:", sum,
                " Average:", avg);
    }

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
    }
}