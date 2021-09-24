package agh.ii.prinjava.lab02.lst02_06;

/**
 * An <i>abstract class</i> is a class that is:
 * <ul>
 *     <li>(either) incomplete (as {@link Shape}); typical case, <i>Abstract Data Type</i>)</li>
 *     <li>(or) to be considered incomplete (as {@link ToBeConsideredAbstract}); unusual, but possible</li>
 * </ul>
 *
 * <p>A class should be declared abstract only if the intent is that subclasses
 * can be created to complete the implementation
 * <p>To prevent instantiation of a class it is recommended to declare only one parameterless
 * constructor that is private
 *
 * <p> An <i>abstract method</i> declaration introduces the method (as a member), providing its:
 * <ul>
 *     <li>signature</li>
 *     <li>result type</li>
 *     <li>throws clause, if any</li>
 * </ul>
 * but <i>does not provide an implementation</i>
 *
 * <p><i>Note</i>: abstract and final cannot go together (since it makes no sense)
 */
abstract class Shape {
    abstract double area();

    public Shape() {
    }
}

class Circle extends Shape {
    private double r;

    @Override
    double area() {
        System.out.println("Circle.area()");
        return Math.PI * r * r;
    }

    public Circle(double r) {
        this.r = r;
    }
}

/**
 * No abstract methods, yet the class is to be considered abstract
 */
abstract class ToBeConsideredAbstract {
    int x;

    void m1() {
        System.out.println("ToBeConsideredAbstract.m1()");
    }
}

public class Main {
    public static void main(String[] args) {
        Shape s1 = new Circle(2);
        System.out.printf("s1Area = %.2f\n", s1.area());

        ToBeConsideredAbstract tbca; // OK, it's just a reference
        //tbca = new ToBeConsideredAbstract(); // 'ToBeConsideredAbstract' is abstract; cannot be instantiated
    }
}
