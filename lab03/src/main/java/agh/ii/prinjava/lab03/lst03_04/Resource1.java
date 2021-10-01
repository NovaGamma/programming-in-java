package agh.ii.prinjava.lab03.lst03_04;

class Resource1 implements AutoCloseable {

    /**
     * Note: although the close method from {@link AutoCloseable} interface throws {@link Exception},
     * we can throw NO exception at all as the methods are covariant with respect to the exceptions
     */
    @Override
    public void close() {
        System.out.println("Resource1.close()");
    }

    public void doStuff() throws ChEx1 {
        System.out.println("Resource1.doStuff()");
        throw new ChEx1();
    }

    Resource1() {
        System.out.println("Resource1: the resource is being opened...");
    }
}
