package agh.ii.prinjava.lab08.lst08_04;

import java.util.concurrent.RecursiveTask;

/**
 * A classic example - a task computing Fibonacci numbers (see {@link RecursiveTask}).
 *
 * <p><i>Note</i>: besides being a dumb way to compute Fibonacci functions (there is a simple fast linear algorithm
 * that you'd use in practice), this is likely to perform poorly because the smallest subtasks are too small
 * to be worthwhile splitting up.
 */
class RecursiveFib extends RecursiveTask<Long> {
    private final long n;

    public RecursiveFib(long n) {
        this.n = n;
    }

    @Override
    protected Long compute() {
        if (n <= 1) {
            return n;
        } else {
            RecursiveFib f1 = new RecursiveFib(n - 1);
            RecursiveFib f2 = new RecursiveFib(n - 2);
            f1.fork();
            f2.fork();
            return f1.join() + f2.join();
        }
    }
}
