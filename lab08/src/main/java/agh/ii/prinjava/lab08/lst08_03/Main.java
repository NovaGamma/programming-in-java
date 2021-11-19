package agh.ii.prinjava.lab08.lst08_03;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    private static void demo1() {
        System.out.println("demo1...");
        ISequenceGen dataSrc = new UnsafeSequenceGen();
        testSequenceGen(dataSrc);
    }

    private static void demo2() {
        System.out.println("\ndemo2...");
        ISequenceGen dataSrc = new SafeSequenceGen1();
        testSequenceGen(dataSrc);
    }

    private static void demo3() {
        System.out.println("\ndemo3...");
        ISequenceGen dataSrc = new SafeSequenceGen2();
        testSequenceGen(dataSrc);
    }

    private static void demo4() {
        System.out.println("\ndemo4...");
        ISequenceGen dataSrc = new SafeSequenceGen3();
        testSequenceGen(dataSrc);
    }

    /**
     * A <em>race condition</em> occurs when (at least) two threads try to read and write the same data
     *
     * <p>It is possible that two threads could call {@link UnsafeSequenceGen#getNext getNext} and receive the same value!
     */
    private static void testSequenceGen(ISequenceGen dataSrc) {
        Collection<Integer> dataSink = Collections.synchronizedList(new ArrayList<>());

        Runnable seqReader = () -> {
            for (int i = 0; i < 1000; i++) {
                dataSink.add(dataSrc.getNext());
            }
        };

        Thread t1 = new Thread(seqReader);
        Thread t2 = new Thread(seqReader);
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Map<Integer, Long> hist = dataSink.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));

        hist.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .forEach(e -> System.out.println(e.getKey() + " => " + e.getValue()));
    }

    private static void demo5() {
        System.out.println("\ndemo5...");
        DeadlockDemo dld = new DeadlockDemo();

        Runnable r1 = () -> {
            while (true) {
                dld.m1();
            }
        };

        Runnable r2 = () -> {
            while (true) {
                dld.m2();
            }
        };

        Thread t1 = new Thread(r1, "t1");
        Thread t2 = new Thread(r2, "t2");

        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
        demo4();
        //demo5(); // <- deadlock !
    }
}

