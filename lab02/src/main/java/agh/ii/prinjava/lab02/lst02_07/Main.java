package agh.ii.prinjava.lab02.lst02_07;
/*
 * An interface is a mechanism for specifying a contract between two parties:
 *   the supplier of a service and the classes that want their objects to be usable with the service.
 */

/**
 * <ul>
 *     <li>Modifier 'abstract' is redundant for interfaces</li>
 *     <li>Modifier 'abstract' is redundant for interface methods</li>
 *     <li>Modifier 'public' is redundant for interface methods</li>
 * </ul>
 */
abstract interface I1 {
    public abstract void m1();
}

interface I2 { // it is still abstract
    void m21(); // it is still public abstract

    void m22(); // as above
}

/**
 * An interface specifies a set of methods that an implementing class must provide.</li>
 */
class C1 implements I2 {
    @Override
    public void m21() {
    }

    @Override
    public void m22() {
    }
}

/**
 * A class can implement more than one interface
 */
class C2 implements I1, I2 {
    @Override
    public void m1() {
    }

    @Override
    public void m21() {
    }

    @Override
    public void m22() {
    }
}

/**
 * <ul>
 *     <li>A variable declared in an interface is public, static, final; since it's constant, it must be initialised</li>
 *     <li>{@code public}, {@code static}, and {@code final} are redundant for variables declared in an interface</li>
 *     <li>static blocks are not allowed in interfaces</li>
 * </ul>
 */
interface I3 {
    // static { int x4 = 5; } // static blocks are not allowed in interfaces<
    // int x1; // it's final, so it must be initialised

    public static final int x2 = 3; // "public static final" is redundant
    int x3 = 5; // it is still "public static final"

    void m1(); // it is still public abstract
}

/**
 * Starting from Java 8 interfaces have new capabilities:
 * <ul>
 *     <li>default implementations of methods were added for technical reasons (streams and collection library)</li>
 *     <li>static methods in some cases are useful (e.g., implementing factory methods)</li>
 * </ul>
 */
interface I4 {
    public default void m1() { // <- Modifier 'public' is redundant for interface methods
        System.out.println("I4.m1()");
    } // public is redundant

    /**
     * it is public, but NOT abstract (error: Illegal combination of modifiers: 'abstract' and 'default')
     */
    default void m2() {
        System.out.println("I4.m2()");
    }
}

interface I5 {
    /**
     * static methods are also available (from Java8)
     */
    static void sm1() {
        System.out.println("I5.sm1()");
        psm2(); // OK
    }

    /**
     * and also private static methods
     */
    private static void psm2() {
        System.out.println("I5.psm2()");
        psm3(); // OK
    }

    private static void psm3() {
        System.out.println("I5.psm3()");
    }

    void m3();
}

/**
 * An interface can extend (inherit from) more than one interface
 */
interface I6 extends I5, I4 {
} // an "aggregating" interface

//interface I7 implements I5, I4 {} //  No implements clause allowed for interface

class C3 implements I4, I5 {
    @Override
    public void m1() {
        System.out.println("C3.m1()");
        m2(); // OK, but
        // sm1(); // <- Error: Static method may be invoked on containing interface class only
        I5.sm1();
        //I5.psm2(); // <- Error: psm2() has private access in I5
    }

    @Override
    public void m3() {
        System.out.println("C3.m3()");
    }
}

class C4 implements I6 {
    @Override
    public void m3() {
        System.out.println("C4.m3()");
    }
}

public class Main {
    private static void demo1() {
        System.out.println("demo1...");
        I5 i3 = new C3();
        i3.m3();
    }

    private static void demo2() {
        System.out.println("demo2...");
        I4 i4 = new C3(); // C3 implements I4, I5
        i4.m1();
        i4.m2();

        I5 i5 = (I5) i4; // <- (!), I4 i4 = new C3(); C3 implements I4, I5
        i5.m3();
        //i5.sm1(); // Error: static method may be invoked on containing interface class only
        I5.sm1();

        I6 i6 = new C4();
        i6.m1();
        i6.m3();
        i6.m2();
    }

    public static void main(String[] args) {
        demo1();
        System.out.println();

        demo2();
    }
}
