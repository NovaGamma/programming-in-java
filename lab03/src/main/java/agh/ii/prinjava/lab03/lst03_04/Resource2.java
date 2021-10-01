package agh.ii.prinjava.lab03.lst03_04;

class Resource2 implements AutoCloseable {

    @Override
    public void close() throws ChEx2 {
        System.out.println("Resource2.close()");
        throw new ChEx2();
    }

    public void doStuff() throws ChEx2 {
        System.out.println("Resource2.doStuff()");
        throw new ChEx2();
    }

    public Resource2() {
        System.out.println("Resource2: the resource is being opened...");
    }
}
