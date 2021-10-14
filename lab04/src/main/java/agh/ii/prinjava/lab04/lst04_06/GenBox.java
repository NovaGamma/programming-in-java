package agh.ii.prinjava.lab04.lst04_06;

/**
 * A generic type (type constructor) is invariant on its type parameter(s).
 * <p>Although B is a subtype of A (as defined above), {@code GenBox<B>} is NOT a subtype of {@code GenBox<A>}
 *
 * @see <a href="https://en.wikipedia.org/wiki/Covariance_and_contravariance_(computer_science)">Covariance and contravariance</a>
 */
class GenBox<T> {
    private T x;

    public GenBox(T x) {
        this.x = x;
    }

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "BoxForT{" + "x=" + x + '}';
    }
}
