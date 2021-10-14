package agh.ii.prinjava.lab04.lst04_01;

/**
 * Motivational example, breaking the DRY principle/rule
 *
 * @see <a href="https://wiki.c2.com/?DontRepeatYourself">Do not Repeat Yourself</a>
 */
class BoxForInt {
    private int x;

    public BoxForInt(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "BoxOfInt{" + "x=" + x + '}';
    }
}