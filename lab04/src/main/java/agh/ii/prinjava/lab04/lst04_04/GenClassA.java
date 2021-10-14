package agh.ii.prinjava.lab04.lst04_04;

/**
 * You can require a type parameter to be a subtype of one or more types (<i>constrained parametric polymorphism</i>)
 * <p>{@code GenClassA<T extends IA>} - the T represents all types extending/implementing {@code IA}
 */
class GenClassA<T extends IA> {
    void m1(T p) {
        p.mA(); // OK, p is treated as of type A
    }
}
