package agh.ii.prinjava.lab04.lst04_01;

/**
 * Almost the same as above, Int -> String
 */
class BoxForString {
    private String x;

    public BoxForString(String x) {
        this.x = x;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "BoxOfString{" + "x='" + x + '\'' + '}';
    }
}