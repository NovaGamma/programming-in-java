package agh.ii.prinjava.lab01.lst01_10;

import java.util.Objects;

public class HashCodeProblem {
    private String s1;
    private String s2;

    public HashCodeProblem(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashCodeProblem that = (HashCodeProblem) o;
        return Objects.equals(s1, that.s1) && Objects.equals(s2, that.s2);
    }
}
