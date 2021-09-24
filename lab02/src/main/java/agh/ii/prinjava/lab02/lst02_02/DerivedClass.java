package agh.ii.prinjava.lab02.lst02_02;

class DerivedClass extends BaseClass {

    @Override
    public void m2() {
        System.out.println("Breaking the consistent state of the inherited part (2)");
    }

    @Override
    protected void m3() {
        System.out.println("Breaking the consistent state of the inherited part (3)");
    }

    // 'fM1()' cannot override 'fM1()' in 'BaseClass'; overridden method is final
    // @Override public final void fM1() {}
}
