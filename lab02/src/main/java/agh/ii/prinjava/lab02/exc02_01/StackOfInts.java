package agh.ii.prinjava.lab02.exc02_01;

/**
 * Stack of integers - Abstract Data Type (ADT)
 */

public interface StackOfInts {
    /**
     *
     * remove the first element of the list and return it
     */
    int pop();

    /**
     *
     * put the element given as parameter at the first place of the list
     */
    void push(int x);

    /**
     *
     * return True if the list is Empty
     */
    default boolean isEmpty() {
        return numOfElems() == 0;
    }

    /**
     *
     * return the number of elements in the list
     */
    int numOfElems();

    /**
     *
     * return the first element of the list
     */
    int peek();
}
