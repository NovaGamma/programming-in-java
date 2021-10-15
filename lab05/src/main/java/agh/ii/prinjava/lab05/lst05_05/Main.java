package agh.ii.prinjava.lab05.lst05_05;

import java.util.*;

/**
 * A set is an efficient data structure for storing and processing non-duplicate elements.
 * <p>You (typically) create a set using one of its three concrete classes (see also {@link EnumSet}):
 * <ul>
 *     <li>{@link HashSet}: implements the {@link Set} interface, backed by a hash table
 *     (actually a {@link HashMap} instance).
 *     It makes no guarantees as to the iteration order of the set; in particular, it does not guarantee
 *     that the order will remain constant over time.
 *     HashSet offers constant time performance for the basic operations (add, remove, contains and size),
 *     assuming the hash function disperses the elements properly among the buckets.
 *     Iterating over this set requires time proportional to the sum of the HashSet instance's size
 *     (the number of elements) plus the "capacity" of the backing HashMap instance (the number of buckets).
 *     Thus, it's very important not to set the initial capacity too high (or the load factor too low)
 *     if iteration performance is important.</li>
 *
 *     <li>{@link LinkedHashSet}: Hash table and linked list implementation of the {@link Set} interface,
 *     with predictable iteration order. This implementation maintains a doubly-linked list which defines
 *     the iteration ordering, which is the order in which elements were inserted into the set (insertion-order).
 *     It provides constant-time performance for the basic operations (add, contains and remove),
 *     assuming the hash function disperses elements properly among the buckets.
 *     Performance is likely to be just slightly below that of HashSet, due to the added expense of maintaining
 *     the linked list, with one exception: Iteration over a LinkedHashSet requires time proportional
 *     to the size of the set, regardless of its capacity. Iteration over a HashSet is likely to be more expensive,
 *     requiring time proportional to its capacity.
 *
 *     </li>
 *     <li>{@link TreeSet}: a {@link NavigableSet} implementation based on a {@link TreeMap}.
 *     The elements are ordered using their natural ordering, or by a {@link Comparator} provided at set creation time,
 *     depending on which constructor is used.
 *     This implementation provides guaranteed log(n) time cost for the basic operations (add, remove and contains).
 *     Note that the ordering maintained by a set (whether or not an explicit comparator is provided)
 *     must be consistent with equals if it is to correctly implement the Set interface.</li>
 * </ul>
 * <p>
 * {@link EnumSet}: a specialized {@link Set} implementation for use with enum types. All the elements in an enum set
 * must come from a single enum type that is specified, explicitly or implicitly, when the set is created.
 * Enum sets are represented internally as bit vectors. This representation is extremely compact and efficient.
 * The space and time performance of this class should be good enough to allow its use as a high-quality,
 * typesafe alternative to traditional int-based "bit flags".
 * All basic operations execute in constant time. They are likely (though not guaranteed) to be much faster than
 * their HashSet counterparts. Even bulk operations execute in constant time if their argument is also an enum set.
 *
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/HashSet.html">HashSet</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/LinkedHashSet.html">LinkedHashSet</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/TreeSet.html">TreeSet</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/EnumSet.html">EnumSet</a>
 */
public class Main {
    private static final List<Integer> INT_LIST1 = List.of(20, 1000, 30, 400, 200, 1000, 30, 400);
    private static final List<String> STR_LIST1 = List.of("Emma", "Jade", "Louise", "Alice", "Lina");

