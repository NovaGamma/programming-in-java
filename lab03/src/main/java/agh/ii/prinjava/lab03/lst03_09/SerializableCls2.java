package agh.ii.prinjava.lab03.lst03_09;

import java.io.Serializable;

class SerializableCls2 implements Serializable {
    private String name;
    private transient int age; // <- a transient field, this will not be serialized (because it's not needed)

    public SerializableCls2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "SerializableCls2{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
}
