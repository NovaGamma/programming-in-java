package agh.ii.prinjava.lab01.lst01_10;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class C1 {
    private int i;
    private long l;
    private String s;

    public C1(int i, long l, String s) {
        this.i = i;
        this.l = l;
        this.s = s;
    }

    @Override
    public String toString() {
        return "C1{" +
                "i=" + i +
                ", l=" + l +
                ", s='" + s + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        C1 c1 = (C1) o;
        return i == c1.i && l == c1.l && Objects.equals(s, c1.s);
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, l, s);
    }
}

public class DemoClass {
    private double d;
    private List<Double> dbls;
    private int[] is;
    private C1 c1;

    @Override
    public String toString() {
        return "DemoClass{" +
                "d=" + d +
                ", dbls=" + dbls.toString() +
                ", is=" + Arrays.toString(is) +
                ", c1=" + c1.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DemoClass demoClass = (DemoClass) o;
        return Double.compare(demoClass.d, d) == 0 &&
                Objects.equals(dbls, demoClass.dbls) &&
                Arrays.equals(is, demoClass.is) &&
                Objects.equals(c1, demoClass.c1);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(d, dbls, c1);
        result = 31 * result + Arrays.hashCode(is);
        return result;
    }
}
