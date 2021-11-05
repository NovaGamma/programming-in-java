package agh.ii.prinjava.lab07.lst07_06;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Collectors - implementations of {@link Collector} that implement various useful reduction operations,
 * such as accumulating elements into collections, summarizing elements according to various criteria
 *
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Collectors.html">Collectors</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/stream/Collector.html">Collector</a>
 */
public class Main {
    private static final List<String> PHONETIC_ALPHABET = List.of(
            "alfa", "bravo", "charlie", "delta", "echo", "foxtrot", "golf", "hotel", "india", "juliet",
            "kilo", "lima", "mike", "november", "oscar", "papa", "quebec", "romeo", "sierra", "tango",
            "uniform", "victor", "whiskey", "x-ray", "yankee", "zulu");

    /**
     * Collectors: {@link Collectors#toList toList}, {@link Collectors#toSet toSet}, {@link Collectors#toMap toMap}
     */
    private static void demo1() {
        System.out.println("demo1...");
        // Collection to a list
        final List<String> paAsList = PHONETIC_ALPHABET.stream()
                .map(String::toUpperCase)
                // maybe some other intermediary operations here
                .collect(Collectors.toList());
        System.out.println("paAsList: " + paAsList);

        // Collection to a set
        final Set<String> paAsSet = PHONETIC_ALPHABET.stream()
                .map(String::toUpperCase)
                // ...
                .collect(Collectors.toSet());
        System.out.println("---\npaAsSet: " + paAsSet);

        // Collection to a map
        Map<Character, String> paAsMap = PHONETIC_ALPHABET.stream()
                .map(String::toUpperCase)
                // ...
                .collect(Collectors.toMap(s -> s.charAt(0), s -> s));
        System.out.println("---\nThe Phonetic alphabet:");
        paAsMap.forEach((k, v) -> System.out.println(k + " => " + v));
    }

