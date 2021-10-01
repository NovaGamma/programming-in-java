package agh.ii.prinjava.lab03.lst03_06;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

/**
 * <ul>
 *     <li>An {@link InputStream} is a source of bytes, and an {@link OutputStream} is a destination for bytes</li>
 *     <li>A {@link Reader} reads characters, and a {@link Writer} writes them ( about a character encoding)</li>
 * </ul>
 */
public class Main {
    private static final String[] textLines = {
            "All I have for you is a gesture, in combination with a word.",
            "Tenet.",
            "Use it carefully.",
            "It'll open the right doors, but some of the wrong ones, too."
    };

    private static final String demoFileName1 = "tenet.txt";

    private static void deleteDemoFile() {
        try {
            Files.deleteIfExists(Path.of(demoFileName1));
            System.out.println("\nDemo file deleted");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Low-level style writing to a text file demo
     */
    private static void demo1() {
        System.out.println("demo1...");
        //try (var fout = new BufferedWriter(new FileWriter(demoFileName1, StandardCharsets.UTF_8))) {
        try (var fout = Files.newBufferedWriter(Path.of(demoFileName1), StandardCharsets.UTF_8)) {
            for (var line : textLines) {
                fout.write(line);
            }
            fout.flush();
            System.out.println("Writing to " + demoFileName1 + " completed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * As {@link #demo1} but with the use of {@link PrintWriter}
     */
    private static void demo2() {
        System.out.println("\ndemo2...");
        //try (PrintWriter fout = new PrintWriter(demoFileName1)) {
        try (var fout = new PrintWriter(Files.newBufferedWriter(Path.of(demoFileName1), StandardCharsets.UTF_8))) {
            for (var line : textLines) {
                fout.println(line);
            }
            fout.flush();
            System.out.println("Writing to " + demoFileName1 + " completed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * As {@link #demo1} but with the use of {@link java.nio.file.Files#write}
     */
    private static void demo3() {
        System.out.println("\ndemo3...");
        try {
            Files.write(Path.of(demoFileName1), List.of(textLines), StandardCharsets.UTF_8);
            System.out.println("Writing to " + demoFileName1 + " completed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Low-level style reading from a text file demo
     */
    private static void demo4() {
        System.out.println("\ndemo4...");
        //try (var fin = new BufferedReader(new InputStreamReader(new FileInputStream(demoFileName1)))) {
        try (var fin = Files.newBufferedReader(Path.of(demoFileName1), StandardCharsets.UTF_8)) {
            String line;
            while ((line = fin.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * As {@link #demo4} but with the use of {@link Scanner}
     */
    private static void demo5() {
        System.out.println("\ndemo5...");
        try (var fin = new Scanner(Path.of(demoFileName1), StandardCharsets.UTF_8)) {
            while (fin.hasNextLine()) {
                String line = fin.nextLine();
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * As {@link #demo4} but with the use of {@link java.nio.file.Files#readAllLines}
     */
    private static void demo6() {
        System.out.println("\ndemo6...");
        try {
            var lines = Files.readAllLines(Path.of(demoFileName1), StandardCharsets.UTF_8);
            for (var line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        demo1();
        deleteDemoFile();
        demo2();
        deleteDemoFile();
        demo3();
        demo4();
        demo5();
        demo6();
        deleteDemoFile();
    }
}
