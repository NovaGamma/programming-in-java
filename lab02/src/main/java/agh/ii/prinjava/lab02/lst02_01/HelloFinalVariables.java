package agh.ii.prinjava.lab02.lst02_01;

class HelloFinalVariables {
    private final int x = 1; // A final instance variable
    public final static double PI_2 = 1.570796; // A final static/class variable

    public static void m1() {
        final int fv1 = 1; // <- a final local variable, initialized at the time of declaration
        final int fv2; // <- a final local variable, no initialisation, only declaration

        System.out.println("Inside m1()...");
        int v1 = 100;
        fv2 = 42; // <- fv2 is final
        System.out.printf("Inside m1(), fv1 = %d, fv2 = %d\n", fv1, fv2);

        v1++; // OK, v1 is not final
        // fv1++; // fv1 cannot be modified because it is final
        // fv2++; // as above
    }
}