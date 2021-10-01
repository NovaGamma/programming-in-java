package agh.ii.prinjava.lab03.lst03_01;

class Error1 extends Error { // A user-defined Error -- you should never do it!!!
    public Error1() {
        super("Error1 occurred");
    }
}