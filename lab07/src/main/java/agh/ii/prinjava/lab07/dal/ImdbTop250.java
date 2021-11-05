package agh.ii.prinjava.lab07.dal;

import agh.ii.prinjava.lab07.model.Movie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface ImdbTop250 {
    String thisFileName = "lab07/datasources/imdb_top250.csv";

    static Optional<List<Movie>> movies() {
        try (Stream<String> lines = Files.lines(Path.of(thisFileName))) {
            return Optional.of(
                    lines.skip(1)
                            .map(Movie::of)
                            .collect(
                                    Collectors.collectingAndThen(
                                            Collectors.toList(),
                                            Collections::unmodifiableList
                                    )
                            )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
