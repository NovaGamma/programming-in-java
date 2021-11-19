package agh.ii.prinjava.lab08.lst08_03;

/**
 * As above, but with the use of the <em>synchronized statement</em>.
 *
 * <p>Notes:
 * <ul>
 *     <li>Synchronization is built around an internal entity known as the <em>intrinsic lock</em> or <em>monitor lock</em>
 *     (the API specification often refers to this entity simply as a <em>monitor</em>)</li>
 *
 *     <li>Every object has an intrinsic lock associated with it. By convention, a thread that needs exclusive and
 *     consistent access to an object's fields has to acquire the object's intrinsic lock before accessing them,
 *     and then release the intrinsic lock when it's done with them</li>
 * </ul>
 *
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/concurrency/locksync.html">Intrinsic Locks and Synchronizationś</a>
 */
class SafeSequenceGen2 implements ISequenceGen {
    private int value;

    @Override
    public int getNext() {
        // synchronized statement
        synchronized (this) {
            return value++;
        }
    }
}
