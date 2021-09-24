package agh.ii.prinjava.lab02.lst02_03;

/**
 * A class that is declared final cannot be subclassed.
 * <p>This is useful when creating an immutable class like the String class
 */


// Cannot inherit from final 'FinalClass'
// class TryingToExtendFinalClass extends FinalClass {}

public class Main {
    public static void main(String[] args) {
        FinalClass fc = new FinalClass(10);
        System.out.println("fc.x = " + fc.getX());
    }
}
