package agh.ii.prinjava.proj1.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DLinkListTest {
    DLinkList<Integer> dLinkList = new DLinkList<>();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addFirst(){
        DLinkList<Integer> list = new DLinkList<>();
        list.addFirst(1);
        assertEquals("DLinkList{ 1 }",list.toString());
        list.addFirst(2);
        assertEquals("DLinkList{ 2 1 }",list.toString());
    }

    @Test
    void addLast(){
        DLinkList<Integer> list = new DLinkList<>();
        list.addLast(1);
        assertEquals("DLinkList{ 1 }",list.toString());
        list.addLast(2);
        assertEquals("DLinkList{ 1 2 }",list.toString());
    }

    @Test
    void removeFirst(){
        DLinkList<Integer> list = new DLinkList<>();
        list.addFirst(1);
        assertEquals(1,list.removeFirst());
        assertEquals("DLinkList{ }",list.toString());
        list.addFirst(1);
        list.addFirst(2);
        assertEquals(2,list.removeFirst());
        assertEquals("DLinkList{ 1 }",list.toString());
    }

    @Test
    void removeLast(){
        DLinkList<Integer> list = new DLinkList<>();
        list.addFirst(1);
        assertEquals(1,list.removeLast());
        assertEquals("DLinkList{ }",list.toString());
        list.addFirst(1);
        list.addFirst(2);
        assertEquals(1,list.removeLast());
        assertEquals("DLinkList{ 2 }",list.toString());
    }
}