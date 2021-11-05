package agh.ii.prinjava.lab07.lst07_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Classes from {@link java.util.stream} support functional-style operations on streams of elements,
 * such as map-reduce transformations on collections.
 *
 * <p>The key abstraction introduced in this package is <i>stream</i>. The classes {@link Stream}, {@link IntStream},
 * {@link LongStream}, and {@link DoubleStream} are streams over objects and the primitive
 * {@code int}, {@code long}, and {@code double} types.
 *
 * <p>Streams differ from collections in several ways:
 * <ul>
 *     <li><i>No storage</i>. A stream is not a data structure that stores elements; instead, it conveys elements
 *     from a source such as a data structure, an array, a generator function, or an I/O channel,
 *     through a pipeline of computational operations</li>
 *
 *     <li><i>Functional in nature</i>. An operation on a stream produces a result, but does not modify its source.
 *     For example, filtering a Stream obtained from a collection produces a new Stream without the filtered elements,
 *     rather than removing elements from the source collection</li>
 *
 *     <li><i>Laziness-seeking</i>. Many stream operations, such as filtering, mapping, or duplicate removal,
 *     can be implemented lazily, exposing opportunities for optimization.
 *      Stream operations are divided into intermediate (Stream-producing) operations and terminal
 *      (value- or side-effect-producing) operations. Intermediate operations are always lazy</li>
 *
 *     <li><i>Possibly unbounded</i>. While collections have a finite size, streams need not. Short-circuiting operations
 *     such as {@link Stream#limit(long)} or {@link Stream#findFirst()} can allow computations on infinite streams
 *     to complete in finite time</li>
 *
 *     <li><i>Consumable</i>. The elements of a stream are only visited once during the life of a stream.
 *     Like an {@link Iterator}, a new stream must be generated to revisit the same elements of the source</li>
 * </ul>
 *
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html">java.util.stream</a>
 * @see <a href=https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html>Stream</a>
 */
public class Main {
    /**
     * Streams can be obtained in a number of ways, for instance:
     * <ul>
     *     <li>From static factory methods on the stream classes, such as
     *         {@link Stream#of}, {@link IntStream#range} or {@link Stream#iterate}</li>
     *     <li>From a Collection via the {@link Collection#stream} and {@link Collection#parallelStream} methods</li>
     *     <li>From an array via {@link Arrays#stream}</li>
     *     <li>The lines of a file can be obtained from {@link BufferedReader#lines}</li>
     *     <li>Streams of file paths can be obtained from methods in {@link Files}</li>
     *     <li>Streams of random numbers can be obtained from {@link Random#ints}</li>
     * </ul>
     */
    private static void demo1() {
        System.out.println("demo1...");
        // Stream.empty() - a finite source; to avoid using null (in many cases considered an anti-pattern!)
        Stream<Integer> s1 = Stream.empty(); // finite source
        s1.forEach(System.out::println); // empty stream
        System.out.println("---");

        // Stream.of(...) - a finite source
        Stream<Integer> s2 = Stream.of(1, 2, 3, 4, 5); // finite source
        s2.forEach(System.out::println);
        System.out.println("---");
    }

    /**
     * {@link Stream#generate}, {@link Stream#iterate} and {@link Stream#limit limit}
     */
    private static void demo2() {
        System.out.println("\ndemo2...");

        // Stream.generate(supplier) - infinite source
        // Note: this is a general-purpose random number generation mechanism in the modern Java,
        // java.util.Random is often considered, not good enough (legacy)
        Supplier<Integer> sup = () -> ThreadLocalRandom.current().nextInt(10); // infinite source
        Stream<Integer> s3 = Stream.generate(sup);
        // s3.forEach(System.out::println); // <- this will run forever!
        // to overcome this
        s3.limit(5).forEach(System.out::println); // limit(n)
        System.out.println("---");

        // Stream.iterate(T seed, UnaryOperator<T> f), infinite source: seed, f(seed), f(f(seed)),...
        Stream<Integer> s4 = Stream.iterate(1, n -> n + 1); // natural numbers: 1, 2, 3,...
        s4.limit(5).forEach(System.out::println);
        System.out.println("---");

        // Stream.iterate(T seed, Predicate<? super T> hasNext, UnaryOperator<T> next);
        // for (T index=seed; hasNext.test(index); index = next.apply(index)) {
        //    ...
        // }
        Stream<Integer> s5 = Stream.iterate(1, n -> n <= 5, n -> n + 1);
        s5.forEach(System.out::println);
    }

    /**
     * Fibonacci sequence as an infinite stream
     */
    private static void demo3() {
        System.out.println("\ndemo3...");

        Stream<long[]> fibs = Stream.iterate(new long[]{1, 1}, p -> new long[]{p[1], p[0] + p[1]});
        fibs.limit(10).forEach(p -> System.out.println(p[0]));
    }

    /**
     * Stream from a list or set
     */
    private static void demo4() {
        System.out.println("\ndemo4...");

        // From a list
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Stream<Integer> stream1 = list.stream();
        stream1.forEach(System.out::println);
        System.out.println("---");

        // From a set
        Set<Integer> set = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Stream<Integer> stream2 = set.stream();
        stream2.forEach(System.out::println);
    }

    /**
     * To stream a map one needs to get a stream of entries from its {@code entrySet()}, i.e.,
     * <p>{@code Stream<Map.Entry<K,V>> stream = map.entrySet().stream()}
     */
    private static void demo5() {
        System.out.println("\ndemo5...");

        Map<Character, String> fonAlpha = Map.of(
                'a', "alfa", 'b', "bravo", 'c', "charlie", 'd', "delta");

        //Stream<Character, String> fonAlphaStream = fonAlpha.stream(); // cannot create stream this way!
        Stream<Map.Entry<Character, String>> fonAlphaStream = fonAlpha.entrySet().stream();
        fonAlphaStream.forEach(entry -> System.out.println(entry.getKey() + " => " + entry.getValue()));
    }

    /**
     * Stream from an array
     */
    private static void demo6() {
        System.out.println("\ndemo6...");
        int[] ints1 = {1, 2, 3, 4, 5};

        //Stream<int> si0 = Arrays.stream(ints1).boxed(); // Error: Type argument cannot be of primitive type!
        Stream<Integer> si1 = Arrays.stream(ints1).boxed(); // boxed() needed, ints1 are of int [] type
        si1.forEach(System.out::println);
        System.out.println("---");

        Integer[] ints2 = {1, 2, 3, 4, 5};
        Stream<Integer> si2 = Arrays.stream(ints2);
        si2.forEach(System.out::println);
    }

    /**
     * A stream ({@link IntStream}) form {@link IntStream#range} and {@link IntStream#rangeClosed}
     */
    private static void demo7() {
        System.out.println("\ndemo7...");
        IntStream intStr1 = IntStream.range(0, 5);
        intStr1.forEach(System.out::println);
        System.out.println("---");

        IntStream intStr2 = IntStream.rangeClosed(0, 5);
        intStr2.forEach(System.out::println);
    }

    /**
     * Stream from {@code Files.lines}
     *
     * <p>The returned stream encapsulates a {@link Reader}. If timely disposal of file system resources is required,
     * the try-with-resources construct should be used to ensure that the stream's close method is invoked
     * after the stream operations are completed.
     *
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html">Files</a>
     */
    private static void demo8() {
        System.out.println("\ndemo8...");
        final String thisFileName = "lab07/src/main/java/agh/ii/prinjava/lab07/lst07_01/Main.java";
        try (Stream<String> lines = Files.lines(Path.of(thisFileName))) {
            lines.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("IOException!");
            //e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
        demo4();
        demo5();
        demo6();
        demo7();
        demo8();
    }
}
