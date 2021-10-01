package agh.ii.prinjava.lab03.lst03_05;

import java.io.Console;
import java.util.Locale;
import java.util.Scanner;

/**
 * Java uses {@code System.out} to refer to the standard output device, and {@code System.in}
 * to the standard input device.
 * By default, the output device is the display monitor, and the input device is the keyboard.
 * <ul>
 *     <li>To perform console output, you simply use the {@code System.out.println} or {@code print} method to display
 *         a primitive value or a string to the console.</li>
 *     <li>To perform console input, you need to use the {@code Scanner} class to create an object
 *         to read input from {@code System.in}</li>
 * </ul>
 *
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/System.html#in">System.in</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/System.html#out">System.out</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/System.html#err">System.err</a>
 * @see <a href=https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Scanner.html>Scanner</a>
 */
public class Main {
    private static void demo1() {
        Scanner in = new Scanner(System.in).useLocale(Locale.ENGLISH);

        System.out.print("Enter an int: ");
        int i = in.nextInt();
        System.out.println(i);

        System.out.print("Enter a double (x.y): ");
        double d = in.nextDouble();
        System.out.println(d);

        System.out.print("Enter your name: ");
        String s = in.next();
        System.out.println(s);
    }

    /**
     * To see this in action, run "{@code java agh.ii.prinjava.lab03.lst03_05.Main}" from the terminal window.
     * <p>Note: you need ether of the two following:
     * <ul>
     *     <li>set accordingly the classpath ({@code java -cp} ...)</li>
     *     <li>set ".../programming-in-java/lab03/build/classes/java/main" as the current/working directory</li>
     * </ul>
     */
    private static void demo2() {
        Console cnsl = System.console();

        if (cnsl == null) {
            System.out.println("No console available");
            return;
        }

        String str = cnsl.readLine("Enter username : ");
        System.out.println("Username : " + str);
        char[] chrs = cnsl.readPassword("Enter password : ");

        System.out.print("Password :");
        for (var c : chrs) {
            System.out.print(c);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        demo1();
        demo2();
    }
}
