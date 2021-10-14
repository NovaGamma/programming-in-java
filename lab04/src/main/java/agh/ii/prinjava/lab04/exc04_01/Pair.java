package agh.ii.prinjava.lab04.exc04_01;

import java.util.Objects;

public class Pair<F,S> implements Cloneable {


    private F fst;
    private S snd;
    Pair(F fst, S snd){
        this.fst = fst;
        this.snd = snd;
    }

    public F getFst() {
        return fst;
    }

    public void setFst(F fst) {
        this.fst = fst;
    }

    public S getSnd() {
        return snd;
    }

    public void setSnd(S snd) {
        this.snd = snd;
    }

    @Override
    public String toString(){
        return "Pair{fst: " + fst + " snd: " + snd + "}";
    }

    public boolean equals(Pair<F, S> o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<F, S> pair = (Pair<F, S>) o;
        return Objects.equals(fst, pair.fst) && Objects.equals(snd, pair.snd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fst, snd);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Pair(fst, snd);
    }
}
