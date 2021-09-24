package agh.ii.prinjava.lab02.lst02_10;

@FunctionalInterface
interface I2 {
    void m1(); // exactly one abstract method => this is an abstract interface

    default void m2() {
        System.out.println("I2.m2()");
    } // this is not abstract
}
