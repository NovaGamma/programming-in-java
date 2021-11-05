package agh.ii.prinjava.lab07.lst07_04;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.*;

/**
 * Stream operations are divided into:
 * <ul>
 *     <li>intermediate</li>
 *     <li>terminal</li>
 * </ul>
 * and are combined to form <i>stream pipelines</i>.
 *
 * <p>A <i>stream pipeline</i> consists of:
 * <ol>
 *     <li>a source (such as a {@link Collection}, an array, a generator function, or an I/O channel)</li>
 *     <li>followed by zero or more intermediate operations such as
 *     {@link Stream#filter filter} or {@link Stream#map map}</li>
 *     <li>and a terminal operation such as {@link Stream#forEach forEach} or {@link Stream#reduce reduce}</li>
 * </ol>
 *
 * <p><i>Intermediate operations</i> return a new stream. They are always <i>lazy</i>; executing an intermediate operation
 * such as {@link Stream#filter filter} does not actually perform any filtering, but instead creates a new stream that,
 * when traversed, contains the elements of the initial stream that match the given predicate.
 * Traversal of the pipeline source does not begin until the terminal operation of the pipeline is executed.
 *
 * <p><i>Terminal operations</i>, such as {@link Stream#forEach forEach} or {@link IntStream#sum sum},
 * may traverse the stream to produce a result or a side effect. After the terminal operation is performed,
 * the stream pipeline is considered consumed, and can no longer be used;
 * if you need to traverse the same data source again, you must return to the data source to get a new stream.
 * In almost all cases (except {@link Stream#iterator iterator} and {@link Stream#spliterator spliterator}),
 * terminal operations are <i>eager</i>, completing their traversal of the data source and processing of the pipeline
 * before returning.
 *
 * <p><i>Note</i>: some operations are <i>short-circuiting</i>. An intermediate operation is short-circuiting if,
 * when presented with infinite input, it may produce a finite stream as a result.
 * A terminal operation is short-circuiting if, when presented with infinite input, it may terminate in finite time.
 * Having a short-circuiting operation in the pipeline is a necessary, but not sufficient, condition for the processing
 * of an infinite stream to terminate normally in finite time.
 *
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/package-summary.html">java.util.stream</a>
 */
public class Main {
    private static final List<String> LOTR_3_QUOTES = List.of(
            "There is no curse in Elvish, Entish, or the tongues of Men for this treachery.",
            "There is only one Lord of the Ring, only one who can bend it to his will. And he does not share power.",
            "Even the smallest person can change the course of the future.");

