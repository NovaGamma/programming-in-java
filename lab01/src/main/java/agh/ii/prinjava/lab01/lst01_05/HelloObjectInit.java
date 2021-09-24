package agh.ii.prinjava.lab01.lst01_05;

/**
 * Class instance (HelloObjectInit) process initialization demo
 */
public class HelloObjectInit {

    /**
     * Overloaded constructor (the main one - it is supposed to be called be remaining constructors, here
     * {@link #HelloObjectInit()})
     */
    public HelloObjectInit(double pfd1) {
        this.pfd1 = pfd1;
        System.out.println("HelloObjectInit(double)");
    }

    /**
     * Overloaded constructor (parameterless)
     */
    public HelloObjectInit() {
        this(1.0);
        System.out.println("HelloObjectInit()");
    }

    // Anonymous block (1)
    {
        // illegal forward reference (CONST1)
        // System.out.println("Anonymous block (1), CONST1 = " + CONST1);

        // No problem with the static constant though (since at this step it has already been initialised)
        System.out.println("Anonymous block (1), S_CONST1 = " + S_CONST1);
        if (S_CONST1 > 0) {
            m1(); // OK, this is an instance method
        }
    }

    // Anonymous static block (1)
    static {
        //illegal forward reference (S_CONST1)
        //System.out.println("Anonymous static block (1), S_CONST1 = " + S_CONST1);
        System.out.println("Anonymous static block (1)");

        sm1(); // OK, this is a static method
        // m1(); // Non-static method 'm1()' cannot be referenced from a static context
    }

    public static void sm1() {
    }

    public void m1() {
    }

    public static final double S_CONST1 = 2.71;
    public final double CONST1 = 3.14;

    // Anonymous block (2)
    {
        System.out.println("Anonymous block (2), S_CONST1 = " + S_CONST1 + ", CONST1 = " + CONST1);
    }

    private double pfd1;

    public static final double S_CONST2; // <-- despite being final it may be also initialised in the static block

    // Anonymous static block (2)
    static {
        S_CONST2 = 1.2;
        System.out.println("Anonymous static block (2), S_CONST1 = " + S_CONST1 + ", S_CONST2 = " + S_CONST2);
    }
}
