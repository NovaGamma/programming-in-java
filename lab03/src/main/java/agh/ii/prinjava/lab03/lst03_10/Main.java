package agh.ii.prinjava.lab03.lst03_10;

import java.util.Arrays;

/**
 * The Cloneable marker interface specifies that the objects of this class can be cloned.
 *
 * <p>To define a custom class that implements the {@code Cloneable} interface, the class must override
 * the {@code clone()} method (from the {@code Object} class).
 *
 * @see <a href="https://github.com/AdoptOpenJDK/openjdk-jdk16/blob/master/src/java.base/share/classes/java/lang/Cloneable.java">Cloneable</a>
 * @see <a href="https://github.com/AdoptOpenJDK/openjdk-jdk16/blob/master/src/java.base/share/classes/java/lang/Object.java">Object</a>
 */
class ShalowCloner implements Cloneable {
    private int x = 10;
    private int[] ints = {1, 2, 3, 4, 5};

    public void setIntAtIdx(int idx, int val) {
        ints[idx] = val;
    }

    /**
     * Creates a shallow copy of the source object
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // shallow copy (but "ints" is of a reference type!)
    }

    @Override
    public String toString() {
        return "ShalowCloner{" + "x=" + x + ", ints=" + Arrays.toString(ints) + '}';
    }
}

/**
 * DeepCloner constructs a deep copy of the source object
 *
 * @see <a href="https://en.wikipedia.org/wiki/Object_copying">Object copying</a>
 */
class DeepCloner implements Cloneable {
    private int x = 10;
    private int[] ints = {1, 2, 3, 4, 5};

    public void setIntAtIdx(int idx, int val) {
        ints[idx] = val;
    }

    /**
     * Creates a deep copy of the source object
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        DeepCloner clone = (DeepCloner) super.clone(); // Shallow copy (OK, for fields of primitive types)
        clone.ints = ints.clone(); // <- Augment the above by a deep copy of the field of a reference type
        return clone; // deep copy
    }

    @Override
    public String toString() {
        return "DeepCloner{" + "x=" + x + ", ints=" + Arrays.toString(ints) + '}';
    }
}

public class Main {
    private static void shalowCloneDemo() {
        System.out.println("shalowCloneDemo...");

        ShalowCloner sc1 = new ShalowCloner();
        System.out.println("sc1: " + sc1);
        try {
            ShalowCloner sc1Clone = (ShalowCloner) sc1.clone(); // <- Object clone() throws...;
            System.out.println("sc1Clone: " + sc1Clone);

            System.out.println("\nUpdating the clone (setting ints[0] to 100)...");
            sc1Clone.setIntAtIdx(0, 100);

            System.out.println("sc1Clone: " + sc1Clone);
            System.out.println("sc1: " + sc1);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    private static void deepCloneDemo() {
        System.out.println("deepCloneDemo...");

        DeepCloner dc1 = new DeepCloner();
        System.out.println("dc1: " + dc1);
        try {
            DeepCloner dc1Clone = (DeepCloner) dc1.clone();
            System.out.println("dc1Clone: " + dc1Clone);

            System.out.println("\nUpdating the clone (setting ints[0] to 100)...");
            dc1Clone.setIntAtIdx(0, 100);

            System.out.println("dc1Clone: " + dc1Clone);
            System.out.println("dc1: " + dc1);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        shalowCloneDemo();
        System.out.println();
        deepCloneDemo();
    }
}
