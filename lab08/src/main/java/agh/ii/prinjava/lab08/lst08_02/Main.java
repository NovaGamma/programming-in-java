package agh.ii.prinjava.lab08.lst08_02;

import java.util.concurrent.*;

/**
 * <ol>
 *   <li>
 *       {@code public interface} {@link Executor} - provides a way of decoupling task submission
 *       from the mechanics of how each task will be run.
 *       An {@code Executor} is normally used instead of explicitly creating threads, e.g.:
 *       <pre> {@code
 *       Executor executor = ...;
 *       executor.execute(new RunnableTask());
 *       }</pre>
 *       instead of:
 *       <pre> {@code
 *       new Thread(new RunnableTask()).start()
 *       }</pre>
 *   </li>
 *   <li>
 *       {@code public interface} {@link ExecutorService} {@code extends Executor} - supplements {@link Executor#execute execute}
 *       with a similar, but more versatile {@link ExecutorService#submit submit} method. Additionally, it provides
 *       methods for submitting large collections of {@link Callable} objects and to manage the shutdown of the executor.
 *       <ul>
 *          <li>{@link ExecutorService#submit submit} extends base method {@code Executor.execute(Runnable)}
 *           by creating and returning a {@link Future} that can be used to cancel execution and/or wait for completion.
 *           Like {@code execute}, {@code submit} accepts {@code Runnable} objects, but also accepts {@code Callable} objects,
 *           which allow the task to return a value.</li>
 *
 *          <li>{@link ExecutorService#invokeAny invokeAny} and {@link ExecutorService#invokeAll invokeAll}
 *              perform the most commonly useful forms of bulk execution, executing a collection of tasks and then waiting
 *              for at least one, or all, to complete</li>
 *          <li>{@link ExecutorService#shutdown shutdown} will allow previously submitted tasks
 *          to execute before terminating and {@link ExecutorService#shutdownNow shutdownNow} method prevents waiting
 *          tasks from starting and attempts to stop currently executing tasks</li>
 *       </ul>
 *   </li>
 *   <li>
 *       The {@link ScheduledExecutorService} interface supplements the methods of its parent {@code ExecutorService}
 *       with {@link ScheduledExecutorService#schedule schedule}, which executes a Runnable or {@code Callable} task
 *       after a specified delay.
 *       In addition, the interface defines {@link ScheduledExecutorService#scheduleAtFixedRate scheduleAtFixedRate}
 *       and {@link ScheduledExecutorService#scheduleWithFixedDelay scheduleWithFixedDelay}, which executes specified
 *       tasks repeatedly, at defined intervals
 *   </li>
 *   <li>
 *       {@code public class} Executors {@link Executors} {@code extends Object} - provides factory and utility methods
 *       for the executor services provided in package {@link java.util.concurrent}
 *   </li>
 * </ol>
 *
 * <i>Note</i>: upon termination, an executor has no tasks actively executing, no tasks awaiting execution,
 * and no new tasks can be submitted. An unused {@code ExecutorService} should be shut down to allow
 * reclamation of its resources.
 *
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/Executor.html">Executor</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/ExecutorService.html">ExecutorService</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/Executors.html">Executors</a>
 */
public class Main {
    /**
     * Selected executors (executor services):
     * <ul>
     *     <li><i>FixedThreadPool</i> - a thread pool that reuses a fixed number of threads
     *     operating off a shared unbounded queue</li>
     *
     *     <li><i>CachedThreadPool</i> - a thread pool that creates new threads as needed,
     *     but will reuse previously constructed threads when they are available</li>
     *
     *     <li><i>SingleThreadExecutor</i> - an executor that uses a single worker thread
     *     operating off an unbounded queue
     *
     *     <li><i>WorkStealingPool</i> - a thread pool that maintains enough threads to support
     *     the given parallelism level, and may use multiple queues to reduce contention
     * </ul>
     *
     * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/Executors.html">Executors</a>
     */
    private static void demo1() {
        System.out.println("demo1...");
        ExecutorService exSrv1 = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        ExecutorService exSrv2 = Executors.newSingleThreadExecutor();
        ExecutorService exSrv3 = Executors.newCachedThreadPool();
        ExecutorService exSrv4 = Executors.newWorkStealingPool();
    }

