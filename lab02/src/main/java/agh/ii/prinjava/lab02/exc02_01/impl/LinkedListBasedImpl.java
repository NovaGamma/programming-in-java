package agh.ii.prinjava.lab02.exc02_01.impl;

import agh.ii.prinjava.lab02.exc02_01.StackOfInts;

public class LinkedListBasedImpl implements StackOfInts {

    @Override
    public int pop(){
        if (isEmpty() == false){
            numOfElems--;
            int to_return = start.elem;
            start = start.next;
            return to_return;
        }
        throw new IllegalStateException("The list is empty");
    }

    @Override
    public void push(int x) {
        numOfElems++;
        if (isEmpty() == false){
            Node new_start = new Node(x);
            new_start.next = start;
            start = new_start;
        }
        else{
            start = new Node(x);
        }
    }

    @Override
    public int numOfElems() {
        return numOfElems;
    }

    @Override
    public int peek() {
        if (isEmpty() == false) {
            return start.elem;
        }
        throw new IllegalStateException("The list is empty");
    }

    private static class Node {
        int elem;
        Node next;

        public Node(int elem) {
            this.elem = elem;
        }
    }

    private Node start;
    private int numOfElems = 0;
}
