package agh.ii.prinjava.lab01.lst01_08;

/**
 * Implementation characteristics:
 * <ul>
 *     <li>Thread-safe</li>
 *     <li>Eager instantiation</li>
 * </ul>
 */
public class EagerSingleton {
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    private EagerSingleton() {
    }

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
}
