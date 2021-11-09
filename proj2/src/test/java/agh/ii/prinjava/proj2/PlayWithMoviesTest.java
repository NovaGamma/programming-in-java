package agh.ii.prinjava.proj2;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PlayWithMoviesTest {

    @Test
    public void testEx01() {
        assertThat(PlayWithMovies.ex01("Guy Ritchie"))
                .containsExactlyInAnyOrder("Snatch", "Lock, Stock and Two Smoking Barrels");

        assertThat(PlayWithMovies.ex01("Christopher Nolan"))
                .containsExactlyInAnyOrder("The Dark Knight", "Inception", "Interstellar",
                        "The Prestige", "Memento", "The Dark Knight Rises", "Batman Begins");

        assertThat(PlayWithMovies.ex01("Quentin Tarantino"))
                .containsExactlyInAnyOrder("Pulp Fiction", "Django Unchained", "Reservoir Dogs",
                        "Inglourious Basterds", "Kill Bill: Vol. 1", "Sin City");
    }

    @Test
    public void testEx02() {
        assertThat(PlayWithMovies.ex02("Tom Hanks"))
                .containsExactlyInAnyOrder("Forrest Gump", "Saving Private Ryan", "The Green Mile",
                        "Toy Story 3", "Toy Story", "Catch Me If You Can");

        assertThat(PlayWithMovies.ex02("Tom Hardy"))
                .containsExactlyInAnyOrder("Inception", "The Dark Knight Rises",
                        "Warrior", "Mad Max: Fury Road", "The Revenant");

        assertThat(PlayWithMovies.ex02("Robert De Niro"))
                .containsExactlyInAnyOrder("The Godfather: Part II", "Goodfellas", "Once Upon a Time in America",
                        "Raging Bull", "Heat", "Casino", "The Deer Hunter");
    }

    @Test
    public void testEx03() {
        Map<String, Long> numOfMoviesPerDirector = PlayWithMovies.ex03();

        assertThat(numOfMoviesPerDirector.size()).isEqualTo(167);
        assertThat(numOfMoviesPerDirector).containsAllEntriesOf(Map.of(
                "Tim Burton", 1L,
                "Guy Ritchie", 2L,
                "Wes Anderson", 1L,
                "Christopher Nolan", 7L,
                "Quentin Tarantino", 6L,
                "Alfred Hitchcock", 9L));
    }

    @Test
    public void testEx04() {
        List<Map.Entry<String, Long>> tenMostFreqDirectors = PlayWithMovies.ex04();

        assertThat(tenMostFreqDirectors).containsSequence(List.of(
                Map.entry("Alfred Hitchcock", 9L),
                Map.entry("Stanley Kubrick", 8L),
                Map.entry("Martin Scorsese", 7L),
                Map.entry("Steven Spielberg", 7L),
                Map.entry("Christopher Nolan", 7L),
                Map.entry("Billy Wilder", 7L),
                Map.entry("Quentin Tarantino", 6L),
                Map.entry("Charles Chaplin", 5L),
                Map.entry("Ridley Scott", 4L),
                Map.entry("Frank Capra", 4L)));
    }

    @Test
    public void testEx05() {
        List<Map.Entry<String, List<String>>> moviesPerDirector = PlayWithMovies.ex05();
        List<String> directors = moviesPerDirector.stream()
                .map(Map.Entry::getKey).toList();

        assertThat(directors).containsSequence(List.of(
                "Alfred Hitchcock",
                "Stanley Kubrick",
                "Martin Scorsese",
                "Steven Spielberg",
                "Christopher Nolan",
                "Billy Wilder",
                "Quentin Tarantino",
                "Charles Chaplin",
                "Ridley Scott",
                "Frank Capra"
        ));

        assertThat(moviesPerDirector.get(4).getValue()).containsExactlyInAnyOrder(
                "The Dark Knight", "Inception", "Interstellar", "The Prestige", "Memento",
                "The Dark Knight Rises", "Batman Begins");

        assertThat(moviesPerDirector.get(6).getValue()).containsExactlyInAnyOrder(
                "Pulp Fiction", "Django Unchained", "Reservoir Dogs", "Inglourious Basterds",
                "Kill Bill: Vol. 1", "Sin City");

        assertThat(moviesPerDirector.get(8).getValue()).containsExactlyInAnyOrder(
                "Gladiator", "Alien", "Blade Runner", "The Martian");
    }

    @Test
    public void testEx06() {
        Map<String, Long> numOfMoviesPerActor = PlayWithMovies.ex06();

        assertThat(numOfMoviesPerActor.size()).isEqualTo(773);
        assertThat(numOfMoviesPerActor).containsAllEntriesOf(Map.of(
                "Tom Hardy", 5L,
                "Derek Jacobi", 1L));
    }

    @Test
    public void testEx07() {
        List<Map.Entry<String, Long>> nineMostFreqActors = PlayWithMovies.ex07();

        assertThat(nineMostFreqActors).containsSequence(List.of(
                Map.entry("Leonardo DiCaprio", 8L),
                Map.entry("Harrison Ford", 7L),
                Map.entry("James Stewart", 7L),
                Map.entry("Robert De Niro", 7L),
                Map.entry("Tom Hanks", 6L),
                Map.entry("William Holden", 5L),
                Map.entry("Paul Newman", 5L),
                Map.entry("Tom Hardy", 5L),
                Map.entry("Cary Grant", 5L)));
    }

    @Test
    public void testEx08() {
        List<Map.Entry<String, List<String>>> moviesPerActor = PlayWithMovies.ex08();
        List<String> actors = moviesPerActor.stream()
                .map(Map.Entry::getKey).toList();

        assertThat(actors).containsSequence(List.of(
                "Leonardo DiCaprio",
                "Harrison Ford",
                "James Stewart",
                "Robert De Niro",
                "Tom Hanks",
                "William Holden",
                "Paul Newman",
                "Tom Hardy",
                "Cary Grant"
        ));

        assertThat(moviesPerActor.get(0).getValue()).containsExactlyInAnyOrder(
                "Inception", "The Departed", "Django Unchained", "The Wolf of Wall Street",
                "Shutter Island", "Catch Me If You Can", "Blood Diamond", "The Revenant");

        assertThat(moviesPerActor.get(4).getValue()).containsExactlyInAnyOrder(
                "Forrest Gump", "Saving Private Ryan", "The Green Mile", "Toy Story 3",
                "Toy Story", "Catch Me If You Can");

        assertThat(moviesPerActor.get(7).getValue()).containsExactlyInAnyOrder(
                "Inception", "The Dark Knight Rises", "Warrior", "Mad Max: Fury Road", "The Revenant");
    }

    @Test
    public void testEx09() {
        List<Map.Entry<String, Long>> fiveMostFreqActorDuos = PlayWithMovies.ex09();

        assertThat(fiveMostFreqActorDuos).containsSequence(List.of(
                Map.entry("Carrie Fisher, Mark Hamill", 4L),
                Map.entry("Joe Pesci, Robert De Niro", 4L),
                Map.entry("Carrie Fisher, Harrison Ford", 4L),
                Map.entry("Harrison Ford, Mark Hamill", 4L),
                Map.entry("Christian Bale, Michael Caine", 3L)));
    }

    @Test
    public void testEx10() {
        List<Map.Entry<String, List<String>>> moviesPerActorDuo = PlayWithMovies.ex10();
        List<String> actorsDuos = moviesPerActorDuo.stream()
                .map(Map.Entry::getKey).toList();

        assertThat(actorsDuos).containsSequence(List.of(
                "Carrie Fisher, Mark Hamill",
                "Joe Pesci, Robert De Niro",
                "Carrie Fisher, Harrison Ford",
                "Harrison Ford, Mark Hamill",
                "Christian Bale, Michael Caine"
        ));

        assertThat(moviesPerActorDuo.get(0).getValue()).containsExactlyInAnyOrder(
                "Star Wars: Episode V - The Empire Strikes Back",
                "Star Wars: Episode IV - A New Hope",
                "Star Wars: Episode VI - Return of the Jedi",
                "Star Wars: The Force Awakens");

        assertThat(moviesPerActorDuo.get(1).getValue()).containsExactlyInAnyOrder(
                "Goodfellas", "Once Upon a Time in America", "Raging Bull", "Casino");

        assertThat(moviesPerActorDuo.get(4).getValue()).containsExactlyInAnyOrder(
                "The Dark Knight", "The Prestige", "Batman Begins");
    }
}
