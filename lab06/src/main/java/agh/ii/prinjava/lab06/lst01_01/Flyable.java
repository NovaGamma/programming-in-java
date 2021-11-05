package agh.ii.prinjava.lab06.lst01_01;

/**
 * A functional interface has exactly one abstract method. Since default methods have an implementation,
 * they are not abstract. If an interface declares an abstract method overriding one of the public methods
 * of {@link Object}, that also does not count since any implementation of the interface will have
 * an implementation from {@code Object} or elsewhere.
 * <p>Functional interfaces provide target types for / can be created with:
 * <ul>
 *     <li>lambda expressions</li>
 *     <li>method references</li>
 *     <li>constructor references</li>
 * </ul>
 * <i>Note</i>: the only thing that you can do with a lambda expression in Java is conversion to a functional interface
 * (the lambda expression becomes the implementation of that single abstract method in the functional interface).
 * <p>It is best to think of a lambda expression as a function, not an object, and to accept that it can be passed
 * to a functional interface.
 *
 * <p>{@link FunctionalInterface} is not a requirement for the compiler to recognize an interface as a functional
 * interface, but merely an aid to capture design intent and enlist the help of the compiler in identifying accidental
 * violations of design intent.
 *
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/FunctionalInterface.html">FunctionalInterface</a>
 */
@FunctionalInterface
interface Flyable {
    void flyTo(double latitude, double longitude);
}
