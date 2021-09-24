package agh.ii.prinjava.lab01.lst01_03;

public class Circle extends Shape {
    private double r;

    @Override
    public double area() {
        return Math.PI * r * r;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * r;
    }

    /**
     * Overloaded constructor (ad-hoc polymorphism, compile-time)
     */
    public Circle(double r, boolean filed) {
        super(filed); // -> Shape(boolean filled)
        this.r = r;
    }

    /**
     * Overloaded constructor (ad-hoc polymorphism, compile-time)
     */
    public Circle(double r) {
        this(r, true); // -> Circle(double r, boolean filed)
    }
}
