package agh.ii.prinjava.lab01.lst01_08;

/**
 * The singleton - one of the classic (GoF) design patterns.
 * <p>It restricts the instantiation of a class to one "single" instance
 * <p>Implementations of the singleton pattern must:
 * <ul>
 *     <li>Ensure that only one instance of the singleton class ever exists</li>
 *     <li>Provide global access to that instance</li>
 * </ul>
 *
 * @see <a href="https://dzone.com/articles/singleton-design-pattern-1">
 * The Singleton Design Pattern</a>
 */
public class Main {
    public static void main(String[] args) {
        // UnsafeSingleton usgt0 = new UnsafeSingleton(); // the constructor is private

        if (UnsafeSingleton.getInstance() != UnsafeSingleton.getInstance()) {
            System.out.println("Two instances of an UnsafeSingleton?");
        }

        if (EagerSingleton.getInstance() != EagerSingleton.getInstance()) {
            System.out.println("Two instances of an EagerSingleton?");
        }

        if (LazySingleton.getInstance() != LazySingleton.getInstance()) {
            System.out.println("Two instances of a sLazySingleton?");
        }
    }
}
