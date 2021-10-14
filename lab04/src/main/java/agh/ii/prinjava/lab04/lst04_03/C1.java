package agh.ii.prinjava.lab04.lst04_03;

/**
 * Just like type declarations, method declarations can be generic, i.e., parameterized by one or more type parameters.
 * A <i>generic method</i> is a method that declares one or more type variables.
 * <p>Generic methods allow type parameters to be used to express dependencies among
 * the types of one or more arguments to a method and/or its return type.
 */
class C1 {
    /**
     * Instance generic method
     */
    <T> T m1(T x) {
        return x;
    }

    /**
     * Static generic method
     */
    static <T> T sm1(T y) {
        return y;
    }
}
