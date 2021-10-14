package agh.ii.prinjava.lab04.lst04_04;

/**
 * <p>{@code GenClassC<T extends IC>} - the T represents all types extending/implementing {@code IC}
 */
class GenClassC<T extends IC> {
    void m1(T p) {
        p.mA(); // OK, p is treated as of type C
        p.mB(); // OK, as above
        p.mC(); // OK, as above
    }
}
