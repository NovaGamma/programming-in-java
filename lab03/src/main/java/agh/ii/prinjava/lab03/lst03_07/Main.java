package agh.ii.prinjava.lab03.lst03_07;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * <ul>
 *     <li>The abstract {@link java.io.InputStream} is the root class for reading binary data</li>
 *     <li>The abstract {@link java.io.OutputStream} is the root class for writing binary data</li>
 * </ul>
 * <p>
 * {@link java.io.FileInputStream} / {@link java.io.FileOutputStream} are for reading/writing bytes from/to files.
 * All the methods in these classes are inherited from {@link java.io.InputStream} and {@link java.io.OutputStream}.
 *
 * <p>The {@link java.io.InputStream} and {@link java.io.OutputStream} classes implement the {@link AutoCloseable} interface.
 * The AutoClosable interface defines the close() method that closes a resource. Any object of the AutoClosable
 * type can be used with the try-with-resources syntax for automatic closing.
 */
public class Main {
    private static final double[] dbls = {1.1, 2.2, 3.3, 4.4, 5.5};
    private static final String demoFileName = "dbls.dat";

    private static void deleteDemoFile() {
        try {
            Files.deleteIfExists(Path.of(demoFileName));
            System.out.println("\nDemo file deleted");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Low-level solution as the write methods of an {@link java.io.OutputStream}
     * can write ONLY individual bytes and byte arrays
     */
    private static void demo1() {
        System.out.println("demo1...");
        // Prepare the data to be sent to the stream
        ByteBuffer bb = ByteBuffer.allocate(dbls.length * Double.BYTES);
        for (double d : dbls) {
            bb.putDouble(d);
        }

        try (var fout = Files.newOutputStream(Path.of(demoFileName))) {
            fout.write(bb.array());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Low-level solution as the read methods of an {@link java.io.FileInputStream}
     * can read ONLY individual bytes and byte arrays
     */
    private static void demo2() {
        System.out.println("\ndemo2...");
        try (var fin = Files.newInputStream(Path.of(demoFileName))) {
            // Again, this low-level stuff...
            byte[] data = fin.readAllBytes();
            double[] ldbls = new double[data.length / Double.BYTES];
            ByteBuffer.wrap(data).asDoubleBuffer().get(ldbls);

            for (double d : ldbls) {
                System.out.println(d);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@link java.io.DataOutputStream} to the rescue (compare to {@link #demo1}).
     * It enables you to write primitive data-type values and strings into an output stream
     */
    private static void demo3() {
        System.out.println("\ndemo3...");
        try (var out = new DataOutputStream(Files.newOutputStream(Path.of(demoFileName)))) {
            for (double d : dbls) {
                out.writeDouble(d); // instead of byte-level writes, DataOutputStream introduces data awarness
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@link java.io.DataInputStream} to the rescue (compare to {@link #demo2}).
     * It converts an input stream of bytes into primitive data-type values and strings
     */
    private static void demo4() {
        System.out.println("\ndemo4...");
        try (var in = new DataInputStream(Files.newInputStream(Path.of(demoFileName)))) {
            for (int i = 0; i < dbls.length; i++) {
                System.out.println(in.readDouble());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // (write + read) + delete, byte-oriented
        demo1();
        demo2();
        deleteDemoFile();

        // (write + read) + delete, data type aware (but only for predefined types)
        demo3();
        demo4();
        deleteDemoFile();

        // But what about user-defined types? Object serialisation is (or rather used to be) the answer :)
    }
}
