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

/**
 * An interface specifies a set of methods that an implementing class must provide.</li>
 */


/**
 * A class can implement more than one interface
 */


/**
 * <ul>
 *     <li>A variable declared in an interface is public, static, final; since it's constant, it must be initialised</li>
 *     <li>{@code public}, {@code static}, and {@code final} are redundant for variables declared in an interface</li>
 *     <li>static blocks are not allowed in interfaces</li>
 * </ul>
 */


/**
 * Starting from Java 8 interfaces have new capabilities:
 * <ul>
 *     <li>default implementations of methods were added for technical reasons (streams and collection library)</li>
 *     <li>static methods in some cases are useful (e.g., implementing factory methods)</li>
 * </ul>
 */




/**
 * An interface can extend (inherit from) more than one interface
 */


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
