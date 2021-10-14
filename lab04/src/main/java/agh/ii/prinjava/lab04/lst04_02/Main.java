package agh.ii.prinjava.lab04.lst04_02;


public class Main {
    /**
     * Generics enable you to detect errors at compile time rather than at runtime.
     */
    private static void demo1() {
        System.out.println("demo1...");

        GenBox<Integer> bi1 = new GenBox<Integer>(1); // Autoboxing
        int x = bi1.getX(); // No casting needed (it's added by the compiler)
        System.out.println("x from bi1 = " + x);

        // Explicit type argument Integer can be replaced with <> (the "diamond operator")
        GenBox<Integer> bi2 = new GenBox<>(2);

        x = bi2.getX();
        System.out.println("x from bi2 = " + x);

        var bi3 = new GenBox<>(3); // the type is inferred from the argument type (3 -> Integer)
        x = bi3.getX();
        System.out.println("x from bi3 = " + x);

        var bi4 = new GenBox<>();
        // Not enough information to infer any other type than Object
        //x = bi4.getX(); // <- Required type: int. Provided: Object
        // cast is needed, but then NullPointerException:
        // - cannot invoke "java.lang.Integer.intValue()" because the return value of "GenBox.getX()" is null

        GenBox<Integer> bi5 = new GenBox<Integer>();
        bi5.setX(1);
        x = bi5.getX();
        System.out.println("x from bi5 = " + x);

        var bi6 = new GenBox<>();
        bi6.setX(1);
        //x = bi6.getX(); // <- Required type: int. Provided: Object
        x = (int) bi6.getX();
        System.out.println("bi6.x = " + x);
    }

    /**
     * NEVER USE <i>RAW</i> types as before Java 5 (as )!
     * <p>Warning: Main.java uses unchecked or unsafe operations
     *
     * <p>A <i>raw type</i> - a generic class or interface used without specifying a concrete type
     * (this enables backward compatibility with earlier versions of Java, pre-Java5).
     */
    private static void demo2() {
        System.out.println("demo2...");

        // Never use raw types!!!
        GenBox b1 = new GenBox(3); // raw type, compiles, but... (T ~ Object)
        //int x = b1.getX(); // <- Required type: int. Provided: Object
        int x = (int) b1.getX();
        System.out.println("x from b1 = " + x);

        /*
         * String y = (String) b1.getX(); // <- ClassCastException
         * System.out.println("y (as String) from b1 = " + y);
         */
        String y = b1.getX().toString();
        System.out.println("y from getX().toString() = " + y);
    }

    public static void main(String[] args) {
        demo1();
        demo2();
    }
}
