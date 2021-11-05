package agh.ii.prinjava.lab07.dal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public interface Alice {
    static Optional<List<String>> lines() {
        String aliceFileName = "lab07/datasources/alice.txt";

        try (Stream<String> lines = Files.lines(Path.of(aliceFileName))) {
            return Optional.of(
                    lines.skip(1)
                            .filter(s -> !s.isBlank())
                            .map(String::stripLeading)
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

    /**
     * Note the imperative style (compare to {@link #lines})
     */
    static Optional<Map<Integer, List<String>>> chapterLines() {
        Optional<List<String>> optLines = lines();

        if (optLines.isPresent()) {
            Pattern chapterTitle = Pattern.compile("^[IV]+--.+");
            List<String> lines = optLines.get().stream().skip(2).toList();

            Map<Integer, List<String>> result = new TreeMap<>();
            int chapterNum = 1;
            List<String> chapterLines = new ArrayList<>();

            for (var line : lines) {
                if (chapterTitle.matcher(line).matches()) {
                    result.put(chapterNum, chapterLines);
                    chapterNum += 1;
                    chapterLines = new ArrayList<>();
                } else {
                    chapterLines.add(line);
                }
            }

            result.put(chapterNum, chapterLines);
            return Optional.of(result);
        }

        return Optional.empty();
    }

    /**
     * Mapping "alfa" -> List.of("a", "l", "f", "a")
     */
    static List<String> expand(String s) {
        return s.codePoints()
                .mapToObj(codePoint -> Character.toString((char) codePoint))
                .collect(toList());
    }
}
