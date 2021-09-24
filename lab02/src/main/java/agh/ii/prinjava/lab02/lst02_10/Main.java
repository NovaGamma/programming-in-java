package agh.ii.prinjava.lab02.lst02_10;

/**
 * A functional interface (aka. <i>Single Abstract Method</i> interface) is an interface
 * that has exactly ONE abstract method (aside from the methods of Object),
 * and thus represents a single function contract.
 */
@FunctionalInterface
interface I1 {
    void m1(); // exactly one abstract method => this is an abstract interface
}

@FunctionalInterface
interface I2 {
    void m1(); // exactly one abstract method => this is an abstract interface

    default void m2() {
        System.out.println("I2.m2()");
    } // this is not abstract
}

/**
 * A functional interface can be implemented by named (non-anonymous) classes
 */
class C2 implements I2 {
    @Override
    public void m1() {
        System.out.println("C2.m1()");
    }
}

// Not a functional interface (no abstract method)}
//@FunctionalInterface interface I01 {}

// Not a functional interface (more than one abstract method)}
//@FunctionalInterface
//interface I02 {
//    void m1();
//    void m2();
//}

@FunctionalInterface
interface Instrument {
    void play();
}

public class Main {

    private static void demo1() {
        System.out.println("demo1...");
        I2 i21 = new C2();
        i21.m1();
        i21.m2();
    }

    private static void demo2() {
        System.out.println("demo2...");
        I2 i21 = new I2() {
            @Override
            public void m1() {
                System.out.println("Anonymous.m1()");
            }
        };

        i21.m1();
        i21.m2();
    }

    private static void demo3() {
        System.out.println("demo3...");

        Instrument[] instruments = {
                new Instrument() {
                    @Override
                    public void play() {
                        System.out.println("AnonymousInstance1.play()");
                    }
                },
                new Instrument() {
                    @Override
                    public void play() {
                        System.out.println("AnonymousInstance2.play()");
                    }
                },
                new Instrument() {
                    @Override
                    public void play() {
                        System.out.println("AnonymousInstance3.play()");
                    }
                },
        };

        for (var i : instruments) {
            i.play();
        }
    }

    public static void main(String[] args) {
        demo1();
        System.out.println();
        demo2();
        System.out.println();
        demo3();
    }
}
