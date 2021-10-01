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
class ShallowCloner implements Cloneable {
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
