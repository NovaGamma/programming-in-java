package agh.ii.prinjava.lab08.lst08_03;

/**
 * <em>Liveness</em> - a concurrent application's ability to execute in a timely manner
 *
 * <p><em>Deadlock</em> (the most common kind of liveness problem) describes a situation where two or more threads
 * are blocked forever, waiting for each other
 *
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/concurrency/deadlock.html">Deadlock</a>
 */
class DeadlockDemo {
    private final Object o1 = new Object();
    private final Object o2 = new Object();

    /**
     * Note the order of the synchronization blocks (compare to {@link DeadlockDemo#m2 m2})
     */
    public void m1() {
        System.out.println("m1: (1)");
        synchronized (o1) {
            System.out.println("m1: (2)");
            synchronized (o2) { // <- Deadlock!
                System.out.println("m1: (3)");
            }
        }
    }

    /**
     * Note that {@code o1} and {@code o2} are reversed (when compared to {@link DeadlockDemo#m1 m1})
     */
    public void m2() {
        System.out.println("m2: (1)");
        synchronized (o2) {
            System.out.println("m2: (2)");
            synchronized (o1) { // Deadlock
                System.out.println("m2: (3)");
            }
        }
    }
}
