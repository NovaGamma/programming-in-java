package agh.ii.prinjava.lab06.lst01_02;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;

/**
 * <i>Functional interfaces</i> - often representing abstract concepts like functions, actions, or predicates -
 * provide target types for lambda expressions and method references. Each functional interface
 * has a single abstract method, called the <i>functional method</i> for that functional interface, to which the lambda
 * expression's parameter and return types are matched or adapted.
 * Functional interfaces can provide a target type in multiple contexts, such as assignment context, method invocation,
 * or cast context.
 *
 * <p>There are nine basic functional interfaces:
 * <ol>
 *     <li>{@link Consumer}</li>
 *     <li>{@link BiConsumer}</li>
 *     <li>{@link Supplier}</li>
 *     <li>{@link Function}</li>
 *     <li>{@link BiFunction}</li>
 *     <li>{@link Predicate}</li>
 *     <li>{@link BiPredicate}</li>
 *     <li>{@link UnaryOperator}</li>
 *     <li>{@link BinaryOperator}</li>
 * </ol>
 *
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/package-summary.html">java.util.function</a>
 */
public class Main {
    /**
     * {@link Consumer} represents an operation that accepts a single input argument and returns no result.
     * Unlike most other functional interfaces, Consumer is expected to operate via side effects
     *
     * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/Consumer.html">Consumer</a>
     */
    private static void demo1() {
        System.out.println("demo1...");
        Consumer<String> c1 = s -> System.out.println(s);
        c1.accept("Bonjour"); // -> Bonjour

        // an example related to Java Collection Framework
        List<Integer> l1 = List.of(1, 2, 3, 4, 5);
        l1.forEach(t -> System.out.println(t));
        System.out.println("---");
        l1.forEach(System.out::println); // the same with a method reference
        System.out.println("---");

        //or, more explicitly
        Consumer<Integer> c2 = t -> System.out.println(t);
        l1.forEach(c2);
    }

    /**
     * {@link BiConsumer} represents an operation that accepts two input arguments and returns no result.
     * This is the two-arity specialization of {@link Consumer}. Unlike most other functional interfaces,
     * BiConsumer is expected to operate via side effects
     *
     * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/BiConsumer.html">BiConsumer</a>
     */
    private static void demo2() {
        System.out.println("\ndemo2...");
        BiConsumer<String, Integer> c1 = (t, u) -> System.out.println(t + " " + u);
        c1.accept("Fahrenheit", 451);

        // An example related to the Java Collection Framework
        Map<ThreeColours, String> title2Actors = Map.of(
                ThreeColours.BLUE, "Juliette Binoche",
                ThreeColours.WHITE, "Zbigniew Zamachowski",
                ThreeColours.RED, "Irene Jacob");

        title2Actors.forEach((t, s) -> System.out.println("The leading actor of " + t + " is " + s));
        System.out.println("---");

        //or, more explicitly
        BiConsumer<ThreeColours, String> c2 = (t, s) -> System.out.println("The leading actor of " + t + " is " + s);
        title2Actors.forEach(c2);
    }

    /**
     * {@link Supplier} represents a supplier of results. There is no requirement that a new or distinct
     * result be returned each time the supplier is invoked.
     *
     * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/Supplier.html">Supplier</a>
     */
    private static void demo3() {
        System.out.println("\ndemo3...");
        Supplier<Integer> s1 = () -> 42;
        System.out.println("Supplier s1 always gives: " + s1.get());

        Supplier<Integer> s2 = () -> ThreadLocalRandom.current().nextInt(0, 100);
        System.out.println("The random number form [0,100): " + s2.get());
    }

    /**
     * {@link Function} - represents a function that accepts one argument and produces a result
     *
     * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/Function.html">Function</a>
     */
    private static void demo4() {
        System.out.println("\ndemo4...");
        Function<String, Integer> f1 = s -> s.length();
        String s = "abc";
        System.out.println("The length of " + s + " is " + f1.apply(s));
    }

    /**
     * {@link BiFunction} - represents a function that accepts two arguments and produces a result.
     * This is the two-arity specialization of {@link Function}
     *
     * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/BiFunction.html">BiFunction</a>
     */
    private static void demo5() {
        System.out.println("\ndemo5...");
        BiFunction<Integer, Integer, String> bf = (i1, i2) -> i1 + " " + i2;
        int i1 = 111;
        int i2 = 222;
        System.out.println("The concatenation of " + i1 + " and " + i2 + " gives " + bf.apply(111, 222));
    }

    /**
     * {@link Predicate} - represents a predicate (boolean-valued function) of one argument
     *
     * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/Predicate.html">Predicate</a>
     */
    private static void demo6() {
        System.out.println("\ndemo6...");
        Predicate<Integer> isEven = i -> i % 2 == 0;
        Predicate<Integer> isOdd = i -> i % 2 == 1;
        System.out.println("Is 42 even? " + isEven.test(42));
        System.out.println("Is 451 odd? " + isOdd.test(451));
    }

    /**
     * {@link BiPredicate} - represents a predicate (boolean-valued function) of two arguments.
     * This is the two-arity specialization of {@link Predicate}
     *
     * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/BiPredicate.html">BiPredicate</a>
     */
    private static void demo7() {
        System.out.println("\ndemo7...");
        BiPredicate<String, String> bp = (s1, s2) -> s1.length() == s2.length();
        System.out.println("Are lengths of abc and efg equal? " + bp.test("abc", "efg"));
    }

    /**
     * {@link UnaryOperator} - represents an operation on a single operand that produces a result of the same typ as
     * its operand. This is a specialization of {@link Function } for the case where the operand and result
     * are of the same type
     *
     * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/UnaryOperator.html">UnaryOperator</a>
     */
    private static void demo8() {
        System.out.println("\ndemo8...");
        UnaryOperator<Integer> uOp1 = i -> 2 * i;
        Integer v = uOp1.apply(5);
        System.out.println("uOp1(5) = " + v);

        UnaryOperator<String> uOp2 = s -> new StringBuilder(s).reverse().toString();
        String s1 = "TENET";
        System.out.println(s1 + " read backwards: " + uOp2.apply(s1));
    }

    /**
     * {@link BinaryOperator} - represents an operation upon two operands of the same type, producing a result of
     * the same type as the operands. This is a specialization of {@link BiFunction} for the case where the operands
     * and the result are all of the same type.
     *
     * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/BinaryOperator.html">BinaryOperator</a>
     */
    private static void demo9() {
        System.out.println("\ndemo9...");
        BinaryOperator<Integer> plus = (a, b) -> a + b;
        System.out.println("5 + 10 = " + plus.apply(5, 10));
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
        demo9();
    }
}

enum ThreeColours {BLUE, WHITE, RED}
