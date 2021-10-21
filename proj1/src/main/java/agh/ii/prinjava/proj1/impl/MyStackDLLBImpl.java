package agh.ii.prinjava.proj1.impl;

public class MyStackDLLBImpl<E> implements MyStack<E> {
    private DLinkList<E> elems = new DLinkList<>();

    /**
     * Implementation of pop method using DLinkList
     * @return the first value, at the top of the stack
     */
    @Override
    public E pop() {
        if(!isEmpty()){
            return elems.removeFirst();
        }
        else{
            throw new RuntimeException("The Stack is Empty");
        }
    }

    /**
     *
     * @param x the value to be added to the stack
     */
    @Override
    public void push(E x) {
        elems.addFirst(x);
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
     * @return E type, the value of the first element of the stack, but does not remove it from the stack
     */
    @Override
    public E peek() {
        if(!isEmpty()){
            return elems.getFirst();
        }
        else {
            throw new RuntimeException("The Stack is Empty");
        }
    }
}
