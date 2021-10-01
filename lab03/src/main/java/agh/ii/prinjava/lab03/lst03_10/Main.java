package agh.ii.prinjava.lab03.lst03_10;

import java.util.Arrays;


public class Main {
    private static void shalowCloneDemo() {
        System.out.println("shalowCloneDemo...");

        ShallowCloner sc1 = new ShallowCloner();
        System.out.println("sc1: " + sc1);
        try {
            ShallowCloner sc1Clone = (ShallowCloner) sc1.clone(); // <- Object clone() throws...;
            System.out.println("sc1Clone: " + sc1Clone);

            System.out.println("\nUpdating the clone (setting ints[0] to 100)...");
            sc1Clone.setIntAtIdx(0, 100);

            System.out.println("sc1Clone: " + sc1Clone);
            System.out.println("sc1: " + sc1);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    private static void deepCloneDemo() {
        System.out.println("deepCloneDemo...");

        DeepCloner dc1 = new DeepCloner();
        System.out.println("dc1: " + dc1);
        try {
            DeepCloner dc1Clone = (DeepCloner) dc1.clone();
            System.out.println("dc1Clone: " + dc1Clone);

            System.out.println("\nUpdating the clone (setting ints[0] to 100)...");
            dc1Clone.setIntAtIdx(0, 100);

            System.out.println("dc1Clone: " + dc1Clone);
            System.out.println("dc1: " + dc1);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        shalowCloneDemo();
        System.out.println();
        deepCloneDemo();
    }
}
