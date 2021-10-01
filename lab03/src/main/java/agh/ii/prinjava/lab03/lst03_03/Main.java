package agh.ii.prinjava.lab03.lst03_03;

public class Main {
    private static int intDiv(int x, int y) {
        return x / y; // Possible ArithmeticException
    }

    private static void m1(int x) throws CheckedEx1 {
        if (x == 0) {
            throw new CheckedEx1();
        }
    }

    /**
     * try-finally (no run-time exception), hint: use debugger
     *
     * @see <a href="https://docs.oracle.com/javase/specs/jls/se17/html/jls-14.html#jls-14.20.2">Execution of try-finally</a>
     */
    private static void demo1() {
        System.out.println("demo1...");
        try {
            int x = intDiv(10, 3);
            System.out.println("demo1: x = " + x);
        } finally {
            System.out.println("demo1: finally-block entered");
        }
    }

    /**
     * try-finally (run-time exception {@link ArithmeticException}); hint: use debugger
     *
     * @see <a href="https://docs.oracle.com/javase/specs/jls/se17/html/jls-14.html#jls-14.20.2">Execution of try-finally</a>
     */
    private static void demo2() {
        System.out.println("\ndemo2...");
        try {
            int x = intDiv(10, 0);
            System.out.println("demo2: x = " + x);
        } finally {
            System.out.println("demo2: finally-block entered");
        }
    }

    /**
     * try-catch-finally (hint: use debugger)
     *
     * @see <a href="https://docs.oracle.com/javase/specs/jls/se17/html/jls-14.html#jls-14.20.2">Execution of try-catch-finally</a>
     */
    private static void demo3() {
        System.out.println("\ndemo3...");
        try {
            m1(1);
            System.out.println("call of m1(1) was successful");
            m1(0);
            System.out.println("call of m1(0) was successful");
        } catch (CheckedEx1 e) {
            System.out.println("demo3: catch-block entered");
        } finally {
            System.out.println("demo3: finally-block entered");
        }
    }

    /**
     * There are some specific cases when the "finally" block is not executed,
     * e.g., when {@code exit} is called</li>
     */
    private static void demo4() {
        System.out.println("\ndemo4...");
        try {
            System.out.println("demo4: entering the try-finally block...");
            System.exit(1);
        } finally {
            System.out.println("demo4: finally-block entered"); // never executed!!! (check with the debugger)
        }
    }

    /**
     * return + try-finally block (hint: use the debugger to see that the finally-block is executed as the last)
     */
    private static int demo5() {
        System.out.println("\ndemo5...");
        int x = 1;
        try {
            return x; // <- this does not exit the function
            //System.out.println("Unreachable statement"); // <- compilation error, but see the finally-block
        } finally {
            //x = 10; // warning: "the value 2 assigned to 'x' is never used"
            // BUT, this block is executed despite the previous "return"
            System.out.println("demo5: finally-block entered");
        }
    }

    /**
     * VERY BAD style! return in finally-block is not recommended!
     */
    private static int demo6() {
        System.out.println("\ndemo6...");
        try {
            return 1; // (*)
        } finally {
            System.out.println("demo6: finally-block begin");
            // this "overrides" (*) // warning: 'finally' block can not complete normally. 'return inside finally block
            // return 2;
            //System.out.println("demo6: finally-block end"); // <- Error: "Unreachable statement"
        }
    }

    public static void main(String[] args) {
        try {
            demo1();
            //demo2();
            demo3();
            //demo4(); // exit(1) is called -> Execution failed for task...
            demo5();
            System.out.println("demo6() returned " + demo6());
        } catch (Throwable e) {
            System.out.println("Something bad happened, burying the head in the sand..."); // Do not do it
        }
    }
}

class CheckedEx1 extends Exception {
    public CheckedEx1() {
        super("CheckedEx1 occurred");
    }
}
