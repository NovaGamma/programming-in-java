package agh.ii.prinjava.lab06.lst01_01;

import java.lang.Math;

public class Main {
    private static void demo1() {
        mathFunction f01 = (x) -> x - 2;
        mathFunction2 f02 = (x,y) -> (int) Math.sqrt(x*x+y*y);
        mathFunction3 f03 = (x,y,z) -> (int) Math.sqrt(x*x + y*y +z*z);

        System.out.println("demo1...");
        Superman.getInstance().flyTo(48.7887337, 2.3637327);
    }

    /**
     * Functional interfaces provide target types for (can be created with) lambda expressions
     */
    private static void demo2() {
        System.out.println("\ndemo2...");
        Flyable f0 = new Flyable() {
            @Override
            public void flyTo(double latitude, double longitude) {
                System.out.println("Flying (with anonymous class) to (" + latitude + ", " + longitude + ")");
            }
        };
        f0.flyTo(10, 20);

        Flyable f1 = (lat, lon) -> System.out.println("Flying (with lambda) to (" + lat + ", " + lon + ")");
        f1.flyTo(10, 20);
    }

    /**
     * Functional interfaces provide target types for (can be created with) method references.
     *
     * <p>A method reference directs the compiler to produce an instance of a functional interface,
     * overriding the single abstract method of the interface to call the given method.
     *
     * <p>Like a lambda expression, a method reference is not an object. It gives rise to an object when assigned
     * to a variable whose type is a functional interface.
     *
     * <p> {@code ::} operator separates the method name from the name of an object or class.
     * There are three variants:
     * <ol>
     *     <li>{@code object::instanceMethod} - the method reference is equivalent to a lambda expression whose parameters
     *     are passed to the method. For instance, in {@code System.out::println}, the object is {@code System.out},
     *     and the method expression is equivalent to {@code x -> System.out.println(x)}</li>
     *
     *     <li>{@code Class::instanceMethod} - the first parameter becomes the implicit parameter of the method.
     *     For instance, {@code String::compareToIgnoreCase} is the same as {@code(x, y) -> x.compareToIgnoreCase(y)}</li>
     *
     *     <li>{@code Class::staticMethod} - all parameters are passed to the static method:
     *     {@code Math::pow} is equivalent to {@code (x, y) -> Math.pow(x, y)}</li>
     * </ol>
     * Method reference examples:
     * <ul>
     *     <li>{@code separator::equals} corresponds to: {@code x -> separator.equals(x)}</li>
     *     <li>{@code String::trim} corresponds to: {@code x -> x.trim()}</li>
     *     <li>{@code String::concat} corresponds to: {@code (x, y) -> x.concat(y)}</li>
     *     <li>{@code Integer.valueOf} corresponds to: {@code x -> Integer.valueOf(x)}</li>
     *     <li>{@code Integer.sum} corresponds to: {@code (x, y) -> Integer.sum(x, y)}</li>
     *     <li>{@code Integer::new} corresponds to: {@code x -> new Integer(x)}</li>
     *     <li>{@code Integer[]::new} corresponds to: {@code n -> new Integer[n]}</li>
     * </ul>
     */
    private static void demo3() {
        System.out.println("\ndemo3...");

        C1 c1 = new C1(1, 2);
        c1.m2(5);

        I1 i1 = c1::m2;
        i1.f1(5); // equivalent to c1.m2(5);

        Flyable f1 = c1::m1; // an instance method reference (syntax: objectName::method)
        //Flyable f0 = c1::m2; // m2 does not match to Flyable.flyTo

        c1.m1(45, 7);
        f1.flyTo(45, 7);

        Flyable f2 = C1::sm1; // a static method reference (syntax: className::method)
        f2.flyTo(0, 0);
    }

    /**
     * Functional interfaces provide target types for (can be created with) constructor references
     * <p><i>Note</i>: constructor references are just like method references,
     * except that the name of the method is "{@code new}"
     */
    private static void demo4() {
        System.out.println("\ndemo4...");

        IFactory<C1> c1Factory = C1::new; // a constructor reference (syntax: className::new)
        C1 c11 = new C1(10, 20);
        C1 c12 = c1Factory.create(10, 20);

        IFactory<C2> c2Factory = C2::new;
        C2 c2 = c2Factory.create(1, 2);
    }

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
        demo4();
    }
}

