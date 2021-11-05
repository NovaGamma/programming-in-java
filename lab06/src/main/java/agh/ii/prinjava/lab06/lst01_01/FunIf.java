package agh.ii.prinjava.lab06.lst01_01;

@FunctionalInterface
interface FunIf<T, R>{
    R apply(T t);
}