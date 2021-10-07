package agh.ii.prinjava.lab03.exc03_01.impl;

import agh.ii.prinjava.lab03.exc03_01.QueueOfInts;

import java.io.Serializable;

public class LinkedListBasedImpl implements QueueOfInts, Serializable {

    @Override
    public String toString() {
        Node el = first;
        String serial = "LinkedListBasedImpl{";
        while (el != null) {
            serial += "{elem: " + el.elem + "}";
            el = el.next;
        }
        return serial+"}";
    }

    @Override
    public void enqueue(int x) {
        numOfElems++;
        if (!isEmpty()){
            Node new_elem = new Node(x,first,null);
            first.prev = new_elem;
            first = new_elem;
        }
        first.elem = x;
    }

    @Override
    public int dequeue(){
        if (!isEmpty()){
            numOfElems--;
            int to_return = last.elem;
            last.prev.next = null;
            return to_return;
        }
        throw new RuntimeException("The list is empty");
    }

    @Override
    public int numOfElems() {
        return numOfElems;
    }

    @Override
    public int peek() {
        if (!isEmpty()){
            return last.elem;
        }
        throw new RuntimeException("The list is empty");
    }

    private static class Node{
        int elem;
        Node next;
        Node prev;

        public Node(int elem, Node next, Node prev) {
            this.elem = elem;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node first = null;
    private Node last = null;

    private int numOfElems = 0;
}
