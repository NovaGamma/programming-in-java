package agh.ii.prinjava.proj2.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record Movie(int rank, double rating, String title, int year, String rated, int runtime,
                    List<String> genre, List<String> directors,
                    List<String> actors, String production) {

    /**
     * Factory method
     */
    public static Movie of(String rawLine) {
        List<String> vals = new ArrayList<>();
        boolean inQuote = false;
        int start = 0, sLen = rawLine.length();

        for (int i = 0; i < sLen - 1; i++) {
            if (rawLine.charAt(i) == ',' && !inQuote) {
                vals.add(rawLine.substring(start, i));
                start = i + 1;
            } else if (rawLine.charAt(i) == '"') {
                inQuote = !inQuote;
            }
        }
        vals.add(rawLine.substring(start));

        String splitPtn = ",\\s*";

        return new Movie(
                Integer.parseInt(vals.get(0)),
                Double.parseDouble(vals.get(1)),
                trimQuotes(vals.get(2)),
                Integer.parseInt(vals.get(3)),
                trimQuotes(vals.get(4)),
                Integer.parseInt(vals.get(5)),
                Arrays.asList(trimQuotes(vals.get(6)).split(splitPtn)),
                Arrays.asList(trimQuotes(vals.get(7)).split(splitPtn)),
                Arrays.asList(trimQuotes(vals.get(8)).split(splitPtn)),
                trimQuotes(vals.get(9))
        );
    }

    private static String trimQuotes(String s) {
        return s.substring(1, s.length() - 1);
    }
}
