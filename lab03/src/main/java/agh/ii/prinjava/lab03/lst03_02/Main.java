package agh.ii.prinjava.lab03.lst03_02;

/**
 * A {@code throw} statement causes an exception to be thrown. The result is an immediate transfer of control
 * that may exit multiple statements and multiple constructor, instance initializer, static initializer
 * and field initializer evaluations, and method invocations until a {@code try} statement is found that catches
 * the thrown value.
 * If no such try statement is found, then execution of the thread that executed the {@code throw} is terminated
 * after invocation of the {@code uncaughtException} method for the thread group to which the thread belongs.
 *
 * @see <a href="https://docs.oracle.com/javase/specs/jls/se17/html/jls-14.html#jls-14.18">The throw statement</a>
 * @see <a href="https://docs.oracle.com/javase/specs/jls/se17/html/jls-8.html#jls-8.4.6">A throws clause</a>
 */
public class Main {
    /**
     * Methods or constructors that fail to handle exceptional conditions thrown as checked exceptions
     * in their bodies will normally cause compile-time errors if they lack proper exception types
     * in their throws clauses.
     * The Java programming language thus encourages a programming style where rare and otherwise
     * truly exceptional conditions are documented in this way
     */
    private static void m1() {
        //throw new CheckedEx1(); // <- compilation error: Unhandled exception
    }

    /**
     * Now, since the {@link CheckedEx1} and {@link CheckedEx2} are stated in the throw clause,
     * the method compiles
     *
     * @throws CheckedEx1 some description of the context
     * @throws CheckedEx2 some description of the context
     * @see <a href="https://docs.oracle.com/javase/tutorial/essential/exceptions/catchOrDeclare.html">The Catch or Specify Requirement</a>
     */
    private static void m2(int x) throws CheckedEx1, CheckedEx2 {
        if (x > 0) {
            throw new CheckedEx1();
        } else {
            throw new CheckedEx2();
        }
    }

    private static void demo1() {
        System.out.println("demo1...");
        //m2(1); // Compilation error: "Unhandled exceptions"
    }

    /**
     * Now, it's OK since the exceptions are specified int the throw close
     *
     * <p>Note: if an exception cannot be handled (to fix the computation, it should be re-thrown
     */
    private static void demo2() throws CheckedEx2, CheckedEx1 {
        System.out.println("\ndemo2...");
        m2(1);
    }

    /**
     * This function is able to fix the errors, so it handles them
     *
     * <p>A {@code try} statement executes a block. If an exception is thrown and the try statement
     * has one or more catch clauses that can catch it, then control will be transferred
     * to the first such catch clause.
     *
     * @see <a href="https://docs.oracle.com/javase/specs/jls/se17/html/jls-14.html#jls-14.20">the try statement</a>
     * @see <a href="https://docs.oracle.com/javase/specs/jls/se17/html/jls-14.html#jls-14.20.1">execution of try-catch</a>
     */
    private static void demo3() {
        System.out.println("\ndemo3...");
        try {
            m2(1);
        } catch (CheckedEx1 e) {
            System.out.println("CheckedEx1 was caught in demo3");
            // DO something to fix the computation!!!
        } catch (CheckedEx2 e) {
            System.out.println("CheckedEx2 was caught in demo3");
            // DO something to fix the computation!!!
        }
    }

    /**
     * This function is able to fix only {@link CheckedEx1}, so it handles it (and ONLY it!)
     */
    private static void m3() throws CheckedEx2 {
        try {
            m2(1);
        } catch (CheckedEx1 e) {
            System.out.println("CheckedEx1 was caught in m3");
            // DO something to fix the computation!!!
        }
    }

    /**
     * This method has to handle {@link CheckedEx2}, since it can be thrown by {@link #m3()}
     */
    private static void demo4() {
        System.out.println("\ndemo4...");
        try {
            m3();
        } catch (CheckedEx2 e) {
            System.out.println("CheckedEx2 was caught in demo4");
            // DO something to fix the computation!!!
        }
    }

    /**
     * This method can only partially fix the problem, so it re-throw {@link CheckedEx2}
     */
    private static void demo5() throws CheckedEx2 {
        System.out.println("\ndemo5...");
        try {
            m3();
        } catch (CheckedEx2 e) {
            System.out.println("CheckedEx2 was caught in demo5: doing whatever is possible in this context...");
            System.out.println("Re-throwing the exception");
            throw e;
        }
    }

    /**
     * Changing the type of the exception
     *
     * @see <a href="https://docs.oracle.com/javase/tutorial/essential/exceptions/runtime.html">Unchecked Exceptions-the controversy</a>
     */
    private static void demo6() {
        System.out.println("\ndemo6...");
        try {
            m3();
        } catch (CheckedEx2 e) {
            System.out.println("CheckedEx2 was caught in demo6: doing whatever is possible in this context...");
            System.out.println("Re-throwing as RuntimeException");
            throw new RuntimeException(); // not thrown, since m3() -> m2(1) -> no exception
        }
    }

    /**
     * Catching Multiple Exception ("multi-catch")
     * <p>Note: there cannot be an "is-a" relationship between types in multi-catch
     *
     * @see <a href="https://docs.oracle.com/javase/8/docs/technotes/guides/language/catch-multiple.html#multiple">Handling More Than One Type of Exception</a>
     */
    private static void demo7() {
        System.out.println("\ndemo7...");
        try {
            m2(1);
        } catch (CheckedEx1 | CheckedEx2 e) { // NOT "catch (CheckedEx1 e1 | CheckedEx2 e2)" - a typical error
            System.out.println("CheckedEx1 or CheckedEx2 was caught in demo7");
            // DO something to fix the computation!!!
        }
    }

    public static void main(String[] args) {
        demo1();
        //demo2(); // <- checked exceptions have to be handled
        demo3();
        demo4();
        //demo5(); // <- checked exceptions have to be handled
        demo6(); // mo exception detected: since m3() -> m2(1) -> no exception
        demo7();
    }
}

/**
 * You can define a custom exception class by extending the java.lang.Exception class.
 *
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Exception.html">Exception</a>
 */
class CheckedEx1 extends Exception {
    public CheckedEx1() {
        super("CheckedEx1 occurred");
    }
}

class CheckedEx2 extends Exception {
    public CheckedEx2() {
        super("CheckedEx2 occurred");
    }
}
