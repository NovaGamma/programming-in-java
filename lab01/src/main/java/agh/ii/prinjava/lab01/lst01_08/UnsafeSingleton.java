package agh.ii.prinjava.lab01.lst01_08;

/**
 * Implementation characteristics:
 * <ul>
 *     <li>Not thread-safe! (do not use!)</li>
 *     <li>Lazy instantiation</li>
 * </ul>
 */
public class UnsafeSingleton {
    private static UnsafeSingleton SINGLETON = null;

    /**
     * private, therefore you cannot - without hacking - create an instance
     * with the {@code new} operator
     */
    private UnsafeSingleton() {
    }

    /**
     * kind of a factory method
     */
    public static UnsafeSingleton getInstance() {
        if (SINGLETON == null) {
            SINGLETON = new UnsafeSingleton();  // the first call creates the instance
        }
        return SINGLETON;
    }
}

