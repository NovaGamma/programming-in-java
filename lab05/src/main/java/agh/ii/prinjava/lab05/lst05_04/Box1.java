package agh.ii.prinjava.lab05.lst05_04;

record Box1<T extends Comparable<T>>(T e) implements Comparable<Box1<T>> {
    @Override
    public int compareTo(Box1<T> that) {
        return e.compareTo(that.e);
    }
}
