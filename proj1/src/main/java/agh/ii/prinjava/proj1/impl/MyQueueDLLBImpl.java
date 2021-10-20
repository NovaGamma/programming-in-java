package agh.ii.prinjava.proj1.impl;

public class MyQueueDLLBImpl<E> implements MyQueue<E> {
    private DLinkList<E> elems = new DLinkList<>();

    /**
     *
     * @param x, add given element to the end of the queue
     */
    @Override
    public void enqueue(E x) {
        elems.addFirst(x);
    }

    /**
     * Implementation of dequeue method using DLinkList
     * @return E the last value, at the beginning of the queue
     */
    @Override
    public E dequeue() {
        if(elems.numOfElems() != 0){
            return elems.removeLast();
        }
        else{
            throw new RuntimeException("The Queue is Empty");
        }
    }

    /**
     *
     * @return nums of elements in the queue, based on the doubly linked list implementation
     */
    @Override
    public int numOfElems() {
        return elems.numOfElems();
    }

    /**
     *
     * @return E type, the value of the first element of the queue, but does not remove it from the queue
     */
    @Override
    public E peek() {
        if(elems.numOfElems() != 0){
            return elems.getLastValue();
        }
        else {
            throw new RuntimeException("The Stack is Empty");
        }
    }
}
