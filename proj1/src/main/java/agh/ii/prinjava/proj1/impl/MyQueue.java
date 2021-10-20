package agh.ii.prinjava.proj1.impl;

public interface MyQueue<E> {
    /**
     *
     * @param x, add given element to the end of the queue
     */
    void enqueue(E x);

    /**
     *
     * @return E type, the element at the head of the queue
     */
    E dequeue();

    /**
     *
     * @return True if the number of elements in the queue is 0, meaning that the stack is empty
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
     * @return E type, the value of the head element of the queue, but does not remove it from the queue
     */
    E peek();

    /** Consider pros and cons of having a factory method in the interface */
    static <T> MyQueue<T> create() {
        return new MyQueueDLLBImpl<>();
    }
}
