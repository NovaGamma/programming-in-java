package agh.ii.prinjava.lab08.lst08_01;

/**
 * An application that creates an instance of {@link Thread} must provide the code that will run in that thread.
 * There are two ways to do this:
 * <ol>
 *     <li>Provide a {@link Runnable} object (the recommended way!)</li>
 *     <li>Subclass {@link Thread}. The {@code Thread} class itself implements Runnable, though its run method
 *     does nothing. An application can subclass {@code Thread}, providing its own implementation of run</li>
 * </ol>
 * <p>
 * The {@code Runnable} interface should be implemented by any class whose instances are intended to be executed
 * by a thread. The class must define a method of no arguments called {@link Runnable#run() run}.
 *
 * <p>{@code Runnable} provides the means for a class to be active while not subclassing {@link Thread}.
 * A class that implements {@code Runnable} can run without subclassing {@code Thread} by instantiating a {@code Thread}
 * instance and passing itself in as the target, i.e., {@link Thread#Thread(Runnable)}
 *
 * <p>In most cases, the {@code Runnable} interface should be used if you are only planning to override
 * the {@link Runnable#run() run} method and no other {@code Thread} methods.
 *
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Runnable.html">Runnable</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Thread.html">Thread</a>
 */
record WorkerTask(long duration, String taskName) implements Runnable {

    @Override
    public void run() {
        // Blocking methods like "sleep" throw exceptions, and they have to be handled
        try {
            Thread.sleep(duration);
            System.out.println(this.taskName + " completed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