    private static void demo2() {
        System.out.println("\ndemo2...");
        int n = Runtime.getRuntime().availableProcessors();
        System.out.println("JVM: availableProcessors = " + n);

        Runnable r = () -> System.out.println(Thread.currentThread().getName());
        ExecutorService execService = Executors.newFixedThreadPool(n);
        // ExecutorService does not implement the Autocloseable interface
        try {
            execService.execute(r);
            //or
            final Future<?> f = execService.submit(r);
            System.out.println("f.get(): " + f.get()); // -> null
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            execService.shutdown(); // <- an executor service has to be shutdown!
        }

        System.out.println("About to end demo2, the thread name: " + Thread.currentThread().getName());
    }

    private static void demo3() {
        System.out.println("\ndemo3...");
        ExecutorService exServ = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        Callable<String> c1 = () -> Thread.currentThread().getName();
        SummingCallable c2 = new SummingCallable(5, 10);

        try {
            Future<String> f1 = exServ.submit(c1);
            String val1 = f1.get();
            System.out.println("f1.get(): " + val1);

            Future<Integer> f2 = exServ.submit(c2);
            Integer val2 = f2.get();
            System.out.println("f.get(): " + val2);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            exServ.shutdown();
        }

        System.out.println("About to end demo3, the thread name: " + Thread.currentThread().getName());
    }

    /**
     * {@link ScheduledExecutorService} - an executor service that can schedule commands to run after a given delay,
     * or to execute periodically
     * <p> Selected scheduled executors (executor services):
     * <ul>
     *     <li><i>SingleThreadScheduledExecutor</i> - a single-threaded executor that can schedule commands
     *     to run after a given delay, or to execute periodically</li>
     *
     *     <li><i>ScheduledThreadPool</i> - a thread pool that can schedule commands to run after a given delay,
     *     or to execute periodically.
     *
     * </ul>
     * <p>
     * {@link ScheduledExecutorService#schedule schedule} - submits a value-returning one-shot task that becomes
     * enabled after the given delay
     *
     * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/Executors.html">Executors</a>
     */
    private static void demo4() {
        System.out.println("\ndemo4...");
        ScheduledExecutorService exSrv1 = Executors.newSingleThreadScheduledExecutor();
        //ScheduledExecutorService exSrv2 = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        Runnable r1 = () -> System.out.println(Thread.currentThread().getName());

        try {
            exSrv1.schedule(r1, 5, TimeUnit.SECONDS);
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            exSrv1.shutdown();
        }
    }

    /**
     * {@link ScheduledExecutorService#scheduleAtFixedRate scheduleAtFixedRate} - submits a periodic action
     * that becomes enabled first after the given initial delay, and subsequently with the given period;
     * that is, executions will commence after initialDelay, then initialDelay + period,
     * then initialDelay + 2 * period, and so on
     */
    private static void demo5() {
        System.out.println("\ndemo5...");
        ScheduledExecutorService exSrv = Executors.newSingleThreadScheduledExecutor();
        Runnable r = () -> System.out.println(Thread.currentThread().getName());

        try {
            exSrv.scheduleAtFixedRate(r, 2, 5, TimeUnit.SECONDS);
            Thread.sleep(15000);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            exSrv.shutdown();
        }
    }

    /**
     * {@link ScheduledExecutorService#scheduleWithFixedDelay scheduleWithFixedDelay} - submits a periodic action
     * that becomes enabled first after the given initial delay, and subsequently with the given delay between
     * the termination of one execution and the commencement of the next
     */
    private static void demo6() {
        System.out.println("\ndemo6...");
        ScheduledExecutorService exSrv = Executors.newSingleThreadScheduledExecutor();
        Runnable r = () -> System.out.println(Thread.currentThread().getName());

        try {
            exSrv.scheduleWithFixedDelay(r, 2, 5, TimeUnit.SECONDS);
            Thread.sleep(15000);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            exSrv.shutdown();
        }
    }

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
        demo4();
        demo5();
        demo6();
    }
}

