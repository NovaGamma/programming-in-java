package agh.ii.prinjava.lab03.lst03_01;

/**
 * An exception is an event, which occurs during the execution of a program,
 * that disrupts the normal flow of the program's instructions.
 *
 * <p>A valid Java code must honor the <i>Catch or Specify Requirement (CSR)</i>.
 * This means that code that might throw certain exceptions must be enclosed by either of the following:
 * <ul>
 *     <li>A try statement that catches the exception
 *         (the try must provide a handler for the exception)</li>
 *     <li>A method that specifies that it can throw the exception
 *         (the method must provide a throws clause that lists the exception)</li>
 * </ul>
 * Note: not all exceptions are subject to the <i>CSR</i>
 *
 * <p>The following three kinds/categories of exceptions are considered:
 * <ul>
 *     <li>Checked exceptions (are subject to the CSR) - exceptional conditions that a well-written application
 *         should anticipate and recover from</li>
 *     <li>Unchecked exceptions (are not subject to the CSR)
 *     <ul>
 *         <li>Errors - exceptional conditions that are external to the application, and that the application
 *             usually cannot anticipate or recover from</li>
 *         <li>Runtime exceptions - exceptional conditions that are internal to the application,
 *             and that the application usually cannot anticipate or recover from;
 *             these usually indicate programming bugs</li>
 *     </ul>
 *     </li>
 * </ul>
 *
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/exceptions/catchOrDeclare.html">The Catch or Specify Requirement</a>
 * @see <a href="https://docs.oracle.com/javase/specs/jls/se17/html/jls-11.html">Exceptions</a>
 */
public class Main {
    /**
     * RuntimeException typically is not specified in the method declaration
     */
    private static void m1() {
        throw new RuntimeException(); // OK, it's a runtime exception ("throws" in the declaration not needed)
    }

    /**
     * A methods CAN but DOESN'T need to handle or specify an unchecked exception.
     * Specification of unchecked exceptions can be done, e.g., for documentation purposes
     * (it appears in the corresponding JavaDoc -> programmers know what to expect)
     *
     * @throws RunTimeEx1 some description
     */
    private static void m2() throws RunTimeEx1 {
        throw new RunTimeEx1();
    }

    /**
     * Runtime exceptions are unchecked, i.e. are not subject to the CSR
     * (throws/try-catch are not "forced" by the compiler)
     */
    private static void demo1() {
        m1();
        m2();
    }

    /**
     * Yet, they can be handled
     */
    private static void demo2() {
        System.out.println("demo2...");
        try {
            m1();
            m2();
        } catch (RuntimeException e) {
            System.out.println("RuntimeException was caught in demo2");
            //e.printStackTrace();
        }
    }

    /**
     * Auxiliary method
     */
    private static int intDiv(int a, int b) {
        return a / b;
    }

    private static void demo3() {
        System.out.println("\ndemo3...");
        try {
            int x = intDiv(3, 0);
            System.out.println("demo3: x = " + x);
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException was caught in demo3");
        }
    }

    /**
     * Error and its subclasses are regarded as unchecked exceptions for the purposes of
     * compile-time checking of exceptions (the CSR does not apply)
     *
     * <p>Note: you should NEVER throw Errors (they are used by JVM, e.g., OutOfMemoryError, StackOverflowError)
     */
    private static void m3() {
        throw new Error1(); // <- NEVER do it :)
    }

    /**
     * [as in the case of runtime exceptions] Methods CAN but DOESN'T need to handle or specify an {@code Error}
     * ({@code Error} and its subclasses are regarded as unchecked exceptions).
     *
     * @throws Error1 some description
     */
    public static void m4() throws Error1 {
        throw new Error1();
    }

    private static void demo4() {
        System.out.println("\ndemo4...");
        try {
            m3();
        } catch (Error1 e) {
            System.out.println("Error1 was caught in demo4");
        }
    }

    private static void demo5() {
        System.out.println("\ndemo5...");
        try {
            m4();
        } catch (Error e) { // Error is the base class for Error1 which is thrown
            System.out.println("Error was caught in demo5");
        }
    }

    public static void main(String[] args) {
        // demo1(); // <- demo1() does not catch the exception thrown by m1()
        demo2();
        demo3();
        demo4();
        demo5();
    }
}

// Examples of user-defined exceptions/errors

/**
 * An {@code Error} is a subclass of {@code Throwable} that indicates serious problems that a reasonable
 * application should not try to catch.
 *
 * <p>The class Error is a separate subclass of {@code Throwable}, distinct from {@code Exception} in the class
 * hierarchy, to allow programs to use the idiom "} catch (Exception e) {" to catch all exceptions from which
 * recovery may be possible without catching errors from which recovery is typically not possible.
 *
 * <p>Note: A method is NOT required to declare in its {@code throws} clause any subclasses of {@code Error}
 * that might be thrown during the execution of the method but not caught, since these errors are abnormal
 * conditions that should never occur. That is, {@code Error} and its subclasses are regarded as unchecked
 * exceptions for the purposes of compile-time checking of exceptions.
 *
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Throwable.html">Throwable</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Error.html">Error</a>
 */
class Error1 extends Error { // A user-defined Error -- you should never do it!!!
    public Error1() {
        super("Error1 occurred");
    }
}

/**
 * {@code RuntimeException} is the superclass of those exceptions that can be thrown
 * during the normal operation of the Java Virtual Machine.
 *
 * <p>{@code RuntimeException} and its subclasses are unchecked exceptions.
 * Unchecked exceptions do not need to be declared in a method or constructor's throws clause
 * if they can be thrown by the execution of the method or constructor and propagate
 * outside the method or constructor boundary.
 *
 * <p>Runtime exceptions can occur anywhere in a program, and in a typical one they can be very numerous.
 * Having to add runtime exceptions in every method declaration would reduce a program's clarity.
 * Thus, the compiler does not require that you catch or specify runtime exceptions (although you can).
 *
 * <p>One case where it is a common practice to throw a RuntimeException is when the user calls a method
 * incorrectly. For example, a method can check if one of its arguments is incorrectly null.
 * If an argument is null, the method might throw a NullPointerException, which is an unchecked exception.
 *
 * <p>Generally speaking, do not throw a RuntimeException or create a subclass of RuntimeException
 * simply because you don't want to be bothered with specifying the exceptions your methods can throw.
 *
 * <p>Here's the bottom line guideline: If a client can reasonably be expected to recover from an exception,
 * make it a checked exception. If a client cannot do anything to recover from the exception,
 * make it an unchecked exception.
 *
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Throwable.html">Throwable</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Exception.html">Exception</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/RuntimeException.html">RuntimeException</a>
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/exceptions/runtime.html">Unchecked Exceptions-the Controversy</a>
 */
class RunTimeEx1 extends RuntimeException {
    public RunTimeEx1() {
        super("RunTimeEx1 occurred");
    }
}
