package agh.ii.prinjava.lab06.lst01_01;

@FunctionalInterface
interface IFactory<T> {
    T create(double a, double b);
}
