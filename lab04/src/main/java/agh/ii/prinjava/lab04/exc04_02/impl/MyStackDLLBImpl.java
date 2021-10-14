package agh.ii.prinjava.lab04.exc04_02.impl;

import agh.ii.prinjava.lab04.exc04_02.MyStack;

public class MyStackDLLBImpl<E> implements MyStack<E> {
    private DLinkList<E> elems;

    @Override
    public E pop() {
        throw new IllegalStateException("To be implemented");
    }

    @Override
    public void push(E x) {
        throw new IllegalStateException("To be implemented");
    }

    @Override
    public int numOfElems() {
        throw new IllegalStateException("To be implemented");
    }

    @Override
    public E peek() {
        throw new IllegalStateException("To be implemented");
    }
}
