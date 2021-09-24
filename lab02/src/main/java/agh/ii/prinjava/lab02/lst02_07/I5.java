package agh.ii.prinjava.lab02.lst02_07;

interface I5 {
    /**
     * static methods are also available (from Java8)
     */
    static void sm1() {
        System.out.println("I5.sm1()");
        psm2(); // OK
    }

    /**
     * and also private static methods
     */
    private static void psm2() {
        System.out.println("I5.psm2()");
        psm3(); // OK
    }

    private static void psm3() {
        System.out.println("I5.psm3()");
    }

    void m3();
}
