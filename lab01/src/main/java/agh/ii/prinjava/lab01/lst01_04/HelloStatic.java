package agh.ii.prinjava.lab01.lst01_04;

/**
 * <ul>
 *     <li>A static variable (can be seen in Java as a class variable) is shared by all objects of the class</li>
 *     <li>Static variables are initialized when class is loaded (so <i>only once</i> per class,
 *         before any object of that class is created)</li>
 *     <li>static constants = static final variables; they are quite common
 *         (in contrast to mutable static variables)</li>
 * </ul>
 * <ul>
 *     <li>A common use for static methods is a factory method - a static method that returns new instances of the class
 *         (presented in the next section)</li>
 *     <li>Static methods are methods that do not operate on objects (they do not receive an implicit first argument), i.e.
 *     <ul>
 *         <li>{@code o1.staticMet(arg1, arg2) = C1.staticMet(arg1, arg2)} (possible but considered as bad style!)</li>
 *         <li>{@code o1.instanceMet(arg1, arg2) = C1.staticMet(o1, arg1, arg2)} (conceptually only, does not compile)</li>
 *     </ul>
 *     where C1 is the class in which {@code staticMet} and {@code instanceMet} are defined
 *     </li>
 * </ul>
 */
public class HelloStatic {
    private int instanceVar;
    /**
     * may be initialized this way (or in a constructor, e.g., {@linkplain  #HelloStatic(double)})
     */
    private final double INSTANCE_CONST_1 = 3.141593;
    private final double INSTANCE_CONST_2;

    private static int staticVar;
    public static final double STATIC_CONST = 1.57079; // must be initialised

    /**
     * Implicit {@code this} specified explicitly (<i>extremely untypical</i>, for hacking purposes only!)
     */
    public void instanceMethod1(HelloStatic this) {
        System.out.println("instanceVar = " + this.instanceVar + "instanceVar2 = " + this.INSTANCE_CONST_2);
        System.out.println("staticVar = " + staticVar + "staticConst = " + STATIC_CONST); // access to the static part
    }

    /**
     * Normal instance method
     */
    public void instanceMethod2() {
        System.out.println("instanceVar = " + instanceVar + "instanceVar2 = " + INSTANCE_CONST_2);
    }

    /**
     * No access to the non-static part from the static context
     */
    static void staticMethod(HelloStatic hs) {
        // (1) Non-static field 'instanceVar' cannot be referenced from a static context
        // System.out.println("instanceVar = " + instanceVar);

        // Non-static method 'instanceMethod2()' cannot be referenced from a static context
        // instanceMethod2();

        // But since 'hs' is the method parameter, the following is possible
        System.out.println("hs.instanceVar = " + hs.instanceVar);
        hs.instanceMethod2();
    }

    /**
     * Instance constants can be initialized in the constructor (compare to {@link #INSTANCE_CONST_1})
     */
    public HelloStatic(double instanceConst2) {
        this.INSTANCE_CONST_2 = instanceConst2;

        staticVar++; // OK
        instanceVar = staticVar; // OK
    }
}
