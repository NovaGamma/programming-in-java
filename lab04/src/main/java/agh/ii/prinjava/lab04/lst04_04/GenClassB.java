package agh.ii.prinjava.lab04.lst04_04;

/**
 * <p>{@code GenClassB<T extends IB>} - the T represents all types extending/implementing {@code IB}
 */
class GenClassB<T extends IB> {
    void m1(T p) {
        p.mA(); // OK, p is treated as of type B
        p.mB(); // OK, as above
    }
}
