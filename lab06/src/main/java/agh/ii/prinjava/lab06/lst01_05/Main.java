package agh.ii.prinjava.lab06.lst01_05;

import java.util.List;
import java.util.Optional;

/**
 * {@link Optional} - a container object which <i>may</i> or <i>may not</i> contain a non-null value.
 *
 * <p>{@code Optional} is primarily intended for use as a method return type where there is a clear need
 * to represent "no result" and where using {@code null} is likely to cause errors.
 * <p>A variable whose type is {@code Optional} should never itself be {@code null};
 * it should always point to an {@code Optional} instance.
 *
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Optional.html">Optional</a>
 */
public class Main {
    private static final List<String> LIST1 = List.of("Alice", "Emma", "Rose", "Sophie");
    private static final List<String> LIST2 = List.of();
    private static final List<String> LIST3 = null;
    private static final String UNKNOWN_HEAD_STRING = "Unknown";

    /**
     * Approach 1: throwing a runtime exception when no valid result can be returned
     */
    private static <E> E headOf_v1(List<E> list) {
        if (list == null || list.size() == 0) throw new IllegalArgumentException();
        return list.get(0);
    }

    /**
     * Approach 2: returning {@code null} to represent "no result"
     */
    private static <E> E headOf_v2(List<E> list) {
        return (list != null && list.size() > 0) ? list.get(0) : null;
    }

    /**
     * Approach 3: using {@code Optional} - the recommended approach in many scenarios
     *
     * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Optional.html">Optional</a>
     */
    private static <E> Optional<E> headOf_v3(List<E> list) {
        return (list != null && list.size() > 0) ? Optional.of(list.get(0)) : Optional.empty();
    }

    /**
     * The use of {@link #headOf_v1}: (runtime) exceptions handling is required
     */
    private static void demo1() {
        System.out.println("demo1...");
        System.out.println("LIST1: " + LIST1 + ", LIST2: " + LIST2 + ", LIST3: " + LIST3);

        // "Risky" operation: no "try-catch" => potential runtime exception
        String head = headOf_v1(LIST1);
        System.out.println("headOf_v2(LIST1): " + head);

        //head = headOf_v1(LIST2); // <- RuntimeException!
        //System.out.println("headOf_v2(LIST2): " + head);

        // "try-catch" to be on the safe-side
        try {
            head = headOf_v1(LIST3); // <- RuntimeException!
            System.out.println("headOf_v2(LIST3): " + head);
        } catch (RuntimeException e) {
            System.out.println("headOf_v1(LIST3) -> RuntimeException!");
        }
    }

    /**
     * The use of {@link #headOf_v2}: the "NullPointerException syndrome"
     */
    private static void demo2() {
        System.out.println("\ndemo2...");
        System.out.println("LIST1: " + LIST1 + ", LIST2: " + LIST2 + ", LIST3: " + LIST3);

        String head = headOf_v2(LIST1);
        if (head != null) { // <- testing for null
            System.out.println("headOf_v2(LIST1): " + head);
        } else {
            System.out.println("headOf_v2(LIST1) is null");
        }

        // Simplification of the above with the use of "ternary operator" (i.e., "? :")
        head = headOf_v2(LIST2);
        System.out.println("headOf_v2(LIST2): " + ((head != null) ? head : UNKNOWN_HEAD_STRING));

        head = headOf_v2(LIST3);
        System.out.println("headOf_v2(LIST3): " + ((head != null) ? head : UNKNOWN_HEAD_STRING));
    }

    /**
     * The use of {@link #headOf_v3}: {@code Optional} to the rescue
     * (problems of {@link #headOf_v1} and  {@link #headOf_v2} are gone)
     *
     * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Optional.html">Optional</a>
     */
    private static void demo3() {
        System.out.println("\ndemo3...");
        Optional<String> head = headOf_v3(LIST1);
        System.out.println("headOf_v2(LIST1): " + head.orElse(UNKNOWN_HEAD_STRING));

        head = headOf_v3(LIST2);
        System.out.println("headOf_v2(LIST2): " + head.orElse(UNKNOWN_HEAD_STRING));

        head = headOf_v3(LIST3);
        System.out.println("headOf_v2(LIST3): " + head.orElse(UNKNOWN_HEAD_STRING));
    }

    /**
     * {@code Optional} makes the code more readable: the middle name is declared as optional in {@link Person2}
     */
    private static void demo4() {
        System.out.println("\ndemo4...");
        Person1 p11 = new Person1("John", "Fitzgerald", "Kennedy");
        Person1 p12 = new Person1("Ronald", "Wilson", "Reagan");
        Person1 p13 = new Person1("George", null, "Washington"); // null = no middle name
        System.out.println("p11: " + p11);
        System.out.println("p12: " + p12);
        System.out.println("p13: " + p13);

        System.out.println("---");

        Person2 p21 = new Person2("John", Optional.of("Fitzgerald"), "Kennedy");
        Person2 p22 = new Person2("Ronald", Optional.of("Wilson"), "Reagan");
        Person2 p23 = new Person2("George", Optional.empty(), "Washington"); // no middle name
        System.out.println("p21: " + p21);
        System.out.println("p22: " + p22);
        System.out.println("p23: " + p23);
    }

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
        demo4();
    }
}

/**
 * Auxiliary classes
 */
record Person1(String givenName,
               String middleName, // <- some people do not have a middle name
               String surname) {
}

/**
 * {@code Optional} makes the model more explicit: the middleName is an optional value
 *
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Optional.html">Optional</a>
 */
record Person2(String givenName,
               Optional<String> middleName,
               String surname) {
}