    /**
     * Creating set instances, and iterating through their elements
     */
    private static void demo1() {
        System.out.println("demo1...");
        Set<Integer> hs = new HashSet<>(INT_LIST1);
        Set<Integer> lhs = new LinkedHashSet<>(INT_LIST1);
        Set<Integer> ts = new TreeSet<>(INT_LIST1);

        System.out.println("Initializer list: " + INT_LIST1);
        System.out.println("as a HashSet: " + hs);
        System.out.println("as a LinkedHashSet: " + lhs);
        System.out.println("as a TreeSet: " + ts);

        System.out.println("Elements of hs:");
        // (1) using iterator
        for (var it = hs.iterator(); it.hasNext(); ) {
            System.out.println(it.next());
        }

        System.out.println("---\nElements of lhs:");
        // (2) enhanced loop (for-each loop)
        for (var e: lhs) {
            System.out.println(e);
        }

        System.out.println("---\nElements of ts:");
        // (3) forEach method
        ts.forEach(System.out::println);
    }

    /**
     * Basic set operations
     */
    private static void demo2() {
        System.out.println("\ndemo2...");
        Set<String> hs1 = new HashSet<>(Set.of("A", "B", "C"));
        Set<String> hs2 = new HashSet<>(List.of("B", "C", "D", "E"));
        Set<String> hs3 = new HashSet<>(List.of("D", "E", "F"));
        System.out.println("hs1: " + hs1 + ", size: " + hs1.size());
        System.out.println("hs2: " + hs2 + ", size: " + hs2.size());
        System.out.println("hs3: " + hs3 + ", size: " + hs3.size());

        System.out.println("---");
        System.out.println("Is A in hs1? " + hs1.contains("A"));
        System.out.println("Is A in hs2? " + hs2.contains("A"));

        hs1.addAll(hs2);
        System.out.println("After adding hs2 to hs1, hs1: " + hs1);

        hs1.removeAll(hs2);
        System.out.println("After removing hs2 to hs1, hs1: " + hs1);

        hs2.retainAll(hs3);
        System.out.println("After retaining common elements in hs2 and hs3, hs2: " + hs2);
    }

    /**
     * Note:
     * <ul>
     *     <li>{@link C0} does NOT override {@link #equals} nor {@link #hashCode}</li>
     *     <li>{@link C1} overrides {@link #equals} but does not override {@link #hashCode}</li>
     * </ul>
     */
    private static void demo3() {
        System.out.println("\ndemo3...");
        Set<C0> hs = new HashSet<>();
        Set<C0> lhs = new LinkedHashSet<>();
        //Set<C0> ts = new TreeSet<>(); // Warning: Construction of sorted collection with non-comparable elements
        for (var e : STR_LIST1) {
            hs.add(new C0(e));
            lhs.add(new C0(e));
            //ts.add(new C0(e)); // <- ClassCastException: C0 cannot be cast to class Comparable (compare method)
        }

        System.out.println("Initializer list: " + STR_LIST1);
        System.out.println("as a HashSet: " + hs);
        System.out.println("as a LinkedHashSet: " + lhs);

        System.out.println("---");
        hs.add(new C0("Rose"));
        hs.add(new C0("Rose"));
        System.out.println(hs); // !!! "Jade", "Lina", "Emma", "Louise", "ROSE", "ROSE", "Alice"

        System.out.println("---");
        lhs.add(new C0("Rose"));
        lhs.add(new C0("Rose"));
        System.out.println(lhs); // !!! "Emma", "Jade", "Louise", "Alice", "Lina", "Rose", "Rose"

        System.out.println("---");
        String name = "Julia";
        C0 c01 = new C0(name);
        hs.add(c01);
        System.out.println("hs = " + hs);
        System.out.println("Is C0(" + name + ") in hs? " + hs.contains(c01));
        System.out.println("Checking the same in a different way gives " + hs.contains(new C0(name))); // !!!
        System.out.println("Is C0(Rose) in hs? " + hs.contains(new C0("Rose"))); // !!!
    }

    /**
     * HashSet of {@link C1} vs. HashSet of {@link C2}
     */
    private static void demo4() {
        System.out.println("\ndemo4...");
        Set<C1> hs1 = new HashSet<>();
        for (var e : STR_LIST1) {
            hs1.add(new C1(e));
        }
        hs1.add(new C1("Rose"));
        hs1.add(new C1("Rose"));
        System.out.println("hs1 (of C1) = " + hs1);

        System.out.println("---");
        Set<C2> hs2 = new HashSet<>();
        for (var e : STR_LIST1) {
            hs2.add(new C2(e));
        }
        hs2.add(new C2("Rose"));
        hs2.add(new C2("Rose"));
        System.out.println("hs2 (of C2) = " + hs2);
    }

