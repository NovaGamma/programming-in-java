package agh.ii.prinjava.proj1.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyQueueDLLBImplTest {
    MyQueue<Integer> queueOfInts = MyQueue.create();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void enqueue(){
        MyQueue<Integer> queue = MyQueue.create();
        queue.enqueue(1);
        assertEquals("Queue{DLinkList{ 1 }}",queue.toString());
        queue.enqueue(2);
        assertEquals("Queue{DLinkList{ 2 1 }}",queue.toString());
    }

    @Test
    void dequeue(){
        MyQueue<Integer> queue = MyQueue.create();
        queue.enqueue(1);
        assertEquals(1,queue.dequeue());
        assertEquals("Queue{DLinkList{ }}",queue.toString());
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(1,queue.dequeue());
        assertEquals("Queue{DLinkList{ 2 }}",queue.toString());
    }

    @Test
    void peek(){
        MyQueue<Integer> queue = MyQueue.create();
        queue.enqueue(1);
        assertEquals(1,queue.peek());
        assertEquals("Queue{DLinkList{ 1 }}",queue.toString());
        queue.enqueue(2);
        assertEquals(1,queue.peek());
        assertEquals("Queue{DLinkList{ 2 1 }}",queue.toString());
    }
}