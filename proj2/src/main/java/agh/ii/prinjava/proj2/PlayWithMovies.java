package agh.ii.prinjava.proj2;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import agh.ii.prinjava.proj2.model.Movie;
import agh.ii.prinjava.proj2.dal.ImdbTop250;
import agh.ii.prinjava.proj2.utils.Utils;

interface PlayWithMovies {

    /**
     * Returns the movies (only titles) directed (or co-directed) by a given director
     */
    static List<String> ex01(String director) {
        final Optional<List<Movie>> optMovies = ImdbTop250.movies();
        return optMovies.orElseThrow().stream()
                .filter(m -> m.directors().contains(director))
                .map(Movie::title)
                .toList();
    }

    /**
     * Returns the movies (only titles) in which an actor played
     */
    static List<String> ex02(String actor) {
        final Optional<List<Movie>> optMovies = ImdbTop250.movies();
        return optMovies.orElseThrow().stream()
                .filter(m -> m.actors().contains(actor))
                .map(Movie::title)
                .toList();
    }

    /**
     * Returns the number of movies per director (as a map)
     */
    static Map<String, Long> ex03() {
        final Optional<List<Movie>> optMovies = ImdbTop250.movies();
        return optMovies.orElseThrow().stream()
                .map(Utils::oneToManyByDirector)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(movie -> movie.directors().get(0),Collectors.counting()));
    }

    /**
     * Returns the 10 directors with the most films on the list
     */
    static List<Map.Entry<String, Long>> ex04() {
        final Map<String, Long> directorFilmList = ex03();
        return directorFilmList.entrySet().stream()
                .sorted((e1,e2) -> Long.compare(e2.getValue(),e1.getValue()))
                .limit(10)
                .toList();
    }

    /**
     * Returns the movies (only titles) made by each of the 10 directors found in {@link PlayWithMovies#ex04 ex04}
     */
    static List<Map.Entry<String, List<String>>> ex05() {
        final List<Map.Entry<String, Long>> directors = ex04();
        return directors.stream()
                .map(e -> Map.entry(e.getKey(),ex01(e.getKey())))
                .toList();
    }

    /**
     * Returns the number of movies per actor (as a map)
     */
    static Map<String, Long> ex06() {
        final Optional<List<Movie>> optMovies = ImdbTop250.movies();
        return optMovies.orElseThrow().stream()
                .map(Utils::oneToManyByActor)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(movie -> movie.actors().get(0),Collectors.counting()));
    }

    /**
     * Returns the 9 actors with the most films on the list
     */
    static List<Map.Entry<String, Long>> ex07() {
        final Map<String, Long> actorFilmList = ex06();
        return actorFilmList.entrySet().stream()
                .sorted((e1,e2) -> Long.compare(e2.getValue(),e1.getValue()))
                .limit(9)
                .toList();
    }

    /**
     * Returns the movies (only titles) of each of the 9 actors from {@link PlayWithMovies#ex07 ex07}
     */
    static List<Map.Entry<String, List<String>>> ex08() {
        final List<Map.Entry<String, Long>> actors = ex07();
        return actors.stream()
                .map(e -> Map.entry(e.getKey(),ex02(e.getKey())))
                .toList();
    }

    /**
     * Returns the 5 most frequent actor partnerships (i.e., appearing together most often)
     */
    static List<Map.Entry<String, Long>> ex09() {
        final Optional<List<Movie>> optMovies = ImdbTop250.movies();
        return optMovies.orElseThrow().stream()
                .map(Utils::oneToManyByActorDuo)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(movie -> movie.actors().get(0),Collectors.counting()))
                .entrySet().stream()
                .sorted((e1,e2) -> Long.compare(e2.getValue(),e1.getValue()))
                .limit(5).toList();
    }

    /**
     * Returns the movies (only titles) of each of the 5 most frequent actor partnerships
     */
    static List<Map.Entry<String, List<String>>> ex10() {
        final List<Map.Entry<String, Long>> actorsDuo = ex09();
        return actorsDuo.stream()
                .map(e -> Map.entry(e.getKey(), intersectionDuo(e.getKey())))
                .toList();
    }

    private static List<String> intersectionDuo(String duo){
        List<String> first = ex02(duo.split(", ")[0]);
        List<String> second = ex02(duo.split(", ")[1]);
        return first.stream().filter(second::contains).collect(Collectors.toList());
    }
}

