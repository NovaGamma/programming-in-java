package agh.ii.prinjava.lab05.lst05_06;

import java.util.*;

/**
 * A map is a container object that stores a collection of key/value pairs (a map cannot contain duplicate keys).
 * <p>You (typically) create a map using one of its three concrete classes (see also {@link EnumMap}):
 * <ul>
 *     <li>{@link HashMap}: a hash table based implementation of the {@link Map} interface.
 *     This class makes no guarantees as to the order of the map; in particular, it does not guarantee that the order
 *     will remain constant over time.
 *     This implementation provides constant-time performance for the basic operations (get and put),
 *     assuming the hash function disperses the elements properly among the buckets.
 *     Iteration over collection views requires time proportional to the "capacity" of the HashMap instance
 *     (the number of buckets) plus its size (the number of key-value mappings).
 *     Thus, it's very important not to set the initial capacity too high (or the load factor too low)
 *     if iteration performance is important.</li>
 *     <li>{@link LinkedHashMap}: a hash table and linked list implementation of the {@link Map} interface,
 *     with predictable iteration order. This implementation differs from HashMap in that it maintains
 *     a doubly-linked list running through all of its entries.
 *     This linked list defines the iteration ordering, which is normally the order in which keys were inserted
 *     into the map (insertion-order). Note that insertion order is not affected if a key is re-inserted into the map.
 *     A special constructor is provided to create a linked hash map whose order of iteration is the order in which
 *     its entries were last accessed, from least-recently accessed to most-recently (access-order).
 *     This kind of map is well-suited to building LRU caches.</li>
 *     <li>{@link TreeMap}: a Red-Black tree based {@link NavigableMap} implementation.
 *     The map is sorted according to the natural ordering of its keys, or by a {@link Comparator} provided
 *     at map creation time, depending on which constructor is used.
 *     This implementation provides guaranteed log(n) time cost for the containsKey, get, put and remove operations.
 *     Note that the ordering maintained by a tree map, like any sorted map, and whether or not an explicit comparator
 *     is provided, must be consistent with equals if this sorted map is to correctly implement the Map interface.
 *     This is so because the Map interface is defined in terms of the equals operation, but a sorted map performs
 *     all key comparisons using its compareTo (or compare) method, so two keys that are deemed equal
 *     by this method are, from the standpoint of the sorted map, equal.</li>
 * </ul>
 * <p>
 *  {@link EnumMap}: a specialized Map implementation for use with enum type keys. All of the keys in an enum map
 *  must come from a single enum type that is specified, explicitly or implicitly, when the map is created.
 *  Enum maps are represented internally as arrays (which is extremely compact and efficient) and are maintained
 *  in the natural order of their keys (the order in which the enum constants are declared).
 *
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/HashMap.html">HashMap</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/LinkedHashMap.html">LinkedHashMap</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/TreeMap.html">TreeMap</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/EnumMap.html">EnumMap</a>
 */
public class Main {

    private static void fillTheMap(Map<String, Integer> m) {
        m.put("this", 10);
        m.put("super", 2);
        m.put("void", 17);
        m.put("boolean", 5);
        m.put("return", 21);
    }

    /**
     * Creating map instances, and iterating through their elements (no iterator -> no enhanced for-loop)
     */
    private static void demo1() {
        System.out.println("demo1...");

        Map<String, Integer> lhm = new LinkedHashMap<>();
        fillTheMap(lhm);
        System.out.println("lhm: " + lhm);

        Map<String, Integer> hm = new HashMap<>();
        fillTheMap(hm);
        System.out.println("hm: " + hm);

        Map<String, Integer> tm = new TreeMap<>(lhm); // or: Map<String, Integer> tm = new TreeMap<>(); tm.putAll(hm);
        System.out.println("tm: " + tm);

        System.out.println("---\nElements of lhm:");
        lhm.forEach((k, v) -> System.out.println(k + " -> " + v));

        System.out.println("---\nElements of hm:");
        hm.forEach((k, v) -> System.out.println(k + " -> " + v));

        System.out.println("---\nElements of tm:");
        tm.forEach((k, v) -> System.out.println(k + " -> " + v));

        System.out.println("---\nElements of lhm (via keySet()):");
        for (var k : lhm.keySet()) {
            System.out.println(k + " -> " + lhm.get(k));
        }

        System.out.println("---\nElements of tm (via entrySet()):");
        for (var kv : tm.entrySet()) {
            System.out.println(kv.getKey() + " -> " + kv.getValue());
        }
    }

    /**
     * Basic map operations
     */
    private static void demo2() {
        System.out.println("\ndemo2...");
        Map<String, Integer> hm = new HashMap<>();
        hm.put("if", 10);
        hm.put("else", 6);
        hm.put("while", 4);
        hm.put("do", 2);
        System.out.println("hm = " + hm + ", size of hm: " + hm.size());

        System.out.println("---\ncalling 'hm.put(\"if\", 11)'");
        hm.put("if", 11);
        System.out.println("hm = " + hm);

        System.out.println("---\ncalling 'hm.replace(\"if\", 12)'");
        hm.replace("if", 12);
        System.out.println("hm = " + hm);

        System.out.println("---\ncalling 'hm.remove(\"do\")'");
        hm.remove("do");
        System.out.println("hm = " + hm);

        System.out.println("Is 'if' (key) in hm?: " + hm.containsKey("if"));
        System.out.println("Is 'repeat' (key) in hm?: " + hm.containsKey("repeat"));

        System.out.println("---\nValues stored in hm: " + hm.values());
        System.out.println("---\nIs value 6 in hm?: " + hm.containsValue(6));

        System.out.println("---\ncalling 'hm.clear()'");
        hm.clear();
        System.out.println("hm = " + hm);
    }

    /**
     * HashMap vs. TreeMap with {@link C1} keys
     */
    private static void demo3() {
        System.out.println("\ndemo3...");
        Map<C1, Integer> hm = new HashMap<>();
        C1 c11 = new C1("class");
        hm.put(c11, 5);
        hm.put(new C1("interface"), 10);
        hm.forEach((k, v) -> System.out.println(k + " -> " + v));

        System.out.println("Is 'C1(\"class\") (key) in hm?: " + hm.containsKey(c11));
        System.out.println("Checking the same in a different way gives " + hm.containsKey(new C1("class")));

        System.out.println("---\nThe same for TreeMap...");
        Map<C1, Integer> tm = new TreeMap<>();
        tm.put(c11, 6);
        tm.put(new C1("interface"), 11);
        tm.forEach((k, v) -> System.out.println(k + " -> " + v));

        System.out.println("Is 'C1(\"class\") (key) in hm?: " + tm.containsKey(c11));
        System.out.println("Checking the same in a different way gives " + tm.containsKey(new C1("class"))); // Why?
    }

    /**
     * EnumMap brief demo
     *
     * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/EnumMap.html">EnumMap</a>
     */
    private static void demo4() {
        System.out.println("\ndemo4...");
        Map<Day, Integer> em = new EnumMap<>(Day.class);
        System.out.println("Initial em.size() = " + em.size());

        em.put(Day.FRIDAY, 11);
        em.put(Day.WEDNESDAY, 7);
        em.put(Day.MONDAY, 9);
        em.forEach((k, v) -> System.out.println(k + " -> " + v));

        System.out.println("---\nReplacing value for WEDNESDAY ...");
        em.replace(Day.WEDNESDAY, 5);
        em.forEach((k, v) -> System.out.println(k + " -> " + v));
    }

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
        demo4();
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

enum Day {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY}
