package agh.ii.prinjava.lab03.lst03_08;


public class Main {
    private static void demo1(Object o) {
        if (o instanceof Reliable) {
            System.out.println("Reliable");
        } else {
            System.out.println("Unreliable");
        }
    }

    public static void main(String[] args) {
        demo1(new Dunedain());
        demo1(new Orc());
    }
}
