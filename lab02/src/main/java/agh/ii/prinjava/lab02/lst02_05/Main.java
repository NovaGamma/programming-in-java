package agh.ii.prinjava.lab02.lst02_05;

/**
 * A <i>nested class</i> (<a href="https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html">see</a>)
 * is any class whose declaration occurs within the body of another class or interface declaration.
 * A nested class may be:
 * <ol>
 *     <li>a static member class</li>
 *     <li>an inner class, which may be:</li>
 *     <ul>
 *         <li>a non-static member class (often referred to as <i>inner class</i>)</li>
 *         <li>a local class</li>
 *         <li>an anonymous class</li>
 *     </ul>
 * </ol>
 * <p> A <i>local class</i> - a nested class whose declaration is contained by a block;
 * typically, in the body of a method
 * (<a href="https://docs.oracle.com/javase/tutorial/java/javaOO/localclasses.html">see</a>)
 * <p> An <i>anonymous class</i> - an inner class with no name
 * (<a href="https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html">see</a>)
 *
 * <p>Note: nested/inner classes introduce strong coupling
 */

class OuterClass { // <- Top level class

    private int x; // this is private (!)
    private int y; // as above
    private int z; // as above

    private static int s; // private, static

    /**
     * InnerClass (non-static member class) can be seen as a "member" of OuterClass,
     * hence it has access to the OuterClass private part
     */
    class InnerClass {
        int x;
        String z;

        void m1IC() {
            System.out.println("InnerClass.m1IC...");

            OuterClass.this.x = 3;
            InnerClass.this.x = 1; // or just this.x = 1;

            y = 10; // y from the OuterClass, i.e. it corresponds to OuterClass.this.y = 10;
            x = 1; // x from the InnerClass, i.e. it corresponds to this.x = 1; or InnerClass.this.x = 1;

            z = "abc"; // or his.z = "abc"; or InnerClass.this.z = "abc";
            OuterClass.this.z = 4;
        }
    }

    /**
     * static member class
     */
    static class StaticNestedClass {
        /**
         * OuterClass.this is not accessible here!
         */
        void m1SNC() {
            System.out.println("StaticNestedClass.m1SNC...");
            // OuterClass.this.x = 1; // Error: OuterClass.this cannot be referenced from a static context
            OuterClass.s = 5; // OK, since "s" is static
        }
    }

    /**
     * Note: a variable is considered an <i>effective final</i> if it's not modified
     * after initialization in the local block
     */
    void localClassDemo() {
        final int lv1 = 10; // (explicitly) final local variable
        int lv2 = 20; // effectively final (since Java 8)

        int lv3 = 20; // <- (!)
        lv3 = 30; // <- (!) Not effectively final local variable

        //new LocalClass().m1LC1(); // Error, see (*)
        class LocalClass {
            void m1LC1() {
                System.out.println("lv1 = " + lv1 + ", lv2 = " + lv2);

                //Variable 'lv3' is accessed from within inner class, needs to be final or effectively final
                //System.out.println("lv3 = " + lv3);
            }
        }
        new LocalClass().m1LC1(); // (*) OK, LocalClass is now known
    }

    // A local class is contained by a block, NOT necessarily in the body of a method
    {
        System.out.println("Entering the inner (initialisation) block...");

        // A local class in an inner (initialisation) block
        class LocalClassInABlock {
            void sayHello() {
                System.out.println("Hello from LocalClassInABlock!");
            }
        }
        new LocalClassInABlock().sayHello();

        System.out.println("Leaving the inner (initialisation) block...");
    }

    // { LocalClassInABlock lcinb; /* <- Cannot resolve symbol 'LocalClassInABlock' */ }
}

/**
 * Enums are a good example of the use of nested classes
 */
class Price {
    private long value;
    private Currency currency;

    enum Currency {
        EUR, GBP, USD
    }
}

class BaseClass {
    void m1() {
        System.out.println("BaseClass.m1()");
    }
}

public class Main {

    /**
     * Working with static member classes
     */
    private static void demo1() {
        System.out.println("demo1...");
        OuterClass.StaticNestedClass snc1 = new OuterClass.StaticNestedClass();
        snc1.m1SNC();
    }

    /**
     * Working with inner (non-static member) classes
     *
     * <p>Since InnerClass belongs to the "instance-part" of OuterClass its instance have to be created
     * in a different way:
     * <ol>
     *     <li>create an instance of OuterClass</li>
     *     <li>use it to create an instance of InnerClass</li>
     * </ol>
     * That is why, the following code causes a compilation error:
     * {@code OuterClass.InnerClass ic1 = new OuterClass.InnerClass(); // Error: OuterClass is not an enclosing class}
     */
    private static void demo2() {
        System.out.println("demo2...");
        OuterClass oc1 = new OuterClass(); // step (1)
        OuterClass.InnerClass ic1 = oc1.new InnerClass(); // step (2)
        ic1.m1IC();
    }

    /**
     * Working with local classes
     */
    private static void demo3() {
        System.out.println("demo3...");
        OuterClass oc1 = new OuterClass();
        oc1.localClassDemo();

        class LocalClassFromDemo3 {
            void m1() {
                System.out.println("Hello from LocalClassFromDemo3.m1()");
            }

            static void sm1() {
                System.out.println("Hello from LocalClassFromDemo3.sm1()");
            }
        }
        LocalClassFromDemo3.sm1();
        (new LocalClassFromDemo3()).m1();
    }

    /**
     * Working with local anonymous classes
     */
    private static void demo4() {
        System.out.println("demo4...");
        int lv = 100; // because of line (**) below this local variable becomes "automatically" effectively final
        BaseClass lac = new BaseClass() {
            @Override
            void m1() {
                super.m1();
                System.out.println("lac.m1()" + lv); // (**)
            }
        };
        lac.m1();

        (new BaseClass() {
            @Override
            void m1() {
                super.m1();
                System.out.println("anonymous.m1()" + lv); // (**)
            }
        }).m1();
    }

    public static void main(String[] args) {
        demo1();
        System.out.println();

        demo2();
        System.out.println();

        demo3();
        System.out.println();

        demo4();
    }
}
