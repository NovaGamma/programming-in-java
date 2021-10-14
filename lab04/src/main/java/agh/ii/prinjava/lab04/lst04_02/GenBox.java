package agh.ii.prinjava.lab04.lst04_02;

/**
 * <p>A <i>generic type</i> is a generic class or interface that is parameterized over types.
 * A generic type can be considered as a <i>type constructor</i> (vs. data/instance constructor).
 *
 * <p>A <i>generic class</i> is a class that declares one or more type variables
 * <p>A <i>generic method</i> is a method that declares one or more type variables
 *
 * <p><i>Generic programming</i> - programming in terms of a generic type instead of a specific type.
 *
 * <p><i>Parametric polymorphism</i> - parts of the program (methods and/or classes/interfaces)
 * can be parametrised by types.
 *
 * <p>A type is <i>(parametrically) polymorphic</i> if its declaration contains
 * at least one <i>type parameter/variable</i>.
 *
 * <p><i>Type parameter</i> (e.g., the "T" in {@code GenClass<T>}) vs.
 * <i>type argument</i> (e.g., {@code Double} in {@code GenClass<Double> r;})
 *
 * <p>A generic type is compiled into a <i>raw type</i> (type erasure) - at the bytecode level,
 * code that doesn’t use generics looks just like code that does.
 *
 * <p>Semantically, Java generics are defined by erasure, whereas C++ templates are defined by expansion.
 * In C++ templates, each instance of a template at a new type is compiled separately.
 *
 * <p><i>Cast-iron guarantee</i>: the implicit casts added by the compilation of generics never fail
 *
 * <p>-----</p>
 * <p> Note: type variable "T" is ONLY for instance-related members (i.e. NOT for static members)
 * <p> Reason: type erasure (a static member is only one per class -> there is only one GenBox)<br>
 * <pre>{@code
 *  GenBox<Double> bd1 = new GenBox<Double>(1);
 *  GenBox<Double> bd2 = new GenBox<>(1); // <- inference: T ~ Double
 *  var bd2 = new GenBox<Double>(1);
 *  }</pre>
 * </p>
 *
 * @param <T> the type of the element stored in the box
 * @see <a href="https://docs.oracle.com/javase/tutorial/extra/generics/index.html">Java Generics</a>
 */
class GenBox<T> {
    private T x;

    public GenBox() {
    }

    public GenBox(T x) {
        this.x = x;
    }

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "GenBox{" + "x=" + x + '}';
    }
}
