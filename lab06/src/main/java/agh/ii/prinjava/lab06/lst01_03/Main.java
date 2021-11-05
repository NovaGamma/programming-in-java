package agh.ii.prinjava.lab06.lst01_03;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A <i>higher-order function</i> is a function that returns and/or takes other functions as argument(s)
 */
public class Main {
    private static final List<Integer> LIST_1TO5 = List.of(1, 2, 3, 4, 5, -6);

    /**
     * DRY - Do not Repeat Yourself!
     *
     * @see <a href="https://en.wikipedia.org/wiki/Don%27t_repeat_yourself">DRY principle</a>
     */
    private static int sumOf(List<Integer> lst) {
        int sum = 0;
        for (var e : lst) {
            sum += e;
        }
        return sum;
    }

    /**
     * Almost the same as {@link #sumOf} (violation of the DRY principle!)
     */
    private static int sumOfSQRs(List<Integer> lst) {
        int sum = 0;
        for (var e : lst) {
            sum += e * e;
        }
        return sum;
    }

    /**
     * Almost the same as {@link #sumOf} (violation of the DRY principle!)
     */
    private static int sumOfAbsVals(List<Integer> lst) {
        int sum = 0;
        for (var e : lst) {
            sum += Math.abs(e);
        }
        return sum;
    }

    /**
     * Higher-order function to the rescue (one function to rule them all)
     */
    private static int sumOfWith(List<Integer> lst, UnaryOperator<Integer> uop) {
        int sum = 0;
        for (var e : lst) {
            sum += uop.apply(e);
        }
        return sum;
    }

    /**
     * A more general solution: fold/reduce left
     */
    private static <T> T foldLeft(List<T> lst, T identityElem, BinaryOperator<T> binOp) {
        T acc = identityElem;
        for (var e : lst) {
            acc = binOp.apply(acc, e);
        }
        return acc;
    }

    /**
     * IntBinaryOperator factory (a static function that returns a function)
     */
    private static IntBinaryOperator intBinaryOperatorFactory(BinaryOp binOp) {
        return switch (binOp) {
            case ADD -> (a, b) -> a + b;
            case SUB -> (a, b) -> a - b;
            case MUL -> (a, b) -> a * b;
        };
    }

    /**
     * A central finite difference based approximation of the derivative of function "f" at "x"
     * <p>{@link #df} takes a function (f) and returns the approximation of its first derivative.
     * <p>Note: the result is a <i>closure</i>, i.e. a function + (its) environment (here it is "h")
     */
    private static DoubleUnaryOperator df(DoubleUnaryOperator f, double h) {
        return x -> (f.applyAsDouble(x + h) - f.applyAsDouble(x - h)) / (2 * h);
    }

    /**
     * Living in the <i>DRY-violation-is-OK</i> world :)
     */
    private static void demo1() {
        System.out.println("\ndemo1...");
        List<Integer> l1 = LIST_1TO5;
        int sum1 = sumOf(l1);
        int sum2 = sumOfSQRs(l1);
        int sum3 = sumOfAbsVals(l1);
        System.out.println("sum1: " + sum1 + ", sum2 = " + sum2 + ", sum3 = " + sum3);
    }

    /**
     * Higher-order functions: functions as arguments (1)
     */
    private static void demo2() {
        System.out.println("\ndemo2...");
        List<Integer> l1 = LIST_1TO5;
        int sum1 = sumOfWith(l1, e -> e);
        int sum2 = sumOfWith(l1, e -> e * e);
        int sum3 = sumOfWith(l1, e -> Math.abs(e));
        System.out.println("sum1: " + sum1 + ", sum2 = " + sum2 + ", sum3 = " + sum3);
    }

    /**
     * Higher-order functions: functions as arguments (2)
     */
    private static void demo3() {
        System.out.println("\ndemo3...");
        List<Integer> l1 = LIST_1TO5;
        int sum1 = foldLeft(l1, 0, (acc, e) -> acc + e);
        int sum2 = foldLeft(l1, 0, (acc, e) -> acc + e * e);
        int sum3 = foldLeft(l1, 0, (acc, e) -> acc + Math.abs(e));
        System.out.println("sum1: " + sum1 + ", sum2 = " + sum2 + ", sum3 = " + sum3);

        int prod1 = foldLeft(l1, 1, (acc, e) -> acc * e);
        int prod2 = foldLeft(l1, 1, (acc, e) -> acc * Math.abs(e));
        System.out.println("---\nprod1: " + prod1 + ", prod2 = " + prod2);
    }

    /**
     * Higher-order functions: functions as results
     */
    private static void demo4() {
        System.out.println("\ndemo4...");

        IntBinaryOperator add = intBinaryOperatorFactory(BinaryOp.ADD);
        IntBinaryOperator sub = intBinaryOperatorFactory(BinaryOp.SUB);
        IntBinaryOperator mul = intBinaryOperatorFactory(BinaryOp.MUL);

        int addRes = add.applyAsInt(10, 5);
        int subRes = sub.applyAsInt(10, 5);
        int mulRes = mul.applyAsInt(10, 5);

        System.out.println("addRes: " + addRes + ", subRes: " + subRes + ", mulRes: " + mulRes);
    }

    /**
     * Higher-order functions: functions both as results and arguments
     */
    private static void demo5() {
        System.out.println("\ndemo5...");
        DoubleUnaryOperator f = x -> x * x;
        DoubleUnaryOperator exactFPrim = x -> 2 * x;
        DoubleUnaryOperator approxFPrim = df(f, 1e-8);

        double dfAbsErr;
        for (int x = 0; x < 10; x++) {
            dfAbsErr = Math.abs(exactFPrim.applyAsDouble(x) - approxFPrim.applyAsDouble(x));
            System.out.println("dfAbsErr(" + x + "): " + dfAbsErr);
        }
    }

    /**
     * Functions as elements of a data structure
     */
    private static void demo6() {
        System.out.println("\ndemo6...");
        DoubleUnaryOperator[] fns1 = {
                x -> x * x,
                x -> Math.sin(x),
                Math::sqrt, // or as a method reference
                Math::log10,
        };

        double x0 = 100;
        for (var f : fns1) {
            System.out.println(f.applyAsDouble(x0));
        }
        System.out.println("---");

        List<DoubleUnaryOperator> fns2 = new ArrayList<>();
        fns2.add(x -> 2 * x + Math.log10(x));
        fns2.add(x -> Math.log(x) / (1 + x));
        fns2.add(x -> Math.sin(x) / x);
        fns2.add(Math::cos);
        fns2.forEach(f -> System.out.println(f.applyAsDouble(x0)));
    }

    public static void main(String[] args) {
        List<Integer> lst = (List<Integer>) IntStream.rangeClosed(1, 15);
        UnaryOperator<Integer> p5 = a -> (int)java.lang.Math.pow(a,5);
        sumOfWith(lst, p5);
        demo1();
        demo2();
        demo3();
        demo4();
        demo5();
        demo6();
    }
}

enum BinaryOp {ADD, SUB, MUL}
