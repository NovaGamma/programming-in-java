package agh.ii.prinjava.lab03.lst03_09;

import java.io.Serializable;

class SerializableCls1 implements Serializable {
    private String name;

    public SerializableCls1(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SerializableCls1{" + "name='" + name + '\'' + '}';
    }
}
