package agh.ii.prinjava.lab08.lst08_03;

/**
 * Threads communicate primarily by sharing access to fields and the objects reference fields refer to.
 * This form of communication is extremely efficient, but makes two kinds of errors possible:
 * <em>thread interference</em> and <em>memory consistency errors</em>.
 * The tool needed to prevent these errors is <em>synchronization</em>
 *
 * <p>{@code value++} may appear to be a single operation, but is in fact three separate operations:
 * <ol>
 *     <li>read the value</li>
 *     <li>add one to it</li>
 *     <li>write out the new value</li>
 * </ol>
 * Since operations in multiple threads may be arbitrarily interleaved by the runtime, it is possible for two threads
 * to read the value at the same time, both see the same value, and then both add one to it.
 * The result is that the same sequence number is returned from multiple calls in different threads.
 *
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/concurrency/interfere.html">Thread Interference</a>
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/concurrency/memconsist.html">Memory Consistency Errors</a>
 */
class UnsafeSequenceGen implements ISequenceGen {
    private int value;

    @Override
    public int getNext() {
        return value++;
    }
}
