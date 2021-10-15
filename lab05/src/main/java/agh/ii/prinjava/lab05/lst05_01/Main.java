package agh.ii.prinjava.lab05.lst05_01;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>The {@link List} interface extends the {@link Collection} interface -
 * defines a collection for storing elements in a sequential order (with duplicates allowed);
 * each element of a list has an integer index
 * <p>To create a list, use one of its two concrete classes: {@link ArrayList} or {@link LinkedList}.
 *
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Collection.html">Collection</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/List.html">List</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/ArrayList.html">ArrayList</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/LinkedList.html">LinkedList</a>
 */
public class Main {
    private static final List<Integer> LIST_10_TO_40 = List.of(10, 20, 30, 40); // since Java 9
    private static final int TEST_LIST_SIZE = 50_000;

    /**
     * Auxiliary method 1: some operations on a list
     */
    private static void listMods(List<Integer> lst) {
        System.out.println("l1.get(1) = " + lst.get(1));

        lst.add(1, -20);
        lst.set(2, -30);
        System.out.println("l1 = " + lst);

        lst.remove(1);
        lst.remove(Integer.valueOf(-30));
        System.out.println("l1 = " + lst);
    }

    /**
     * Auxiliary method 2: time duration of adding elements at the end of a list
     */
    private static long dtOfAddingAtTheEnd(List<Integer> lst) {
        long t0 = System.currentTimeMillis();
        for (int i = 0; i < TEST_LIST_SIZE; i++) {
            lst.add(i);
        }
        return System.currentTimeMillis() - t0;
    }

    /**
     * Auxiliary method 3: time duration of adding elements at the front of a list
     */
    private static long dtOfAddingAtTheFront(List<Integer> lst) {
        long t0 = System.currentTimeMillis();
        for (int i = 0; i < TEST_LIST_SIZE; i++) {
            lst.add(0, i);
        }
        return System.currentTimeMillis() - t0;
    }

    /**
     * Auxiliary method 4: time duration of accessing list elements with "get" method
     */
    private static long dtOfGet(List<Integer> lst) {
        long sum = 0;
        long t0 = System.currentTimeMillis();
        for (int i = 0; i < TEST_LIST_SIZE; i++) {
            sum += lst.get(i);
        }
        return System.currentTimeMillis() - t0;
    }

    /**
     * ArrayList demo 1 (ArrayList is a resizable-array implementation of the List interface)
     */
    private static void demo1() {
        System.out.println("demo1...");
        final int[] ints = {10, 20, 30, 40};
        List<Integer> l1 = new ArrayList<>(4); // initialCapacity, see also trimToSize
        for (var e : ints) {
            l1.add(e);
        }
        System.out.println("array ints = " + ints); // compare the output to the following
        System.out.println("l1 = " + l1);
        System.out.println("l1.size() = " + l1.size());
    }

    /**
     * ArrayList demo 2
     */
    private static void demo2() {
        System.out.println("\ndemo2...");
        List<Integer> l1 = new ArrayList<>(LIST_10_TO_40); // ArrayList(c: Collection<? extends E>)
        System.out.println("l1 = " + l1);

        listMods(l1);
    }

    /**
     * LinkedList demo 1 (LinkedList is a linked-list implementation of the List interface)
     */
    private static void demo3() {
        System.out.println("\ndemo3...");
        List<Integer> l1 = new LinkedList<>(LIST_10_TO_40); // LinkedList(c: Collection<? extends E>)
        System.out.println("l1 = " + l1);

        listMods(l1);
    }

    /**
     * LinkedList demo 2
     */
    private static void demo4() {
        System.out.println("\ndemo4...");
        LinkedList<Integer> l1 = new LinkedList<>(LIST_10_TO_40);
        System.out.println("l1 = " + l1);

        l1.add(50);
        l1.addFirst(0);
        l1.addLast(60);
        System.out.println("l1 = " + l1);

        l1.removeFirst();
        l1.removeLast();
        System.out.println("l1 = " + l1);
    }

    /**
     * ArrayList vs LinkedList performance 1 (adding at the end)
     */
    private static void demo5() {
        System.out.println("\ndemo5...");
        List<Integer> alst = new ArrayList<>();
        List<Integer> llst = new LinkedList<>();

        long dtAL = dtOfAddingAtTheEnd(alst);
        long dtLL = dtOfAddingAtTheEnd(llst);

        double relDT = (double) dtAL / dtLL;
        System.out.format("ArrayList/LinkedList DT ratio (adding at the end): %.4f\n", relDT);
    }

    /**
     * ArrayList vs LinkedList performance 2 (adding at the front)
     */
    private static void demo6() {
        System.out.println("\ndemo6...");
        List<Integer> alst = new ArrayList<>();
        List<Integer> llst = new LinkedList<>();

        long dtAL = dtOfAddingAtTheFront(alst);
        long dtLL = dtOfAddingAtTheFront(llst);

        double relDT = (double) dtAL / dtLL;
        System.out.format("ArrayList/LinkedList DT ratio (adding at the front): %.4f\n", relDT);
    }

    /**
     * ArrayList vs LinkedList performance 2 (list.get(i))
     */
    private static void demo7() {
        System.out.println("\ndemo7...");
        List<Integer> alst = new ArrayList<>();
        List<Integer> llst = new LinkedList<>();
        for (int i = 0; i < TEST_LIST_SIZE; i++) {
            alst.add(i);
            llst.add(i);
        }

        long dtAL = dtOfGet(alst);
        long dtLL = dtOfGet(llst);

        double relDT = (double) dtAL / dtLL;
        System.out.format("ArrayList/LinkedList DT ratio (lst.get(i)): %.4f\n", relDT);
    }

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
        demo4();
        demo5();
        demo6();
        demo7();
    }
}
