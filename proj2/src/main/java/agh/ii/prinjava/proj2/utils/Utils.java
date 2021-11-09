package agh.ii.prinjava.proj2.utils;

import agh.ii.prinjava.proj2.model.Movie;

import java.util.*;
import java.util.stream.Stream;

public interface Utils {
    /**
     * Fills the map with entries of the form (k_i, v), i = 1, 2, 3,... (many-to-one)
     *
     * @param m     the map to be filled
     * @param keys  the list of keys to be added into the map
     * @param value the value to be added into the map
     * @param <K>   the type of the key
     * @param <V>   the type of the value
     */
    static <K, V> void addKeysWithOneValue(Map<K, List<V>> m, List<K> keys, V value) {
        Objects.requireNonNull(m);
        Objects.requireNonNull(keys);

        for (K key : keys) {
            m.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
        }
    }

    /**
     * Similar to {@link Utils#addKeysWithOneValue addKeysWithOneValue} but written in a more functional style
     * <p>Since {@link Stream#collect} implements a mutable reduction, it is typically is significantly faster
     * than {@link Stream#reduce reduce} (i.e., the classic/immutable reduction)
     */
    static <K, V> Map<K, List<V>> manyToOneMapOf(List<K> keys, V value) {
        Objects.requireNonNull(keys);

        return keys.stream().collect(
                HashMap::new,
                (acc, key) -> acc.computeIfAbsent(key, k -> new ArrayList<>()).add(value),
                Map::putAll
        );
    }

    /**
     * Creates a list of ordered pairs from the given list of strings.
     *
     * @param lst the source of the pairs, e.g., {@code ["C", "A", "B", "D"]}
     * @return list of pairs, e.g.,  {@code ["A, B", "A, C", "A, D", "B, C", "B, D", "C, D"]}
     */
    static List<String> orderedPairsFrom(List<String> lst) {
        Objects.requireNonNull(lst);

        Object[] elems = lst.stream().sorted().toArray();

        String delimiter = ", ";
        int initCapacity = (lst.size() * (lst.size() - 1)) / 2;
        List<String> pairs = new ArrayList<>(initCapacity);
        for (int i = 0; i < elems.length; i++) {
            for (int j = i + 1; j < elems.length; j++) {
                StringBuilder sb = new StringBuilder(3);
                sb.append(elems[i]);
                sb.append(delimiter);
                sb.append(elems[j]);
                pairs.add(sb.toString());
            }
        }

        return pairs;
    }

    static List<Movie> oneToManyByDirector(Movie m) {
        List<Movie> movies = new ArrayList<>(m.directors().size());

        for (var d : m.directors()) {
            Movie mv = new Movie(m.rank(), m.rating(), m.title(), m.year(), m.rated(), m.runtime(),
                    m.genre(), List.of(d), m.actors(), m.production());
            movies.add(mv);
        }

        return movies;
    }

    static List<Movie> oneToManyByActor(Movie m) {
        List<Movie> movies = new ArrayList<>(m.directors().size());

        for (var a : m.actors()) {
            Movie mv = new Movie(m.rank(), m.rating(), m.title(), m.year(), m.rated(), m.runtime(),
                    m.genre(), m.directors(), List.of(a), m.production());
            movies.add(mv);
        }

        return movies;
    }

    static List<Movie> oneToManyByActorDuo(Movie m) {
        List<Movie> movies = new ArrayList<>(m.directors().size());

        for (var a : orderedPairsFrom(m.actors())) {
            Movie mv = new Movie(m.rank(), m.rating(), m.title(), m.year(), m.rated(), m.runtime(),
                    m.genre(), m.directors(), List.of(a), m.production());
            movies.add(mv);
        }

        return movies;
    }
}

