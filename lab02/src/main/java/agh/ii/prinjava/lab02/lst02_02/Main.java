package agh.ii.prinjava.lab02.lst02_02;

/**
 * A final method cannot be overridden by any subclass
 * <p>
 * Note: methods called from constructors should generally be declared final.
 * If a constructor calls a non-final method, a subclass may redefine
 * that method with surprising or undesirable results.
 */


public class Main {
    public static void main(String[] args) {
        DerivedClass dc = new DerivedClass();

        System.out.println("\nAbout to call fM1, m2, m3, and fm4...");
        dc.fM1();
        dc.m2();
        dc.m3();
        dc.fM4();
    }
}
