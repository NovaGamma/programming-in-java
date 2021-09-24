package agh.ii.prinjava.lab01.lst01_03;

/**
 * Note: abstract classes are one of the topics of the next lecture/lab class
 */
public abstract class Shape {
    private boolean filled;

    public abstract double area();

    public abstract double perimeter();

    public Shape(boolean filled) {
        this.filled = filled;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }
}
