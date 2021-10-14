package agh.ii.prinjava.lab04.lst04_01;


public class Main {

    public static void main(String[] args){
        Integer[] ints = {1, 2, 3};
        String[] strs = {"A", "B", "C"};

        print(ints);
        print(strs);
    }

    public static void print(Object[] elems){
        for (int i = 0; i < elems.length; i++){
            System.out.print(elems[i] + " ");
        System.out.println();
        }
    }

    public static void demo1() {
        System.out.println("demo1...");

        BoxForInt bi = new BoxForInt(10);
        System.out.println("bi = " + bi);
        bi.setX(1);
        int xi = bi.getX();
        System.out.println("x = " + xi);

        BoxForString bs = new BoxForString("abc");
        System.out.println("bs = " + bs);
        bs.setX("ABC");
        String xs = bs.getX();
        System.out.println("bs.x = " + xs);
    }

    public static void demo2() {
        System.out.println("\ndemo2...");

        BoxForObject bo1 = new BoxForObject(10);
        System.out.println("bo1 = " + bo1);
        bo1.setX(1); // <- Autoboxing: int -> Integer
        System.out.println("bo1.x = " + bo1.getX());
        int xi = (int) bo1.getX(); // <- explicit cast is needed
        System.out.println("x = " + xi);

        System.out.println("---");

        BoxForObject bo2 = new BoxForObject("abc");
        System.out.println("bo2 = " + bo2);
        bo2.setX("ABC");
        System.out.println("bo2.x = " + bo2.getX());
        String xs = (String) bo2.getX(); // <- explicit cast is needed
        System.out.println("x = " + xs);
    }

    public static void demo3() {
        System.out.println("\ndemo3...");
        BoxForInt bi = new BoxForInt(10);
        bi.setX(1); // OK
        int xi = bi.getX();
        //bi.setX("abc"); // Compilation error, required type - int, provided - String

        System.out.println("---");

        BoxForString bs = new BoxForString("abc");
        bs.setX("ABC"); // OK
        String xs = bs.getX();
        //bs.setX(1); // Compilation error, required type - String, provided - int

        System.out.println("---");

        BoxForObject bo = new BoxForObject(10);
        bo.setX(1); // OK
        bo.setX(1.34); // OK
        bo.setX(new BoxForInt(10)); // OK
        bo.setX(new BoxForObject(new BoxForObject("XYZ"))); // OK
        bo.setX("abc"); // OK
        // ...other lines...

        bi.setX(5);
        bs.setX("DEF");

        // What is the type of the value in "bo"? To perform explicit cast we should know...
        String xos = (String) bo.getX(); // as above
        try {
            int xoi = (int) bo.getX(); // no problem signalled be the compiler
            System.out.println("xoi = " + xoi);
        } catch (ClassCastException e) {
            System.out.println("ClassCastException was caught!");
            e.printStackTrace();
        }
    }

    /*public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
    }*/
}
