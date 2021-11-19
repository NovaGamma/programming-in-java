package agh.ii.prinjava.lab08.lst08_04;

import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * Less threads
 */
class ForkJoinReduction1 extends RecursiveTask<Long> {
    private final List<Long> xs;
    private final int zero = 0;

    public ForkJoinReduction1(List<Long> xs) {
        this.xs = xs;
    }

    @Override
    protected Long compute() {
        if (xs.size() < 4) {
            return xs.stream().mapToLong(e -> e).sum();
        } else {
            int midIdx = xs.size() / 2;

            ForkJoinReduction1 task1 = new ForkJoinReduction1(xs.subList(0, midIdx)); // [0, midIdx)
            ForkJoinReduction1 task2 = new ForkJoinReduction1(xs.subList(midIdx, xs.size())); // [midIdx, xs.size())

            task2.fork(); // task2 to be executed in a separate thread
            return task1.compute() + task2.join(); // task1 in the current thread, join has to be after compute!
            //return task2.join() + task1.compute(); // switching the order creates sequential processing (task2 -> task1?)
        }
    }
}
