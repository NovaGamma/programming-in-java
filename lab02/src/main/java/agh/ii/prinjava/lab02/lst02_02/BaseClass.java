package agh.ii.prinjava.lab02.lst02_02;

class BaseClass {
    public BaseClass() {
        System.out.println("Initialisation of BaseClass...");

        fM1(); // OK, fM1 is final

        m2(); // Not recommended!
        m3(); // As above

        fM4(); // OK, fM1 is final
        m5(); // OK, m3 is private -> cannot be overridden in subclasses
    }

    public final void fM1() {
        System.out.println("BaseClass.fM1(): performing init phase (1)");
    }

    public void m2() {
        System.out.println("BaseClass.m1(): performing init phase (2)");
    }

    protected void m3() {
        System.out.println("BaseClass.21(): performing init phase (3)");
    }

    public final void fM4() {
        System.out.println("BaseClass.fM2(): performing init phase (4)");
    }

    private void m5() {
        System.out.println("BaseClass.m3(): performing init phase (5)");
    }
}
