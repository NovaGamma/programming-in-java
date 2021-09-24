package agh.ii.prinjava.lab02.exc02_01;

/**
 * Stack of integers - Abstract Data Type (ADT)
 */

public interface StackOfInts {
    int pop();

    void push(int x);

    default boolean isEmpty() {
        return numOfElems() == 0;
    }

    int numOfElems();

    int peek();
}