    /**
     * Collectors:
     * <ul>
     *     <li>{@link Collectors#toCollection toCollection(collectionFactory)} - returns a Collector
     *      that accumulates the input elements into a new Collection, in encounter order</li>
     *
     *      <li>{@link Collectors#collectingAndThen collectingAndThen} - adapts a Collector to perform an additional
     *      finishing transformation</li>
     * </ul>
     */
    private static void demo2() {
        System.out.println("\ndemo2...");
        // Collection to a list (of the given type)
        final List<String> paAsList = PHONETIC_ALPHABET.stream()
                .map(String::toUpperCase)
                // ...
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println("paAsList (ArrayList): " + paAsList);

        // Collection to a set (of the given type)
        final Set<String> paAsSet1 = PHONETIC_ALPHABET.stream()
                .map(String::toUpperCase)
                // ...
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println("---\npaAsSet1 (TreeSet): " + paAsSet1);

        // Collection to a set (of the given type)
        final Set<String> paAsSet2 = PHONETIC_ALPHABET.stream()
                .map(String::toUpperCase)
                // ...
                .collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));
        System.out.println("---\npaAsSet (TreeSet): " + paAsSet2);

        // Collection to a map (of the given type)
        Map<Character, String> paAsMap = PHONETIC_ALPHABET.stream()
                .map(String::toUpperCase)
                // ...
                .collect(Collectors.toMap(s -> s.charAt(0), s -> s, (a, b) -> a + b, TreeMap::new));
        System.out.println("---\nThe Phonetic alphabet (TreeMap):");
        paAsMap.forEach((k, v) -> System.out.println(k + " => " + v));
    }

    /**
     * Collectors: {@link Collectors#toMap toMap} and "duplicate key" exception/problem
     */
    private static void demo3() {
        System.out.println("\ndemo3...");
        try {
            Map<Integer, String> paAsMap1 = PHONETIC_ALPHABET.stream()
                    .map(String::toUpperCase)
                    .collect(Collectors.toMap(String::length, s -> s));
            System.out.println(paAsMap1);
        } catch (Exception e) {
            System.out.println("IllegalStateException: Duplicate key 5 (attempted merging values BRAVO and DELTA)");
            //e.printStackTrace();
        }
        System.out.println("---");

        // to solve the above problem use one of the two other variants of toMap method
        PHONETIC_ALPHABET.stream()
                .collect(Collectors.toMap(String::length, s -> s, (a, b) -> a + ", " + b))
                .forEach((k, v) -> System.out.println(k + " => " + v));
    }

    /**
     * Collectors: {@link Collectors#joining joining}, {@link Collectors#summingInt summingInt},
     * {@link Collectors#averagingInt averagingInt}
     */
    private static void demo4() {
        System.out.println("\ndemo4...");

        final String paAsString = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9).stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        System.out.println("paAsString: " + paAsString);

        final int paTotalStrLen1 = PHONETIC_ALPHABET.stream()
                .collect(Collectors.summingInt(String::length));
        System.out.println("---\npaTotalStrLen1: " + paTotalStrLen1);

        final double paAverStrLen = PHONETIC_ALPHABET.stream()
                .collect(Collectors.averagingInt(String::length));
        System.out.println("---\npaAverStrLen: " + paAverStrLen);

        // Equivalent to .collect(Collectors.summingInt(String::length))
        final int paTotalStrLen2 = PHONETIC_ALPHABET.stream()
                .mapToInt(String::length)
                .sum();
        System.out.println("---\npaTotalStrLen2: " + paTotalStrLen2);
    }

    /**
     * Collectors: {@link Collectors#partitioningBy partitioningBy}, {@link Collectors#groupingBy groupingBy}
     */
    private static void demo5() {
        System.out.println("\ndemo5...");

        PHONETIC_ALPHABET.stream()
                .map(String::toUpperCase)
                .collect(Collectors.partitioningBy(s -> s.length() % 2 == 0))
                .forEach((k, v) -> System.out.println(k + " => " + v));

        System.out.println("---");
        PHONETIC_ALPHABET.stream()
                .map(String::toUpperCase)
                .collect(Collectors.groupingBy(String::length))
                .forEach((k, v) -> System.out.println(k + " => " + v));

        System.out.println("---");
        PHONETIC_ALPHABET.stream()
                .map(String::toUpperCase)
                //.collect(Collectors.groupingBy(s -> s.length(), Collectors.toSet()))
                .collect(Collectors.groupingBy(String::length, Collectors.toCollection(TreeSet::new)))
                .forEach((k, v) -> System.out.println(k + " => " + v));

        System.out.println("---");
        PHONETIC_ALPHABET.stream()
                .map(String::toUpperCase)
                .collect(Collectors.groupingBy(String::length, Collectors.counting()))
                .forEach((k, v) -> System.out.println(k + " => " + v));

        System.out.println("---");
        PHONETIC_ALPHABET.stream()
                .map(String::toUpperCase)
                .collect(Collectors.groupingBy(String::length, Collectors.joining(", ")))
                .forEach((k, v) -> System.out.println(k + " => " + v));
    }

    /**
     * Down-streams - having a collector you specify what happens to the value after the first collecting operation
     */
    private static void demo6() {
        System.out.println("\ndemo6...");

        // Group by the length, and then keep (filter) only those that start with a vowel
        PHONETIC_ALPHABET.stream()
                .collect(
                        Collectors.groupingBy(
                                String::length,
                                Collectors.filtering(s -> "aeiou".contains(s.substring(0, 1)),
                                        Collectors.toList())))
                .forEach((k, v) -> System.out.println(k + " => " + v));

        System.out.println("---");

        // Group by the length, and then extract the first letter of each
        PHONETIC_ALPHABET.stream()
                .collect(
                        Collectors.groupingBy(
                                String::length,
                                Collectors.mapping(s -> s.charAt(0),
                                        Collectors.toList())))
                .forEach((k, v) -> System.out.println(k + " => " + v));
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
