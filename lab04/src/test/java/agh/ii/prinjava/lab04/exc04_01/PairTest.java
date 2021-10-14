package agh.ii.prinjava.lab04.exc04_01;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PairTest {

    Pair<Integer, String> pairOfIntStr;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void t1() {
        assertTrue(true);
    }

    @Test
    void t2(){
        Integer fst = 1;
        String snd = "a";
        Pair<Integer, String> pair = new Pair<Integer, String>(fst, snd);
        assertTrue(pair.getFst() == 1 && pair.getSnd() == "a");
    }

    @Test
    void testClone() throws CloneNotSupportedException {
        Integer fst = 1;
        String snd = "a";
        Pair<Integer, String> pair = new Pair<Integer, String>(fst, snd);
        assertTrue(pair.equals(pair.clone()));
    }
}