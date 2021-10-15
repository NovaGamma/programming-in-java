package agh.ii.prinjava.lab05.lst05_07;

import java.util.*;

/**
 * {@link Collections} : consists exclusively of static methods that operate on or return collections.
 * It contains polymorphic algorithms that operate on collections, "wrappers", which return a new collection
 * backed by a specified collection.
 * <p>{@link Arrays}: contains various methods for manipulating arrays (such as sorting and searching).
 * This class also contains a static factory that allows arrays to be viewed as lists.
 *
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Collections.html">Collections</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Arrays.html">Arrays</a>
 */
public class Main {
    private static final int[] INT_ARRAY = {3, 1, 9, 2, 4, 8, 5, 7, 6};
    private static final List<Integer> INT_LIST = List.of(3, 1, 9, 2, 4, 8, 5, 7, 6);

    /**
     * Shuffling (permutations of) lists
     */
    private static void demo1() {
        System.out.println("demo1...");
        List<Integer> l1 = new ArrayList<>(INT_LIST);
        System.out.println("l1 = " + l1);
        System.out.println("Shuffling l1...");
        Collections.shuffle(l1);
        System.out.println("l1 = " + l1);

        System.out.println("Shuffling l1 once again...");
        Collections.shuffle(l1);
        System.out.println("l1 = " + l1);
    }

    /**
     * Sorting lists and arrays
     */
    private static void demo2() {
        System.out.println("\ndemo2...");
        List<Integer> l1 = new ArrayList<>(INT_LIST);
        System.out.println("l1 = " + l1);
        System.out.println("sorting l1...");
        Collections.sort(l1);
        System.out.println("l1 = " + l1);

        int[] a1 = Arrays.copyOf(INT_ARRAY, INT_ARRAY.length);
        System.out.println("---\na1 = " + Arrays.toString(a1));
        System.out.println("sorting a1...");
        Arrays.sort(a1);
        System.out.println("a1 = " + Arrays.toString(a1));
    }

    /**
     * Binary search works ONLY for sorted collections (and arrays)
     */
    private static void demo3() {
        System.out.println("\ndemo3...");
        List<Integer> l1 = new ArrayList<>(INT_LIST);
        System.out.println("l1 = " + l1);
        int k = Collections.binarySearch(l1, 9);
        System.out.println("9 has been found at idx: " + k); // <- !!!

        System.out.println("---\nsorting l1...");
        Collections.sort(l1);
        System.out.println("l1 = " + l1);
        k = Collections.binarySearch(l1, 9);
        System.out.println("9 has been found at idx: " + k);

        int[] a1 = Arrays.copyOf(INT_ARRAY, INT_ARRAY.length);
        System.out.println("---\na1 = " + Arrays.toString(a1));
        k = Arrays.binarySearch(a1, 9);
        System.out.println("9 has been found at idx: " + k); // <- !!!

        System.out.println("---\nsorting a1...");
        Arrays.sort(a1);
        k = Arrays.binarySearch(a1, 9);
        System.out.println("9 has been found at idx: " + k);
    }

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
    }
}
