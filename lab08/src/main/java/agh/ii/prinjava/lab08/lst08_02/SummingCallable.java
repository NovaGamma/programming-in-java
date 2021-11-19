package agh.ii.prinjava.lab08.lst08_02;

import java.util.concurrent.Callable;

/**
 * The {@link Callable} interface is similar to {@link Runnable}, in that both are designed for classes whose instances
 * are potentially executed by another thread. Implementors define a single method with no arguments called
 * {@link Callable#call call} that returns a result and may throw an exception
 *
 * <p><i>Note</i>: {@link Runnable#run} does not return a result and cannot throw a checked exception
 *
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/Callable.html">Callable</a>
 */
record SummingCallable(int x, int y) implements Callable<Integer> {

    @Override
    public Integer call() {
        return x + y;
    }
}
