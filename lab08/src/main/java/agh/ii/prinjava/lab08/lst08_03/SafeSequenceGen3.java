package agh.ii.prinjava.lab08.lst08_03;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * An atomic action is one that effectively happens all at once. An atomic action cannot stop in the middle:
 * it either happens completely, or it doesn't happen at all. No side effects of an atomic action are visible
 * until the action is complete.
 *
 * <p>{@link java.util.concurrent.atomic} - a small toolkit of classes that support lock-free thread-safe programming
 * on single variables.
 *
 * <p>{@link AtomicInteger} an int value that may be updated atomically.
 * It is used in applications such as atomically incremented counters.
 *
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/atomic/AtomicInteger.html">AtomicInteger</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/atomic/AtomicLong.html">AtomicLong</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/atomic/AtomicBoolean.html">AtomicBoolean</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/atomic/AtomicReference.html">AtomicReference</a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/package-summary.html">java.util.concurrent.atomic</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/invoke/VarHandle.html">VarHandle</a>
 */
class SafeSequenceGen3 implements ISequenceGen {
    AtomicInteger value = new AtomicInteger(0);

    @Override
    public int getNext() {
        return value.getAndIncrement();

    }
}
