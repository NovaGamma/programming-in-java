package agh.ii.prinjava.lab07.lst07_02;

import java.util.List;
import java.util.stream.Stream;

/**
 * {@link Stream} interface:
 * <ul>
 *     <li>{@link Stream#skip skip}</li>
 *     <li>{@link Stream#peek peek}</li>
 *     <li>{@link Stream#takeWhile takeWhile} and {@link Stream#dropWhile dropWhile}</li>
 *     <li>{@link Stream#distinct distinct} and {@link Stream#sorted sorted}</li>
 *     <li>{@link Stream} interface: {@link Stream#max max} and {@link Stream#min min}</li>
 *     <li>{@link Stream} interface: {@link Stream#count count}</li>
 *     <li>{@link Stream#findFirst findFirst} and {@link Stream#findAny findAny}</li>
 *     <li>{@link Stream#anyMatch anyMatch}, {@link Stream#allMatch allMatch}, and {@link Stream#noneMatch noneMatch}</li>
 * </ul>
 */
public class Main {
    private static final List<Integer> LIST1 = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
    private static final List<Integer> LIST2 = List.of(1, 50, 3, 100, 30, 20, 7, 8, 9);
    private static final List<Integer> LIST3 = List.of(3, 5, 1, 9, 6, 2, 7, 3, 2, 1, 9, 4, 8, 5, 6, 4, 7, 1, 8, 9, 1);

    /**
     * {@link Stream} interface: {@link Stream#skip skip}
     */
    private static void demo1() {
        System.out.println("demo1...");
        System.out.println("LIST1: " + LIST1);

        Stream<Integer> iStr = LIST1.stream();
        iStr.skip(4).forEach(System.out::println);
    }

    /**
     * {@link Stream} interface: {@link Stream#peek peek} (can help in debugging)
     */
    private static void demo2() {
        System.out.println("\ndemo2...");
        System.out.println("LIST1: " + LIST1);

        Stream<Integer> iStr = LIST1.stream()
                .peek(s -> System.out.println("\npeek1: " + s))
                .filter(n -> n % 2 == 0)
                .peek(s -> System.out.println("peek2: " + s))
                .filter(n -> n > 5)
                .peek(s -> System.out.println("peek3: " + s));

        iStr.forEach(System.out::println);
    }

    /**
     * {@link Stream} interface: {@link Stream#takeWhile takeWhile} and {@link Stream#dropWhile dropWhile}
     */
    private static void demo3() {
        System.out.println("\ndemo3...");

        System.out.println("LIST2: " + LIST2 + ", takeWhile(n -> n < 100)");
        LIST2.stream()
                .takeWhile(n -> n < 100)
                .forEach(System.out::println);

        System.out.println("\nLIST2: " + LIST2 + ", dropWhile(n -> n < 100)");
        LIST2.stream()
                .dropWhile(n -> n < 100)
                .forEach(System.out::println);
    }

    /**
     * {@link Stream} interface: {@link Stream#distinct distinct} and {@link Stream#sorted sorted}
     */
    private static void demo4() {
        System.out.println("\ndemo4...");
        System.out.println("LIST3: " + LIST3 + "\nIts distinct elements:");
        LIST3.stream().distinct().forEach(System.out::println);

        System.out.println("LIST3: " + LIST3 + "\nIts distinct elements (sorted):");
        LIST3.stream().distinct().sorted().forEach(System.out::println);
    }

    /**
     * {@link Stream} interface: {@link Stream#max max} and {@link Stream#min min}
     */
    private static void demo5() {
        System.out.println("\ndemo5...");
        System.out.println("LIST2: " + LIST2);

        System.out.println("Max value: " + LIST2.stream().max(Integer::compareTo).orElseThrow());
        System.out.println("Min value: " + LIST2.stream().min(Integer::compareTo).orElseThrow());
    }

    /**
     * {@link Stream} interface: {@link Stream#count count}
     */
    private static void demo6() {
        System.out.println("\ndemo6...");
        System.out.println("LIST3: " + LIST3);

        System.out.println("Num of elems of LIST3: " + LIST3.stream().count());
        System.out.println("Num of distinct elems in LIST3: " + LIST3.stream().distinct().count());
    }

    /**
     * {@link Stream} interface: {@link Stream#findFirst findFirst} and {@link Stream#findAny findAny}
     */
    private static void demo7() {
        System.out.println("\ndemo7...");
        System.out.println("LIST3: " + LIST3);

        System.out.println("findFirst: " + LIST3.stream().findFirst().orElseThrow());
        System.out.println("findAny: " + LIST3.stream().findAny().orElseThrow());
    }

    /**
     * {@link Stream} interface:
     * {@link Stream#anyMatch anyMatch}, {@link Stream#allMatch allMatch}, and {@link Stream#noneMatch noneMatch}
     */
    private static void demo8() {
        System.out.println("\ndemo8...");

        List<Integer> l1 = LIST1;
        System.out.println("l1: " + l1);
        System.out.println("Are ANY of the elements even? " + l1.stream().anyMatch(x -> x % 2 == 0));
        System.out.println("Are ALL of the elements smaller thant 10? " + l1.stream().allMatch(x -> x < 10));
        System.out.println("Are NONE of the elements greater thant 10? " + l1.stream().noneMatch(x -> x > 10));
        System.out.println("---");

        // OK, "anyMatch" often stops
        Stream<Integer> s1 = Stream.iterate(1, n -> n + 1); // 1, 2, 3, 4,...
        System.out.println("Are ANY of s1 = (1, 2, 3, ...) elements even? " + s1.anyMatch(x -> x % 2 == 0));

        // OK, "allMatch" sometimes stops, when the result is "false" (but can lead to a long waiting!)
        Stream<Integer> s2 = Stream.iterate(1, n -> n + 1); // 1, 2, 3, 4,...
        System.out.println("Are ALL of s2 = (1, 2, 3, ...) elements even? " + s2.allMatch(x -> x % 2 == 0));

        // OK, "noneMatch" sometimes stops, when the result is "false" (but can lead to a long waiting!)
        Stream<Integer> s3 = Stream.iterate(1, n -> n + 1); // 1, 2, 3, 4,...
        System.out.println("Are NONE of s3 = (1, 2, 3, ...) elements even? " + s3.noneMatch(x -> x % 2 == 0));

        // but in some cases even "anyMatch" does NOT stop:
        //Stream<Integer> s2 = Stream.iterate(1, n -> n + 2); // 1, 3, 5, 7,...
        //s2.anyMatch(x -> x % 2 == 0); // does NOT stop (all elements are odd)
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
