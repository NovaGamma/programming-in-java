package agh.ii.prinjava.lab01.lst01_06;

public class Main {
    public static void main(String[] args) {
        D1 d11; // The constructor is not called here
        System.out.println("(1)");
        System.out.println("About to execute: d11 = new D1(); // D1 extends B1");
        d11 = new D1(); // it's called here
        System.out.println("(2)");

        System.out.println("About to execute: D4 d41 = new D4(); // D4 extends B3");
        D4 d41 = new D4();
        System.out.println();

        System.out.println("About to execute: D6 d61 = new D6(5); // D6 extends B3");
        D6 d61 = new D6(5);

        System.out.println("About to execute: D7 d71 = new D7(); // D7 extends B3");
        D7 d71 = new D7(10);

        System.out.println("About to execute: D7 d72 = new D7(); // D7 extends B3");
        D7 d72 = new D7();

        System.out.println("About to execute: D8 d82 = new D8(); // D8 extends B3");
        D8 d82 = new D8();

        System.out.println("About to execute: D9 d91 = new D9(); // D9 extends D1, and composes D1, D4 and D7");
        D9 d91 = new D9();
    }
}
