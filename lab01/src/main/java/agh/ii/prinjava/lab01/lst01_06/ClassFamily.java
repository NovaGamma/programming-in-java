package agh.ii.prinjava.lab01.lst01_06;

class B1 {
    B1() {
        System.out.println("B1.B1()");
    }
}

class D1 extends B1 {
    public D1() {
        System.out.println("D1.D1()");
    }
}

/**
 * Problem 1
 */
class B2 {
    /**
     * private constructor -> no access from the derived classes
     */
    private B2() {
    }
}
//class D2 extends B2 {} // There is no default constructor available in B2

/**
 * Problem 2
 */
class B3 {
    // no default constructor
    B3(int x) {
        System.out.println("B3.B3(int)");
    }
}
//class D3 extends B3 {} // There is no default constructor available in B3

/**
 * Problem 3
 */
class D4 extends B3 {
    D4() {
        //System.out.println("D4.D4()");
        super(1); // <- Call to 'super()' must be first statement in constructor body
        System.out.println("D4.D4()");
    }
}

/**
 * Problem 4
 */
//class D5 extends B3 { D5(int x) {} } // There is no default constructor available in B3

/**
 * Problem 5: only the default/parameterless constructor can be called implicitly
 */
class D6 extends B3 {
    D6(int x) {
        super(x); // <- explicit call of B3(int x)
        System.out.println("D6.D6(int)");
    }
}

/**
 * Problem 6
 */
class D7 extends B3 {
    D7(int x) {
        super(x); // <- explicit call of B3(int x)
        System.out.println("D7.D7(int)");
    }

    D7() {
        //System.out.println("D7.D7()"); // Call to 'this()' must be first statement in constructor body
        this(1); // <- calling D7(1)
        System.out.println("D7.D7()");
    }
}

class B4 {
    public B4() {
        System.out.println("B4.B4()");
    }

    private int pr_b;
    int b1;
    int b2;
}

class D8 extends B4 {
    int d;
    String b2; // hiding of b2

    public D8() {
        System.out.println("D8.D8()");
        super.b1 = 1; // the same as this.b1 = 1
        this.d = 4;
        //super.d = 1; // supper refers to B4, and there is no d there
        //this.pr_b; // = super.pr_b; no access to private members of the base class

        super.b2 = 4; // the field must be accessed through super.b2 or D8.super.b2 = 5;
        this.b2 = "abc"; // or this.b2
        D8.super.b2 = 5;
        b2 = "def";
    }
}

/**
 * Exercise: draw the class diagram for D9 and explain the sequence of constructor calls
 */
class D9 extends D1 {
    private D1 d1;
    private D4 d4;
    private D7 d7 = new D7();

    {
        d4 = new D4();
    }

    public D9() {
        super(); // explicit call; if not present this would be called implicitly
        System.out.println("D9.D9()");
        System.out.println("About to execute: d1 = new D1(); // D1 extends B1");
        d1 = new D1();
    }
}
