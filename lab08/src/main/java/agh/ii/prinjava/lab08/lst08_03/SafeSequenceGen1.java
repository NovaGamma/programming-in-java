package agh.ii.prinjava.lab08.lst08_03;

/**
 * Java provides two basic synchronization idioms:
 * <ul>
 *     <li>synchronized methods (see {@link SafeSequenceGen1#getNext getNext})</li>
 *     <li>synchronized statements</li>
 * </ul>
 *
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/concurrency/sync.html">Synchronization</a>
 */
class SafeSequenceGen1 implements ISequenceGen {
    private int value;

    /**
     * To make a method {@code synchronized}, simply add the synchronized keyword to its declaration.
     * <p>
     * Making a method synchronized has two effects:
     * <ul>
     *     <li>It is not possible for two invocations of synchronized methods on the same object to interleave.
     *      When one thread is executing a synchronized method for an object, all other threads that invoke synchronized
     *      methods for the same object block (suspend execution) until the first thread is done with the object</li>
     *
     *      <li>When a synchronized method exits, it automatically establishes a happens-before relationship
     *      with any subsequent invocation of a synchronized method for the same object.
     *      This guarantees that changes to the state of the object are visible to all threads</li>
     * </ul>
     *
     * <i>Note</i>: synchronized methods enable a simple strategy for preventing thread interference and memory
     * consistency errors: if an object is visible to more than one thread, all reads or writes to that object's
     * variables are done through synchronized methods
     *
     * @see <a href="https://docs.oracle.com/javase/tutorial/essential/concurrency/syncmeth.html">Synchronized Methods</a>
     */
    @Override
    public synchronized int getNext() {
        return value++;
    }
}
