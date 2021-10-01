package agh.ii.prinjava.lab03.lst03_08;

/**
 * A <i>marker interface</i> (aka. <i>tagging interface</i>) is an interface that has no methods
 * or constants inside it. It provides run-time type information about objects,
 * so the compiler and JVM have additional information about the object.
 *
 * <p>Note: in modern Java, marker interfaces often can be replaced by annotations
 */
interface Reliable {
}

/**
 * Dunedain is marked/tagged as Reliable
 */
class Dunedain implements Reliable {
}

/**
 * Orc is not marked/tagged as Reliable
 */
class Orc {
}

public class Main {
    private static void demo1(Object o) {
        if (o instanceof Reliable) {
            System.out.println("Reliable");
        } else {
            System.out.println("Unreliable");
        }
    }

    public static void main(String[] args) {
        demo1(new Dunedain());
        demo1(new Orc());
    }
}
