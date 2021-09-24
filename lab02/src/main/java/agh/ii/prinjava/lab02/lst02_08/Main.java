package agh.ii.prinjava.lab02.lst02_08;

class C6 {
    void doStuff() {
        System.out.println("C6.doStuff");
    }
}

/**
 * An example of a tight coupling. In most cases a VERY bad design decision <br>
 * (interfaces, as contracts, should be used instead -> loose coupling)
 */
class C7 {
    private C6 c6 = new C6(); // (very) tight coupling

    void doC7Stuff() {
        System.out.println("C7.doStuff");
        c6.doStuff();
    }
}

/**
 * Refactoring of the above (<a href="https://www.baeldung.com/solid-principles">see SOLID principles</a>)
 */
interface IC8 {
    void doStuff();
}

class C8Impl1 implements IC8 {
    @Override
    public void doStuff() {
        System.out.println("C8Impl1.doStuff");
    }
}

class C8Impl2 implements IC8 {
    @Override
    public void doStuff() {
        System.out.println("C8Impl2.doStuff");
    }
}

class C8Impl3 implements IC8 {
    @Override
    public void doStuff() {
        System.out.println("C8Impl3.doStuff");
    }
}

class C9 {
    private IC8 ic8; // IC8 as the contract

    void doC9Stuff() {
        System.out.println("C9.doStuff");
        ic8.doStuff(); // calling "doStuff" through IC8 interface
    }

    /**
     * The implementing class is given as the argument of the constructor
     */
    public C9(IC8 ic8) {
        this.ic8 = ic8;
    }
}

public class Main {

    private static void demo1() {
        System.out.println("demo1...");
        C7 c7 = new C7();
        c7.doC7Stuff();
    }

    private static void demo2() {
        System.out.println("demo2...");

        //After refactoring
        C9[] c9s = {
                new C9(new C8Impl1()),
                new C9(new C8Impl2()),
                new C9(new C8Impl3())
        };

        for (var c9 : c9s) {
            c9.doC9Stuff();
        }
    }

    public static void main(String[] args) {
        demo1();
        System.out.println();

        demo2();
    }
}
