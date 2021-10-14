package agh.ii.prinjava.lab04.lst04_03;

class Rectangle extends Shape {
    private final double w;
    private final double h;

    public Rectangle(double w, double h) {
        this.w = w;
        this.h = h;
    }

    @Override
    double area() {
        return w * h;
    }
}
