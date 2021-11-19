package agh.ii.prinjava.lab08.lst08_04;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The <i>fork/join framework</i> is an implementation of the {@link ExecutorService} interface and is designed
 * for work that can be broken into smaller pieces recursively.
 * As with any {@code ExecutorService} implementation, the fork/join framework distributes tasks to worker threads
 * in a thread pool. The fork/join framework uses a <i>work-stealing algorithm</i>.
 *
 * <p>A ForkJoinTask is a thread-like entity that is much lighter weight than a normal thread.
 * Huge numbers of tasks and subtasks may be hosted by a small number of actual threads in a ForkJoinPool,
 * at the price of some usage limitations.
 *
 * <ul>
 *    <li>{@link RecursiveAction} (abstract class) corresponds to {@link Runnable}</li>
 *    <li>{@link RecursiveTask} (abstract class) corresponds to {@link Callable}</li>
 * </ul>
 *
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/concurrency/forkjoin.html">Fork/Join</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/ForkJoinPool.html">ForkJoinPool</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/ForkJoinTask.html">ForkJoinTask</a>
 */
public class Main {
    private static final List<Long> LIST1 =
            Stream.iterate(1L, n -> n <= 128, n -> n + 1).collect(Collectors.toList());

    private static void demo1() {
        System.out.println("demo1...");

        ForkJoinPool fjPool = new ForkJoinPool();
        Long fib10 = fjPool.invoke(new RecursiveFib(10));
        System.out.println("fib10: " + fib10);
    }

    private static void demo2() {
        System.out.println("\ndemo2...");
        System.out.println("Expected result: " + LIST1.stream().mapToLong(e -> e).sum());

        ForkJoinPool fjPool = new ForkJoinPool();
        Long result = fjPool.invoke(new ForkJoinReduction(LIST1));
        System.out.println("Actual result: " + result);
    }

    /**
     * As {@link Main#demo2 demo2} but with fewer threads
     */
    private static void demo3() {
        System.out.println("\ndemo3...");
        System.out.println("Expected result: " + LIST1.stream().mapToLong(e -> e).sum());

        ForkJoinPool fjPool = new ForkJoinPool();
        Long result = fjPool.invoke(new ForkJoinReduction1(LIST1));
        System.out.println("Actual result: " + result);
    }

    private static void demo4() {
        System.out.println("\ndemo4...");
        ForkJoinPool fjPool = new ForkJoinPool();

        long t1 = System.currentTimeMillis();
        fjPool.invoke(new ForkJoinSimulationTask(16));
        long t2 = System.currentTimeMillis();

        System.out.println("Parallel dt = " + (t2 - t1));
    }

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
        demo4();
    }
}

