package agh.ii.prinjava.lab06.lst01_01;

/**
 * Auxiliary definitions
 */
class C1 {
    C1(double x, double y) {
        System.out.println("Building C1 with (x = " + x + ", y = " + y + ")...");
    }

    void m1(double x, double y) {
        System.out.println("C1.m1(x=" + x + ", y=" + y + ")");
    }

    void m2(double x) {
        System.out.println("C1.m1(x=" + x + ")");
    }

    static void sm1(double a, double b) {
        System.out.println("C1.sm1(x=" + a + ", b=" + b + ")");
    }
}
