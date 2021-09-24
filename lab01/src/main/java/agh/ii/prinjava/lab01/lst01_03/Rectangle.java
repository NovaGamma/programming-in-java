package agh.ii.prinjava.lab01.lst01_03;

public class Rectangle extends Shape {
    private double w;
    private double h;

    @Override
    public double area() {
        return w * h;
    }

    @Override
    public double perimeter() {
        return 2 * (w + h);
    }

    /**
     * Overloaded constructor (ad-hoc polymorphism, compile-time)
     */
    public Rectangle(double w, double h, boolean filled) {
        super(filled); // -> Shape(boolean filled)
        this.w = w;
        this.h = h;
    }

    /**
     * Overloaded constructor (ad-hoc polymorphism, compile-time)
     */
    public Rectangle(double w, double h) {
        this(w, h, true); // -> Rectangle(double w, double h, boolean filled)
    }
}
