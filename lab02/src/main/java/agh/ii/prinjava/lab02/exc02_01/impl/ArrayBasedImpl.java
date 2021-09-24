package agh.ii.prinjava.lab02.exc02_01.impl;

import java.util.Arrays;
import agh.ii.prinjava.lab02.exc02_01.StackOfInts;

public class ArrayBasedImpl implements StackOfInts {

    @Override
    public int pop() {
        if (isEmpty() == false){
            int to_return = stack[0];
            stack = Arrays.copyOfRange(stack,1,stack.length-1);
            return to_return;
        }
        throw new IllegalStateException("The list is empty");
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

    private int[] stack;
    private int numOfElems = 0;
}
