package agh.ii.prinjava.lab05.lst05_02;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * For any object {@code obj} of a class that implements the {@link Iterable} interface,
 * the enhanced for loop is translated to the form:
 * <pre>
 * Iterator<...> iter = obj.iterator();
 * while (iter.hasNext()) {
 *   ... e = iter.next();
 * }
 * </pre>
 *
 * <p>Explicit use of iterators is not recommended in the modern Java. Use the enhanced for-loop instead, i.e.
 * <pre>
 * for (var e : obj) {
 *   // process element
 * }
 * </pre>
 *
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Iterator.html">Iterator</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Iterable.html">Iterable</a>
 */
class IterableArray<E> implements Iterable<E> {
    private final Object[] elems;
    private final int size;

    public IterableArray(int size) {
        this.size = size;
        this.elems = new Object[size];
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException();
        return (E) elems[index];
    }
    public void set(int index, E elem) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException();
        elems[index] = elem;
    }

    @Override
    public String toString() {
        return "IterableArray{" + "elems=" + Arrays.toString(elems) + ", size=" + size + '}';
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Iterable.super.forEach(action);
    }

    /** Iterator as a nested non-static private class */
    private class ArrayIterator implements Iterator<E> {
        private int i = 0;

        public ArrayIterator() {
            System.out.println("New iterator is being created...");
        }

        @Override
        public boolean hasNext() {
            return i < size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            return (E) elems[i++];
        }
    }
}

public class Main {

    /** Using iterators */
    private static void demo1() {
        int itArrSize = 5;
        IterableArray<Integer> itArr = new IterableArray<>(itArrSize);
        for (int i = 0; i < itArrSize; i++) {
            itArr.set(i, 10 * i);
        }

        System.out.println(itArr);

        // (1) Iterator with the while loop
        var it1 = itArr.iterator();
        while (it1.hasNext()) {
            int e = it1.next();
            System.out.print(e + " ");
        }
        System.out.println("\n---");

        // (2) Iterator with the for loop
        for (var it = itArr.iterator(); it.hasNext(); ) {
            int e = it.next();
            System.out.print(e + " ");
        }
        System.out.println("\n---");

        // (3) Enhanced for-loop
        for (var e : itArr) {
            System.out.print(e + " ");
        }
        System.out.println("\n---");

        // (4) forEach method
        itArr.forEach(e -> System.out.print(e + " "));
    }

    public static void main(String[] args) {
        demo1();
    }
}
