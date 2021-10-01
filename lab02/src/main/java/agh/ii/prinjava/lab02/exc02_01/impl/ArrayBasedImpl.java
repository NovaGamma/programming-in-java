package agh.ii.prinjava.lab02.exc02_01.impl;

import java.util.Arrays;
import agh.ii.prinjava.lab02.exc02_01.StackOfInts;

public class ArrayBasedImpl implements StackOfInts {

    @Override
    public int pop() {
        if (!isEmpty()){
            numOfElems--;
            int to_return = stack[0];
            stack = Arrays.copyOfRange(stack,1,stack.length-1);
            return to_return;
        }
        throw new RuntimeException("The list is empty");
    }

    @Override
    public void push(int x) {
        if (!isEmpty()){
            int[] new_stack = new int[numOfElems+1];
            System.arraycopy(stack, 0, new_stack, 1, numOfElems + 1);
            stack = new_stack;
        }
        else{
            stack[0] = x;
        }
        numOfElems++;
    }

    @Override
    public int numOfElems() {
        return numOfElems;
    }

    @Override
    public int peek() {
        if (!isEmpty()){
            return stack[0];
        }
        throw new RuntimeException("The list is empty");
    }

    private int[] stack;
    private int numOfElems = 0;
}
