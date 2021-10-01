package agh.ii.prinjava.lab03.lst03_10;

import java.util.Arrays;

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
