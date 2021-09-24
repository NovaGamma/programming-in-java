package agh.ii.prinjava.lab01.lst01_08;

/**
 * Implementation characteristics:
 * <ul>
 *     <li>Thread-safe</li>
 *     <li>Lazy instantiation</li>
 * </ul>
 */
public final class LazySingleton {

    private static volatile LazySingleton instance = null;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}
