package agh.ii.prinjava.lab02.exc02_01.impl;

import agh.ii.prinjava.lab02.exc02_01.StackOfInts;

public class ArrayBasedImpl implements StackOfInts {

    @Override
    public int pop() {
        throw new IllegalStateException("To be implemented");
    }

    @Override
    public void push(int x) {
        throw new IllegalStateException("To be implemented");
    }

    @Override
    public int numOfElems() {
        return numOfElems;
    }

    @Override
    public int peek() {
        throw new IllegalStateException("To be implemented");
    }

    private int numOfElems = 0;
}
