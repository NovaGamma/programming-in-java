package agh.ii.prinjava.lab04.lst04_04;

/**
 * A multiple-bound type parameter, T (being a subtype of two types, here interfaces)
 */
class GenClassA10<T extends IA & I10> {
    void m1(T p) {
        p.mA(); // OK, p is treated as of type IA
        p.m10(); // OK, p is treated as of type I10
    }
}
