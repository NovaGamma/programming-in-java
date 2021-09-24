package agh.ii.prinjava.lab02.lst02_08;

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
