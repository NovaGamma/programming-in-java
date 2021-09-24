package agh.ii.prinjava.lab01.lst01_01;

/**
 * Encapsulation demo
 */
public class Main {
    public static void main(String[] args) {
        HelloEncapsulation enDemo = new HelloEncapsulation(42);

        // int propVal = enDemo.propVal; // (!)
        // To read or to modify the value of "propVal", we cannot access it directly
        // because of the access modifier ("private"). Instead:
        int propVal = enDemo.getPropVal();
        propVal++;
        enDemo.setPropVal(propVal);
    }
}