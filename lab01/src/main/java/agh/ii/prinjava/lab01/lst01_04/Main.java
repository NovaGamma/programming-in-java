package agh.ii.prinjava.lab01.lst01_04;

/**
 * Use
 * <ul>
 *     <li>{@code ClassName.methodName(arguments)} to invoke a static method</li>
 *     <li>{@code ClassName.staticVariable} to access a static variable (or constant)</li>
 * </ul>
 *  This improves readability because this makes static methods and data easy to spot
 */
public class Main {
    public static void main(String[] args) {
        HelloStatic hsInst = new HelloStatic(1.54);

        System.out.println("HelloStatic.staticConst = " + HelloStatic.STATIC_CONST); // OK
        HelloStatic.staticMethod(hsInst); // OK

        // Bad style - static member 'HelloStatic.staticMethod(HelloStatic)' accessed via instance reference
        // hsInst.staticMethod(hsInst);

        // Note that 'void instanceMethod1(HelloStatic this)'; this is passed implicitly as always for instance methods
        hsInst.instanceMethod1();
        hsInst.instanceMethod2();

        // hsInst.instanceMethod1(hsInst);
    }
}
