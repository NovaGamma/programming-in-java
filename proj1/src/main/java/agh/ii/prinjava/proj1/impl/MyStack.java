package agh.ii.prinjava.proj1.impl;

public interface MyStack<E> {
    /**
     *
     * @return E type, the first value of the stack and remove it from the stack
     */
    E pop();

    /**
     *
     * @param x the value to be added to the stack
     * method to add the given value at the top of the stack
     */
    void push(E x);

    /**
     *
     * @return True if the number of elements in the stack is 0, meaning that the stack is empty
     */
    default boolean isEmpty() {
        return numOfElems() == 0;
    }

    /**
     *
     * @return int, the number of elements in the stack
     */
    int numOfElems();

    /**
     *
     * @return E type, the value of the first element of the stack, but does not remove it from the stack
     */
    E peek();

    /** Consider pros and cons of having a factory method in the interface */
    static <T> MyStack<T> create() {
        return new MyStackDLLBImpl<T>();
    }
}
