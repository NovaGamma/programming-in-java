package agh.ii.prinjava.proj1.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyStackDLLBImplTest {
    MyStack<Integer> stackOfInts = MyStack.create();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void push(){
        MyStack<Integer> stack = MyStack.create();
        stack.push(1);
        assertEquals("Stack{DLinkList{ 1 }}",stack.toString());
        stack.push(2);
        assertEquals("Stack{DLinkList{ 2 1 }}",stack.toString());
    }

    @Test
    void pop(){
        MyStack<Integer> stack = MyStack.create();
        stack.push(1);
        assertEquals(1,stack.pop());
        assertEquals("Stack{DLinkList{ }}",stack.toString());
        stack.push(1);
        stack.push(2);
        assertEquals(2,stack.pop());
        assertEquals("Stack{DLinkList{ 1 }}",stack.toString());
    }

    @Test
    void peek(){
        MyStack<Integer> stack = MyStack.create();
        stack.push(1);
        assertEquals(1,stack.peek());
        assertEquals("Stack{DLinkList{ 1 }}",stack.toString());
        stack.push(2);
        assertEquals(2,stack.peek());
        assertEquals("Stack{DLinkList{ 2 1 }}",stack.toString());
    }
}