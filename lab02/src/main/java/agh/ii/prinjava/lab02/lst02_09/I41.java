package agh.ii.prinjava.lab02.lst02_09;

interface I41 {
    void m1();

    static I41 build(I41_TYPE type) {
        return switch (type) {
            case C41_IMPL_1 -> new I41Impl1();
            case C41_IMPL_2 -> new I41Impl2();
            // default -> throw new IllegalArgumentException(); // Warning: default' branch is unnecessary
        };
    }
}
