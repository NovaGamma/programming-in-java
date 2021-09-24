package agh.ii.prinjava.lab02.lst02_04;

record Actor(String name, String surname) {
    @Override
    public String toString() {
        return name + " " + surname;
    }
}
