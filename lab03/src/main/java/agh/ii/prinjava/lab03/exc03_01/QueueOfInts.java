package agh.ii.prinjava.lab03.exc03_01;

public interface QueueOfInts {
    void enqueue(int x);

    int dequeue();

    default boolean isEmpty() {
        return numOfElems() == 0;
    }

    int numOfElems();

    int peek();
}
