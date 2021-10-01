package agh.ii.prinjava.lab03.lst03_04;

/**
 * Any object that implements {@link java.lang.AutoCloseable}, which includes all objects which implement
 * {@link java.io.Closeable}, can be used as a <i>resource</i> in try-with-resources statement:
 *
 * <p><pre>
 * try (declare and create resources) {
 *   // Use the resource to process the file;
 * }
 * </pre>
 *
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/AutoCloseable.html">AutoCloseable</a>
 * @see <a href="https://docs.oracle.com/javase/specs/jls/se17/html/jls-14.html#jls-14.20.3">try-with-resources</a>
 * @see <a href="https://www.yegor256.com/2017/08/08/raii-in-java.html">RAII in Java</a>
 */
class Resource1 implements AutoCloseable {

    /**
     * Note: although the close method from {@link AutoCloseable} interface throws {@link Exception},
     * we can throw NO exception at all as the methods are covariant with respect to the exceptions
     */
    @Override
    public void close() {
        System.out.println("Resource1.close()");
    }

    public void doStuff() throws ChEx1 {
        System.out.println("Resource1.doStuff()");
        throw new ChEx1();
    }

    Resource1() {
        System.out.println("Resource1: the resource is being opened...");
    }
}

public class Main {
    /**
     * Before try-with-resources was introduced
     */
    private static void demo1() {
        System.out.println("demo1...");
        Resource1 r1 = new Resource1();
        // Warning: 'try' can use automatic resource management
        try {
            r1.doStuff();
        } catch (ChEx1 e) {
            System.out.println("demo1: catch (ChEx1 e)");
        } finally {
            System.out.println("demo1: finally-block");
            r1.close();
        }
    }

    /**
     * As {@link #demo1} but with the use of "try-wit-resources" statement
     *
     * <p>A <i>try-with-resources</i> statement is parameterized with variables (known as resources) that are initialized
     * before execution of the try block and closed automatically, in the reverse order from which they were initialized,
     * after execution of the try block. catch clauses and a finally clause are often unnecessary when resources
     * are closed automatically.
     *
     * @see <a href="https://docs.oracle.com/javase/specs/jls/se17/html/jls-14.html#jls-14.20.3">try-with-resources</a>
     */
    private static void demo2() {
        System.out.println("\ndemo2...");
        try (Resource1 r1 = new Resource1()) {
            r1.doStuff();
        } catch (ChEx1 e) {
            System.out.println("demo2: catch (ChEx1 e)");
        }
    }

    /**
     * The exception handing can become quite "messy", because of nested try-catch-finally blocks
     */
    private static void demo3() {
        System.out.println("\ndemo3...");
        Resource2 r2 = null; // (1)
        try {
            r2 = new Resource2();
            r2.doStuff();
        } catch (ChEx2 e) {
            System.out.println("demo3: catch (ChEx2 e)");
        } finally {
            System.out.println("demo3: finally-block");
            if (r2 != null) { // r2 can be null, see (1)
                try {
                    r2.close();
                } catch (ChEx2 e) {
                    System.out.println("demo3: catch (ChEx2 e)");
                }
            } else {
                System.out.println("demo3: as r2 is null, close() was no called");
            }
        }
    }

    /**
     * try-with-resources to the rescue (compare to {@link #demo3})
     */
    private static void demo4() {
        System.out.println("\ndemo4...");
        try (Resource2 r2 = new Resource2()) {
            r2.doStuff();
        } catch (ChEx2 e) {
            System.out.println("demo4: catch (ChEx2 e)");
        }
    }

    /**
     * try-with-resources with more than one resource
     * <p>
     * Note the reversed order of calling "close":  try(r1, r2) -> (r2.close, r1.close)
     * </p>
     */
    private static void demo5() {
        System.out.println("\ndemo5...");
        try (Resource1 r1 = new Resource1();
             Resource2 r2 = new Resource2()) {
            r1.doStuff();
            r2.doStuff();
        } catch (ChEx1 | ChEx2 e) {
            System.out.println("demo5: catch (ChEx1 | ChEx2 e)");
        }
    }

    /**
     * The code in the finally-block is executed as the last even if combined with try-with-resources
     * <p>(r1, r2) -> (r2.close, r1.close) -> catch-block -> finally-block</p>
     */
    private static void demo6() {
        System.out.println("\ndemo6...");
        try (Resource1 r1 = new Resource1();
             Resource2 r2 = new Resource2()) {
            r1.doStuff();
            r2.doStuff();
        } catch (ChEx1 | ChEx2 e) {
            System.out.println("demo6: catch (ChEx1 | ChEx2 e)");
        } finally {
            System.out.println("demo6: the finally-block");
        }
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

class Resource2 implements AutoCloseable {

    @Override
    public void close() throws ChEx2 {
        System.out.println("Resource2.close()");
        throw new ChEx2();
    }

    public void doStuff() throws ChEx2 {
        System.out.println("Resource2.doStuff()");
        throw new ChEx2();
    }

    public Resource2() {
        System.out.println("Resource2: the resource is being opened...");
    }
}

class ChEx1 extends Exception {
}

class ChEx2 extends Exception {
}
