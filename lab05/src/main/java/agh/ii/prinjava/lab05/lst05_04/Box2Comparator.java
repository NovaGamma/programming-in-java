package agh.ii.prinjava.lab05.lst05_04;

import java.util.Comparator;

/**
 * Generic comparator for {@code Box2<T>}
 */
class Box2Comparator<T extends Comparable<T>> implements Comparator<Box2<T>> {
    @Override
    public int compare(Box2<T> o1, Box2<T> o2) {
        return o1.e().compareTo(o2.e());
    }
}