    /**
     * TreeSet with {@link C1} (comparable, hashCode not overridden)
     */
    private static void demo5() {
        System.out.println("\ndemo5...");
        Set<C1> ts = new TreeSet<>();
        for (var e : STR_LIST1) {
            ts.add(new C1(e));
        }
        ts.add(new C1("Rose"));
        ts.add(new C1("Rose"));
        System.out.println("ts (of C1) = " + ts);
        System.out.println("Is C1(Rose) in ts? " + ts.contains(new C1("Rose"))); // it works for a tree set, why?
    }

    /**
     * TreeSet with C0 + {@link Comparator}
     */
    private static void demo6() {
        System.out.println("\ndemo6...");
        //Set<C0> ts = new TreeSet<>((o1, o2) -> o1.getS().compareTo(o2.getS())); // (**)
        Set<C0> ts = new TreeSet<>(Comparator.comparing(C0::getS)); // equivalent to (**)
        for (var e : STR_LIST1) {
            ts.add(new C0(e));
        }
        System.out.println("as a TreeSet: " + ts);
    }

    /**
     * Selected treeSet-specific methods
     */
    private static void demo7() {
        System.out.println("\ndemo7...");
        TreeSet<Integer> ts = new TreeSet<>();
        for (var e : INT_LIST1) {
            ts.add(e);
        }

        System.out.println("Initializer list: " + INT_LIST1);
        System.out.println("as a TreeSet: " + ts);
        System.out.println("ts.first(): " + ts.first());
        System.out.println("ts.last(): " + ts.last());
        System.out.println("ts.headSet(30): " + ts.headSet(30));
        System.out.println("ts.tailSet(200): " + ts.tailSet(200));
    }

    /**
     * EnumSet brief demo
     * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/EnumSet.html">EnumSet</a>
     */
    private static void demo8() {
        System.out.println("\ndemo8...");
        //EnumSet<DAY> es = new EnumSet<>(); // 'EnumSet' is abstract; cannot be instantiated
        EnumSet<Day> days = EnumSet.allOf(Day.class);
        System.out.println("days: " + days);

        EnumSet<Day> weekDays = EnumSet.range(Day.MONDAY, Day.FRIDAY);
        System.out.println("weekDays: " + weekDays);

        EnumSet<Day> weekend = EnumSet.complementOf(weekDays);
        System.out.println("weekend: " + weekend);

        System.out.println("weekDays:");
        weekDays.forEach(System.out::println);
    }

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
        demo4();
        demo5();
        demo6();
        demo7();
        demo8();
    }
}

/**
 * <p>{@link #equals}, {@link #hashCode} NOT overridden
 */
final class C0 {
    private final String s;

    public C0(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }

    @Override
    public String toString() {
        return "C0{" + "s='" + s + '\'' + '}';
    }
}

/**
 * <ul>
 *     <li>{@link #equals} overridden, {@link #hashCode} NOT overridden</li>
 *     <li>{@link Comparable} implemented</li>
 * </ul>
 */
final class C1 implements Comparable<C1> {
    private final String s;

    public C1(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        C1 c1 = (C1) o;
        return Objects.equals(s, c1.s);
    }

    @Override
    public String toString() {
        return "C1{" + "s='" + s + '\'' + '}';
    }

    @Override
    public int compareTo(C1 o) {
        return s.compareTo(o.s);
    }
}

/**
 * <ul>
 *     <li>{@link #equals}, {@link #hashCode} overridden</li>
 *     <li>{@link Comparable} NOT implemented</li>
 * </ul>
 */
record C2(String s) {
}

enum Day { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }
