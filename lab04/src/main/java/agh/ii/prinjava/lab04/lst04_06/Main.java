package agh.ii.prinjava.lab04.lst04_06;

public class Main {

    private static <T> void copy_v1(GenBox<T> src, GenBox<T> dst) {
        dst.setX(src.getX());
    }

    /**
     * Wildcards: types of constraints of a variable type
     * <ul>
     *     <li><p>{@code GenBox<? extends T>} - "?" in this expression called a <i>wildcard</i>,
     *         stands for some type that is a subtype of T (compare C# - {@code out})</li>
     *     <li>{@code GenBox<? super T>} - "?" stands for some type that is a supertype of T
     *         (compare C# - {@code in})</li>
     * </ul>
     */
    private static <T> void copy_v2(GenBox<? extends T> src, GenBox<? super T> dst) {
        dst.setX(src.getX());
    }

    private static void demo1() {

        // (1)
        GenBox<B> gbB;
        gbB = new GenBox<>(new B()); // OK
        //gbB = new GenBox<A>(new A()); // Required GenBox<B>, provided GenBox<A>
        //gbB = new GenBox<C>(new C()); // Required GenBox<B>, provided GenBox<C>

        // (2) <? extends ...> (subtype wildcard, aka. Upper Bounded Wildcard)
        GenBox<? extends B> gbExB;
        gbExB = new GenBox<B>(new B()); // OK, GenBox<B> is a subtype of GenBox<? extends B>
        gbExB = new GenBox<C>(new C()); // OK, GenBox<C> is a subtype of GenBox<? extends B> (covariance)

        //gbEb = new GenBox<A>(new A()); // Required GenBox<? extends B>, provided GenBox<A>

        // (3) <? super ...> (supertype wildcard, aka. Lower Bounded Wildcard)
        GenBox<? super B> gbSuB;
        gbSuB = new GenBox<B>(new B()); // OK, GenBox<B> is a subtype of GenBox<? super B>
        gbSuB = new GenBox<A>(new A()); // OK, GenBox<A> is a subtype (!) of GenBox<? super B> (contravariance)

        //gbSuB = new GenBox<C>(new C()); // Required GenBox<? super B>, provided GenBox<C>

        // (3) <?> (unbounded wildcards, aka. any object wildcard)
        GenBox<?> bW;
        bW = new GenBox<A>(new A()); // OK
        bW = new GenBox<B>(new B()); // OK
        bW = new GenBox<C>(new C()); // OK
    }

    private static void demo2() {
        // (1)
        GenBox<?> gb1 = new GenBox<>(new A());
        gb1 = new GenBox<>(new B());
        gb1 = new GenBox<>(new C());

        // (2) <? extends ...> - read only (out)
        GenBox<? extends B> gbExB = new GenBox<B>(new B());
        gbExB = new GenBox<C>(new C()); // OK
        B b1 = gbExB.getX(); // OK, "? extends B" is-a B
        A a1 = gbExB.getX(); // OK
        //C c1 = gbExB.getX();
        //gbExB = new GenBox<A>(new A()); // compilation error: Required type: GenBox<? extends B>, provided GenBox<A>

        //gbExB.setX(new B()); // compilation error: Required type: capture of ? extends B, provided B
        //gbExB.setX(new A()); // compilation error: Required type: capture of ? extends B, provided A
        //gbExB.setX(new C()); // compilation error: Required type: capture of ? extends B, provided C

        // (3) <? super ...> - write-only (in)
        GenBox<? super B> gbSuB = new GenBox<B>(new B());
        gbSuB.setX(new B()); // OK
        //B b2 = gbSuB.getX(); // compilation error: Required type: B, provided: capture of ? super B
        //A a2 = gbSuB.getX(); // compilation error: Required type: A, provided: capture of ? super B
        Object o1 = gbSuB.getX(); // OK, but here we have Object

        gbSuB = new GenBox<A>(new A());
        gbSuB.setX(new B()); // OK
        //B b3 = gbSuB.getX(); // compilation error: Required type: B, provided: capture of ? super B
        //A a3 = gbSuB.getX(); // compilation error: Required type: A, provided: capture of ? super B
        Object o2 = gbSuB.getX(); // OK, but here we have Object
    }

    private static void demo3() {
        GenBox<A> gbA1 = new GenBox<>(new A());
        GenBox<A> gbA2 = new GenBox<>(new A());
        copy_v1(gbA1, gbA2);

        GenBox<B> gbB1 = new GenBox<>(new B());
        GenBox<B> gbB2 = new GenBox<>(new B());
        copy_v1(gbB1, gbB2);

        GenBox<C> gbc1 = new GenBox<>(new C());
        GenBox<C> gbc2 = new GenBox<>(new C());
        copy_v1(gbc1, gbc2);
    }

    private static void demo4() {
        GenBox<A> gbA1 = new GenBox<>(new A());
        GenBox<A> gbA2 = new GenBox<>(new A());
        copy_v1(gbA1, gbA2); // OK, as above
        copy_v2(gbA1, gbA2);

        GenBox<B> gbB1 = new GenBox<>(new B());
        GenBox<B> gbB2 = new GenBox<>(new B());
        copy_v1(gbB1, gbB2); // OK, as above
        copy_v2(gbB1, gbB2);

        //copy_v1(gbB1, gbA2); // compilation error (Incompatible equality constraints)
        copy_v2(gbB1, gbA2); // OK

        GenBox<C> gbc1 = new GenBox<>(new C());
        GenBox<C> gbc2 = new GenBox<>(new C());
        copy_v1(gbc1, gbc2);
        copy_v2(gbc1, gbc2);

        //copy_v1(gbc1, gbA2); // compilation error (Incompatible equality constraints)
        //copy_v1(gbc1, gbB2); // compilation error (Incompatible equality constraints)
        copy_v2(gbc1, gbA2); // OK
        copy_v2(gbc1, gbB2); // OK
    }

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
        demo4();
    }
}
