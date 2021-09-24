package agh.ii.prinjava.lab02.lst02_07;

class C3 implements I4, I5 {
    @Override
    public void m1() {
        System.out.println("C3.m1()");
        m2(); // OK, but
        // sm1(); // <- Error: Static method may be invoked on containing interface class only
        I5.sm1();
        //I5.psm2(); // <- Error: psm2() has private access in I5
    }

    @Override
    public void m3() {
        System.out.println("C3.m3()");
    }
}
