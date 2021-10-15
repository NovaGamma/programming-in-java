package agh.ii.prinjava.lab05.lst05_03;

import java.util.*;

/**
 * <p>Queues typically, but do not necessarily (e.g., priority queues),
 * order elements in a FIFO (first-in-first-out) manner.
 * <p>The <em>head</em> of the queue is that
 * element which would be removed by a call to {@link Queue#remove remove()} or
 * {@link Queue#poll poll()}.  In a FIFO queue, all new elements are inserted at
 * the <em>tail</em> of the queue.
 *
 * <p>The {@link Queue#offer offer()} method inserts an element if possible,
 * otherwise returning {@code false}.  This differs from the {@link
 * java.util.Collection#add Collection.add()} method, which can fail to
 * add an element only by throwing an unchecked exception.  The
 * {@code offer} method is designed for use when failure is a normal,
 * rather than exceptional occurrence.
 *
 * <p>The {@link Queue#remove remove()} and {@link Queue#poll poll()} methods remove and
 * return the head of the queue.
 * Exactly which element is removed from the queue is a
 * function of the queue's ordering policy, which differs from
 * implementation to implementation. The {@code remove()} and
 * {@code poll()} methods differ only in their behavior when the
 * queue is empty: the {@code remove()} method throws an exception,
 * while the {@code poll()} method returns {@code null}.
 *
 * <p>{@link Deque} is a linear collection that supports element insertion and removal at
 * both ends.
 * The name <i>deque</i> is short for "double ended queue" and is usually pronounced "deck".
 *
 * <p>Deques can also be used as LIFO (Last-In-First-Out) stacks.
 * This interface should be used in preference to the legacy {@link java.util.Stack Stack} class.
 * When a deque is used as a stack, elements are pushed and popped from the
 * beginning of the deque.
 *
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Queue.html">Queue</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Deque.html">Deque</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/PriorityQueue.html">PriorityQueue</a>
 * @see <a href=https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/LinkedList.html">LinkedList</a>
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/ArrayDeque.html">ArrayDeque</a>
 *
 */
public class Main {
    private static final List<String> lst = List.of("Emma", "Jade", "Louise", "Alice");

    private static void queueStory(Queue<String> toiletQueue) {
        for (var e : lst) {
            System.out.println(e + " decides to queue up");
            toiletQueue.offer(e);
        }

        System.out.print("\nThe toilet is unfortunately closed. In the queue we have: ");
        System.out.println(toiletQueue);

        System.out.println("\nThe toilet has just been opened and:");
        while (toiletQueue.size() > 0) {
            System.out.println(toiletQueue.remove() + " goes to the toilet");
        }
    }

    private static void demo1() {
        System.out.println("demo1...");
        Queue<String> toiletQueue = new LinkedList<>();
        queueStory(toiletQueue);
    }

    /** Priority queue - natural (alphabetical) order */
    private static void demo2() {
        System.out.println("\ndemo2...");
        Queue<String> toiletQueue = new PriorityQueue<>();
        queueStory(toiletQueue);
    }

    /** Priority queue - reversed (alphabetical) order */
    private static void demo3() {
        System.out.println("\ndemo3...");
        Queue<String> toiletQueue = new PriorityQueue<>(Collections.reverseOrder());
        queueStory(toiletQueue);
    }

    /** Deque: adding/removing elements at the beginning */
    private static void demo4() {
        System.out.println("\ndemo4...");
        Deque<Integer> deq = new ArrayDeque<>();

        deq.addFirst(10);
        deq.push(20);
        deq.addFirst(30);
        System.out.println(deq);

        deq.removeFirst();
        System.out.println(deq);

        deq.pop();
        System.out.println(deq);

        deq.removeFirst();
        System.out.println(deq);
    }

    /** Deque: adding/removing elements at the end */
    private static void demo5() {
        System.out.println("\ndemo5...");
        Deque<Integer> deq = new ArrayDeque<>();

        deq.addLast(10);
        deq.offerLast(20);
        deq.offer(30);
        System.out.println(deq);

        deq.removeLast();
        System.out.println(deq);

        deq.pollLast();
        System.out.println(deq);

        deq.removeLast();
        System.out.println(deq);
    }

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
        demo4();
        demo5();
    }
}
