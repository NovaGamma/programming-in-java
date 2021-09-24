package agh.ii.prinjava.lab02.lst02_09;

// I. Factory pattern (another solution below)

/**
 * I31 contract
 */
interface I31 {
    void m1();
}

/**
 * I31 contract implementation 1 (service provider 1))
 */
class I31Impl1 implements I31 {
    @Override
    public void m1() {
        System.out.println("I31Impl1.m1()");
    }
}

/**
 * I31 contract implementation 2 (service provider 2))
 */
class I31Impl2 implements I31 {
    @Override
    public void m1() {
        System.out.println("I31Impl2.m1()");
    }
}

/**
 * Factory class
 */
final class I31Factory {
    private I31Factory() {
    }

    static I31 build(String type) { // <- Enum (instead of string) would be better here!
        return switch (type) {
            case "I31Impl1" -> new I31Impl1();
            case "I31Impl2" -> new I31Impl2();
            default -> throw new IllegalArgumentException();
        };
    }
}

/**
 * I31 (service) consumer
 */
class C33 {
    private I31 i31; // Loosely coupling

    public C33(I31 i31) {
        this.i31 = i31;
    }

    void run() {
        i31.m1();
    }
}


// II. Factory pattern (an interface static method based, solution)

enum I41_TYPE {
    C41_IMPL_1,
    C41_IMPL_2
}

/**
 * I41 contract
 */
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

/**
 * I41 contract implementation 1 (service provider 1))
 */
class I41Impl1 implements I41 {
    @Override
    public void m1() {
        System.out.println("C41Impl1.m1()");
    }
}

/**
 * I41 contract implementation 2 (service provider 2))
 */
class I41Impl2 implements I41 {
    @Override
    public void m1() {
        System.out.println("C41Impl2.m1()");
    }
}

/**
 * I41 (service) consumer
 */
class C41 {
    private I41 i41;

    public C41(I41 i41) {
        this.i41 = i41;
    }

    void run() {
        i41.m1();
    }
}

public class Main {

    private static void demo1() {
        System.out.println("demo1...");
        C33 c331 = new C33(I31Factory.build("I31Impl1"));
        C33 c332 = new C33(I31Factory.build("I31Impl2"));
        c331.run();
        c332.run();
    }

    private static void demo2() {
        System.out.println("demo2...");
        C41 c411 = new C41(I41.build(I41_TYPE.C41_IMPL_1));
        C41 c412 = new C41(I41.build(I41_TYPE.C41_IMPL_2));
        c411.run();
        c412.run();
    }

    public static void main(String[] args) {
        demo1();
        System.out.println();
        demo2();
    }
}
