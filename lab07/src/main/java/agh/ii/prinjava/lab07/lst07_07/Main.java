package agh.ii.prinjava.lab07.lst07_07;

import agh.ii.prinjava.lab07.dal.ImdbTop250;
import agh.ii.prinjava.lab07.model.Movie;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Playing with the IMDB Top 250
 */
public class Main {
    /**
     * Mapping + simple collectors (toList, toArray)
     */
    private static void demo1() {
        System.out.println("\ndemo1...");
        final Optional<List<Movie>> movies = ImdbTop250.movies();

        final List<String> top10Movies = movies.orElseThrow().stream()
                .limit(10)
                .map(m -> m.rank() + ". " + m.title() + ", " + m.rating())
                .toList();
        top10Movies.forEach(System.out::println);

        System.out.println("---");
        final double[] top10Ratings = movies.orElseThrow().stream()
                .limit(10)
                .mapToDouble(Movie::rating) // Stream -> DoubleStream
                .toArray();
        System.out.println(Arrays.toString(top10Ratings));

        System.out.println("---");
        final int[] top10Years = movies.orElseThrow().stream()
                .limit(10)
                .mapToInt(Movie::year) // Stream -> IntStream
                .toArray();
        System.out.println(Arrays.toString(top10Years));
    }

    /**
     * The number of movies for each year
     */
    private static void demo2() {
        System.out.println("\ndemo2...");
        final Optional<List<Movie>> optMovies = ImdbTop250.movies();
        optMovies.orElseThrow().stream()
                .collect(
                        Collectors.groupingBy(
                                Movie::year,
                                Collectors.counting()
                        )
                )
                .forEach((k, v) -> System.out.println(k + " => " + v));
    }

    /**
     * The number of movies for each director (directors)
     */
    private static void demo3() {
        System.out.println("\ndemo3...");
        final Optional<List<Movie>> optMovies = ImdbTop250.movies();
        optMovies.orElseThrow().stream()
                .collect(
                        Collectors.groupingBy(
                                Movie::director,
                                Collectors.counting()
                        )
                )
                .forEach((k, v) -> System.out.println(k + " => " + v));
    }

    /**
     * The longest movies
     */
    private static void demo4() {
        System.out.println("\ndemo4...");
        final Optional<List<Movie>> optMovies = ImdbTop250.movies();
        final Object[] theLongestMovies = optMovies.orElseThrow().stream()
                .filter(m -> m.runtime() > 180)
                .sorted((m1, m2) -> Integer.compare(m2.runtime(), m1.runtime()))
                .toArray();

        for (int i = 0; i < theLongestMovies.length; i++) {
            Movie m = (Movie) theLongestMovies[i];
            System.out.println(i + 1 + ". " + m.title() + " (" + m.runtime() + " minutes)");
        }
    }

    /**
     * Total number of directors
     */
    private static void demo5() {
        System.out.println("\ndemo5...");
        final Optional<List<Movie>> optMovies = ImdbTop250.movies();
        long numOfDirectors = optMovies.orElseThrow().stream()
                .flatMap(m -> Arrays.stream(m.director().split(",")))
                .collect(Collectors.toSet())
                .size();
        System.out.println("Total number of directors: " + numOfDirectors);
    }

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
        demo4();
        demo5();
    }
}
