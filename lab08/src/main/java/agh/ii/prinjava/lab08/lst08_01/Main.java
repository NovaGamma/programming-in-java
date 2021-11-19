package agh.ii.prinjava.lab08.lst08_01;

/**
 * <p>A <i>process</i>: an isolated, independently executing program to which the operating system
 * allocates resources such as memory, file handles, and security credentials.
 * <p><i>Note:</i> most implementations of the Java virtual machine run as a single process
 *
 * <p>A {@link Thread} (sometimes called <i>lightweight processes</i>):
 * a single sequential flow of control within a program ("thread" is short for "thread of control").
 * <ul>
 *     <li>Each thread has its own execution stack and program counter</li>
 *     <li>Threads within a single process share process-wide resources such as memory and file handles</li>
 *     <li>Threads provide a natural decomposition for exploiting hardware parallelism on multiprocessor systems;
 *     multiple threads within the same program can be scheduled simultaneously on multiple CPUs</li>
 * </ul>
 * A thread can be in one of the following states:
 * <ul>
 *     <li>NEW - a thread that has not yet started is in this state</li>
 *     <li>RUNNABLE - a thread executing in the Java virtual machine is in this state</li>
 *     <li>BLOCKED - a thread that is blocked waiting for a monitor lock is in this state</li>
 *     <li>WAITING - a thread that is waiting indefinitely for another thread to perform
 *         a particular action is in this state</li>
 *     <li>TIMED_WAITING - a thread that is waiting for another thread to perform an action for up to a specified
 *     waiting time is in this state</li>
 *     <li>TERMINATED - a thread that has exited is in this state</li>
 * </ul>
 *
 *  <p><i>Note</i>: developing, testing and debugging multi-threaded programs can be extremely difficult
 *  because concurrency bugs do not manifest themselves predictably.
 *
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/concurrency/procthread.html">Processes and Threads</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Thread.html">Thread</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Thread.State.html">Thread.State</a>
 */
public class Main {
    /**
     * Consider the following scenario:
     * <ol>
     *     <li> Data preparation:
     *         <ul>
     *             <li>Worker task 1 (5 seconds)</li>
     *             <li>Worker task 2 (4 seconds)</li>
     *         </ul>
     *         Expected duration (sequential case): 5 + 4 = 9 seconds
     *     </li>
     *     <li>As soon as BOTH results are available - processing data</li>
     * </ol>
     */
    private static void demo1() {
        System.out.println("demo1...");
        System.out.println("\nData preparation (sequential)...");

        WorkerTask workerTask1 = new WorkerTask(5000, "WorkerTask1");
        WorkerTask workerTask2 = new WorkerTask(4000, "WorkerTask2");

        long t1 = System.currentTimeMillis();
        workerTask1.run();
        workerTask2.run();
        System.out.println("Processing the data...");
        long t2 = System.currentTimeMillis();

        System.out.println("sequential dt = " + (t2 - t1));
    }

    /**
     * As above, but run in parallel. Expected duration: max(5, 4) = 5 seconds
     */
    private static void demo2() {
        System.out.println("\ndemo2...");
        System.out.println("\nData preparation (in parallel)...");

        var workerThread1 = new Thread(new WorkerTask(5000, "WorkerTask1"));
        var workerThread2 = new Thread(new WorkerTask(4000, "WorkerTask2"));

        long t1 = System.currentTimeMillis();
        workerThread1.start();
        workerThread2.start();
        try {
            workerThread1.join(); // make the current thread wait for the workerThread1 completion
            workerThread2.join(); // make the current thread wait for the workerThread2 completion
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Processing the data...");
        long t2 = System.currentTimeMillis();

        System.out.println("parallel dt = " + (t2 - t1));
    }

    /**
     * Error 1: calling {@link Thread#run} instead of {@link Thread#start}
     *
     * <p>Calling the {@code run} method directly merely executes the task in the same thread
     * - NO new thread is started!
     */
    private static void demo3() {
        System.out.println("\ndemo3...");
        System.out.println("\nData preparation (intended to be parallel, but)...");

        var workerThread1 = new Thread(new WorkerTask(5000, "WorkerTask1"));
        var workerThread2 = new Thread(new WorkerTask(4000, "WorkerTask2"));

        long t1 = System.currentTimeMillis();
        workerThread1.run(); // <- Error (calling run instead of start)!
        workerThread2.run(); // <- Error (calling run instead of start)!
        System.out.println("Processing the data...");
        long t2 = System.currentTimeMillis();

        System.out.println("parallel dt = " + (t2 - t1));
    }

    /**
     * Error 2: no synchronization at the end (i.e. before step 2)
     */
    private static void demo4() {
        System.out.println("\ndemo4...");
        System.out.println("\nData preparation (in parallel without synchronization at the end)...");

        var workerThread1 = new Thread(new WorkerTask(5000, "WorkerTask1"));
        var workerThread2 = new Thread(new WorkerTask(4000, "WorkerTask2"));

        long t1 = System.currentTimeMillis();
        workerThread1.start();
        workerThread2.start();
        // There is no synchronization here (the current/main thread does not wait for the worker threads)
        System.out.println("Processing the data...");
        long t2 = System.currentTimeMillis();

        System.out.println("parallel dt = " + (t2 - t1));
    }

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
        demo4();
    }

    /*
    private static void demo5() {
        System.out.println("\ndemo5...");
        System.out.println("\nData preparation (in parallel)...");

        WorkerThread workerThread1 = new WorkerThread(5000, "WorkerThread1");
        WorkerThread workerThread2 = new WorkerThread(5000, "WorkerThread1");

        long t1 = System.currentTimeMillis();
        workerThread1.start();
        workerThread2.start();
        try {
            workerThread1.join();
            workerThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Processing the data...");
        long t2 = System.currentTimeMillis();
        System.out.println("parallel dt = " + (t2 - t1));
    }
    */
}

/*

// This approach is NOT recommended
class WorkerThread extends Thread {
    private long duration;
    private String taskName;

    WorkerThread(long duration, String taskName) {
        super(taskName);
        this.duration = duration;
        this.taskName = taskName;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(duration);
            System.out.println(this.taskName + " completed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
*/