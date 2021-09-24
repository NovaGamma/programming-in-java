package agh.ii.prinjava.lab01.lst01_07;

public class Main {
    public static void main(String[] args) {
        // HelloFactoryMethod hfm0 = new HelloFactoryMethod(); // 'HelloFactoryMethod()' has private access

        HelloFactoryMeth hfm1 = HelloFactoryMeth.create(10);
        System.out.println();

        HelloFactoryMeth hfm2 = HelloFactoryMeth.create("HelloFactory");
        System.out.println();

        HelloFactoryMeth hfm3 = HelloFactoryMeth.buildFrom(hfm2);
    }
}
