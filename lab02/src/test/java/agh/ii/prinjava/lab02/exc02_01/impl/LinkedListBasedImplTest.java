package agh.ii.prinjava.lab02.exc02_01.impl;

import agh.ii.prinjava.lab02.exc02_01.StackOfInts;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LinkedListBasedImplTest {

    // Create an empty stack
    private final StackOfInts stackOfInts = StackOfIntsFtry.create(StackOfIntsFtry.Impln.L_LIST_B);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void newStackIsEmpty() {
        assertTrue(stackOfInts.isEmpty());
    }

    @Test
    void peek_and_push() {
        for (int i = 0; i<100; i++) {
            stackOfInts.push(i);

            assertEquals(i, stackOfInts.peek());
        }
    }

    @Test
    void pop() {
        stackOfInts.push(1);
        stackOfInts.push(2);

        assertEquals(1, stackOfInts.pop());
        assertEquals(2, stackOfInts.pop());
    }
}