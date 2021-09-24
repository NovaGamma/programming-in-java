package agh.ii.prinjava.lab01.lst01_07;

/**
 * A factory method - a static method that returns new instances of the class
 *
 * <b>Note: this is just a simple example of a factory method.
 * More general solution (factory method pattern) in the next section (about interfaces)
 * <p> Factory methods, among other things, can:
 * <ul>
 *     <li>return an object of a subclass</li>
 *     <li>return a shared object, instead of unnecessarily constructing new ones</li>
 * </ul>
 * </p>
 */
public class HelloFactoryMeth {
    private int x;

    /**
     * all constructors are private
     */
    private HelloFactoryMeth() {
        this(1);
    }

    private HelloFactoryMeth(int x) {
        this.x = x;
        System.out.println("HelloFactoryMethod(int) private constructor working...");
    }

    static HelloFactoryMeth create(int x) {
        System.out.println("HelloFactoryMethod.create(int) method working...");
        return new HelloFactoryMeth(x);
    }

    /**
     * Another (overloaded name) factory method
     */
    static HelloFactoryMeth create(String s) {
        System.out.println("HelloFactoryMethod.create(String) method working...");
        return new HelloFactoryMeth(s.length());
    }

    /**
     * Yet another factory method, this time with a different name
     */
    static HelloFactoryMeth buildFrom(HelloFactoryMeth s) {
        System.out.println("HelloFactoryMethod.buildFrom(HelloFactoryMeth) method working...");
        return new HelloFactoryMeth(s.x);
    }
}
