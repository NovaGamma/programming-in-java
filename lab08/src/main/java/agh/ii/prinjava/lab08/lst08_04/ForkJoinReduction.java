package agh.ii.prinjava.lab08.lst08_04;

import java.util.List;
import java.util.concurrent.RecursiveTask;

class ForkJoinReduction extends RecursiveTask<Long> {
    private final List<Long> xs;

    public ForkJoinReduction(List<Long> xs) {
        this.xs = xs;
    }

    @Override
    protected Long compute() {
        if (xs.size() < 4) {
            return xs.stream().mapToLong(e -> e).sum();
        } else {
            int midIdx = xs.size() / 2;

            ForkJoinReduction task1 = new ForkJoinReduction(xs.subList(0, midIdx)); // [0, midIdx)
            ForkJoinReduction task2 = new ForkJoinReduction(xs.subList(midIdx, xs.size())); // [midIdx, xs.size())

            task1.fork(); // task1 to be executed in a separate thread
            task2.fork(); // task2 to be executed in a separate thread

            return task1.join() + task2.join();
        }
    }
}
