package agh.ii.prinjava.lab04.lst04_04;

public class Main {
    private static void demo0() {
        System.out.println("demo0...");

        GenClass0<Double> gc1 = new GenClass0<>();
        gc1.m1(Double.valueOf(4.5));

        GenClass0<String> gc2 = new GenClass0<>();
        gc2.m1("ABC");

        GenClass0<CA> gc3 = new GenClass0<>();
        gc3.m1(new CA());

        GenClass0<CB> gc4 = new GenClass0<>();
        gc4.m1(new CB());

        GenClass0<CC> gc5 = new GenClass0<>();
        gc5.m1(new CC());
    }

    private static void demoA() {
        System.out.println("\ndemoA...");

        // Type parameter 'Double' is not within its bound; should implement 'IA'
        // GenClassA<Double> gcA1 = new GenClassA<>();

        GenClassA<CA> gcA = new GenClassA<>(); // OK, CA implements IA, class GenClassA<T extends IA>
        gcA.m1(new CA());
        // gcA.m1(new CB()); // Required type CA, provide CB
        System.out.println("---");

        GenClassA<CB> gcB = new GenClassA<>();
        gcB.m1(new CB());
        System.out.println("---");

        GenClassA<CC> gcC = new GenClassA<>();
        gcC.m1(new CC());
    }

    private static void demoB() {
        System.out.println("\ndemoB...");

        // Type parameter 'CA' is not within its bound; should implement 'IB'
        //GenClassB<CA> gcA = new GenClassB<>();

        GenClassB<CB> gcB = new GenClassB<>(); // OK, class GenClassB<T extends IB>
        gcB.m1(new CB());
        System.out.println("---");

        GenClassB<CC> gcC = new GenClassB<>(); // OK
        gcC.m1(new CC());
    }

    private static void demoC() {
        System.out.println("\ndemoC...");

        // Type parameter 'CA' is not within its bound; should implement 'IC'
        //GenClassC<CA> gcA = new GenClassC<>();

        // Type parameter 'CB' is not within its bound; should implement 'IC'
        //GenClassC<CB> gcA = new GenClassC<>();

        GenClassC<CC> gcC = new GenClassC<>(); // OK
        gcC.m1(new CC());
    }

    private static void demoA10() {
        System.out.println("\ndemoA10...");
        GenClassA10<A10> gcA10 = new GenClassA10<>(); // OK, A10 implements both IA and I10
        gcA10.m1(new A10());
    }

    public static void main(String[] args) {
        demo0();
        demoA();
        demoB();
        demoC();
        demoA10();
    }
}