    /**
     * {@link Stream} interface: {@link Stream#filter filter} - an intermediate operation that returns
     * a stream consisting of the elements that match the given predicate. This is an intermediate operation.
     */
    private static void demo1() {
        System.out.println("demo1...");
        List<Integer> l1 = List.of(1, 2, 3, 4, 5);
        System.out.println("l1: " + l1);
        System.out.print("filtered with (e -> e % 2 == 0): ");
        l1.stream()                            // (1) the stream source
                .filter(e -> e % 2 == 0)       // (2) intermediate operation
                .forEach(s -> System.out.print(s + " ")); // (3) terminal operation

        //or
        System.out.println("\n---");
        List<Integer> l2 = l1.stream()
                .filter(e -> e % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("l2: " + l2 + "\n---");

        // Compare to the following
        List<Integer> l3 = new ArrayList<>();
        for (var e : l1) { // note the sequential structure of the computation
            if (e % 2 == 0) {
                l3.add(e);
            }
        }
        System.out.println("l3: " + l3);
    }

    /**
     * {@link Stream} interface: {@link Stream#map map} - an intermediate operation that returns a stream consisting
     * of the results of applying the given function to the elements of this stream.
     *
     * <p>Given {@link Stream}:
     * <ul>
     *     <li>{@code map(Function): Stream -> Stream}</li>
     *     <li>{@code mapToInt(ToIntFunction): Stream -> IntStream}</li>
     *     <li>{@code mapToLong(ToLongFunction): Stream -> LongStream}</li>
     *     <li>{@code mapToDouble(ToDoubleFunction): Stream -> DoubleStream}</li>
     *     <li>{@code mapToObj(Function): {_|Int|Long|Double}Stream -> Stream}</li>
     * </ul>
     *
     * <p>Given {@link IntStream}:
     * <ul>
     *     <li>{@code map(To{Int|Long|Double}Function): IntStream -> {Int|Long|Double}Stream}</li>
     *     <li>{@code mapToObj(IntFunction): IntStream -> Stream}</li>
     * </ul>
     *
     * <p>Similarly, for {@link LongStream} and {@link DoubleStream}
     */
    private static void demo2() {
        System.out.println("\ndemo2...");
        List<String> l1 = List.of("alpha", "bravo", "charlie", "delta");
        l1.stream().map(String::toUpperCase).forEach(System.out::println);
        System.out.println("---");

        List<String> l2 = l1.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("l2 : " + l2 + "\n--");

        List<Integer> l3 = l1.stream().map(String::length).collect(Collectors.toList());
        System.out.println("l3 : " + l3 + "\n--");

        // Compare to the following
        List<Integer> l4 = new ArrayList<>();
        for (var e : l1) { // again, note the sequential structure of the computation
            l4.add(e.length());
        }
        System.out.println("l4 : " + l4 + "\n---");

        List<List<Integer>> ls5 = List.of(List.of(1, 2), List.of(3, 4, 5), List.of(6, 7, 8, 9));
        System.out.println("ls5: " + ls5);

        final List<Integer> ls5Lengths = ls5.stream().map(List::size).collect(Collectors.toList());
        System.out.println("ls5Lengths: " + ls5Lengths);

        final List<Integer> ls5Sums = ls5.stream()
                .map(l -> l.stream().mapToInt(e -> e).sum())
                .collect(Collectors.toList());
        System.out.println("ls5Sums: " + ls5Sums);
    }

    /**
     * {@link Stream} interface: {@link Stream#flatMap flatMap} - an intermediate operation that has the effect
     * of applying a <i>one-to-many</i> transformation to the elements of the stream, and then flattening
     * the resulting elements into a new stream ({@code flatMap} = {@code flatten . map}).
     */
    private static void demo3() {
        System.out.println("\ndemo3...");
        List<List<Integer>> ls1 = List.of(List.of(1, 2), List.of(3, 4, 5), List.of(6, 7, 8, 9));
        System.out.println("ls1: " + ls1);

        // flatten: [[1, 2], [3, 4, 5], [6, 7, 8, 9]] -> [1, 2, 3, 4, 5, 6, 7, 8, 9]
        final List<Integer> flatten_ls1 = ls1.stream()
                .flatMap(Collection::stream) // flatten . map (e -> e)
                .collect(Collectors.toList());
        System.out.println("flatten_ls1: " + flatten_ls1 + "\n---");

        String lotrSplitRegEx = "\\s|,\\s?|\\.\\s?"; // " " or ", ", "," or ". " or "."
        final List<String> lotr3QuotesWords = LOTR_3_QUOTES.stream()
                .flatMap(line -> Pattern.compile(lotrSplitRegEx).splitAsStream(line)
                        .map(String::toLowerCase))
                .collect(Collectors.toList());
        System.out.println("LOTR_3_QUOTES as a word list: " + lotr3QuotesWords);
    }

    /**
     * {@link Stream} interface: {@link Stream#reduce reduce} - a terminal operation that performs a reduction
     * on the elements of this stream, using an associative accumulation function, and returns an {@link Optional}
     * describing the reduced value
     *
     * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Stream.html">Stream</a>
     */
    private static void demo4() {
        System.out.println("\ndemo4...");
        List<Integer> l1 = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        // (1), no identity given -> Optional result
        System.out.println("l1.reduce((+)): " + l1.stream().reduce(Integer::sum).orElseThrow());

        // (2), initial value of the accumulator (identity) given
        System.out.println("l1.reduce(0, accumulate even only): " +
                l1.stream().reduce(0, (acc, e) -> (e % 2 == 0) ? acc + e : acc));

        System.out.println("l1.mapToDouble.reduce(0, (+)): " +
                l1.stream().mapToDouble(e -> e).reduce(0.0, Double::sum));

        System.out.println("l1.reduce(-45, (+)): " + l1.stream().reduce(-45, Integer::sum));

        System.out.println("l1.reduce(\"\", concat): " +
                l1.stream().map(Object::toString).reduce("", (acc, e) -> acc + e));
    }

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
        demo4();
    }
}
