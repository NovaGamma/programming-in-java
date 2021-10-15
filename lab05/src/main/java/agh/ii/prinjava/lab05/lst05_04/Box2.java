package agh.ii.prinjava.lab05.lst05_04;

import java.util.Comparator;
import java.util.Objects;

/**
 * Note {@link Box2} does not implement {@link Comparable}
 * <p>The use of {@link Comparator} will be necessary in some scenarios (e.g. sorting)
 */
record Box2<T>(T e) {
    @Override
    public int hashCode() {
        return Objects.hash(e);
    }
}
