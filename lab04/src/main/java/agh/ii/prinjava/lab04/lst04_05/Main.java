package agh.ii.prinjava.lab04.lst04_05;

// class GD7 extends GenBase<T> {} // Cannot resolve symbol 'T'

public class Main {
    private static void demo1() {
        GD1<Integer> gd1 = new GD1<>();
        gd1.setX(1); // the param (x) of setX is inferred as Object
        gd1.setX(1.5);
        gd1.setX("abc");
        gd1.setX(new Object());
    }

    private static void demo2() {
        GD2<Integer> gd2 = new GD2<>(); // <> is inferred as "Integer"
        gd2.setX("Abc"); // the param (x) of setX is inferred as String
    }

    private static void demo3() {
        GD3<Integer> gd3 = new GD3<>(); // <> is inferred as "Integer"
        gd3.setX(1); // the param (x) of setX is inferred as Integer
        //gd3.setX("abc");
    }

    private static void demo4() {
        GD4<Integer> gd4 = new GD4<>(); // <> is inferred as "Integer"
        gd4.setX("abc"); // the param (x) of setX is inferred as String, because it's fixed in the class definition
        gd4.setY(1); // the param (y) of setY is inferred as Integer
    }

    private static void demo5() {
        GD5<Integer> gd5 = new GD5<>(); // <> is inferred as "Integer"
        gd5.setX(1); // the param (x) of setX is inferred as Integer, because it's fixed in the class definition
        gd5.setY(1); // the param (y) of setY is inferred as Integer
    }

    private static void demo6() {
        GD6<Integer> gd61 = new GD6<>(); // <> is inferred as "Integer"
        gd61.setX(1); // the param (x) of setX is inferred as Integer
        gd61.setY(2); // the param (y) of setY is inferred as Integer

        GD6<Double> gd62 = new GD6<>(); // <> is inferred as "Double"
        gd62.setX(1.5); // the param (x) of setX is inferred as Double
        gd62.setY(5.1); // the param (y) of setY is inferred as Double

        //GD6<String> gd63 = new GD6<>();
        GD6<Byte> gd63 = new GD6<>(); // <> is inferred as "Byte"
        gd63.setX((byte) 1);
        //...
    }

    private static void demo7() {
        I1 i1 = new GDI1();
        Object o1 = i1.m1();
        i1.m2(o1);
    }

    private static void demo8() {
        I1<String> i1 = new GDI2();
        final String s = i1.m1();
        i1.m2(s);
    }

    private static void demo9() {
        I1<Double> i1 = new GDI3<>(); // T ~ Double
        final Double x = i1.m1();
        i1.m2(x);
    }

    private static void demo10() {
        GD21 gd21 = new GD21();
        gd21.setX(1); // x inferred as Integer
        gd21.setY("abc"); // y inferred as String

        GD22<Integer> gd22 = new GD22<>(); // T inferred as Integer
        gd22.setX(1); // x inferred as Integer
        gd22.setY("abc"); // y inferred as String

        GD23<Double> gd23 = new GD23<>(); // T inferred as Double
        gd23.setX(1.5); // x inferred as Double
        gd23.setY(3.2); // y inferred as Double

        GD24<Double, String> gd24 = new GD24<>(); // T inferred as Double, U as String
        gd24.setX(1.5); // x inferred as Double
        gd24.setY("abc"); // y inferred as String

        GD25<Double, String> gd251 = new GD25<>(); // T inferred as Double, U as String
        gd251.setX("abc"); // x inferred as String
        gd251.setY(2.5); // y inferred as Double

        //GenBase2<Double, String> gd252 = new GD25<Double, String>(); // type parameters are reversed
        GenBase2<Double, String> gd523 = new GD25<String, Double>();
    }

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
        demo4();
        demo5();
        demo6();
        demo7();
        demo8();
        demo9();
        demo10();
    }
}
