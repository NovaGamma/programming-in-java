package agh.ii.prinjava.lab03.lst03_09;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;

class NonSerializableClass1 {
    private String name;

    public NonSerializableClass1(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NotSerializableClass1{" + "name='" + name + '\'' + '}';
    }
}

/**
 * <i>Serialization</i> - a process/mechanism of converting object's state along with its class information
 * into a byte stream (sequence of bytes), as well as the process of rebuilding those bytes into
 * a live object at some future time (often called <i>deserialization</i>).
 *
 * <p>Note: serialization used to be important, but in contemporary applications is not used very often
 *
 * <p>Serializable is only a marker interface - it simply allows the serialization mechanism to verify
 * that the class is able to be persisted
 */
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

/**
 * {@code transient} modifier prevents an instance variable from being serialized
 *
 * <p>Note: certain instance variables should not be serialized, e.g., database connections that
 * are meaningless when an object is reconstituted. Also, when an object keeps a cache of values,
 * it might be better to drop the cache and recompute it instead of storing it.
 */
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

/**
 * Not serializable because of a non-serializable field (nsc)
 */
class NonSerializableCls2 implements Serializable {
    private String name;
    private NonSerializableClass1 nsc; // <- a field of non-serializable type

    public NonSerializableCls2(String name, NonSerializableClass1 nsc) {
        this.name = name;
        this.nsc = nsc;
    }

    @Override
    public String toString() {
        return "NonSerializableCls2{" + "name='" + name + '\'' + ", nsc=" + nsc + '}';
    }
}

class SerializableCls3 implements Serializable {
    private String name;
    private transient NonSerializableClass1 nsc; // <- since it's not serializable, it must be transient

    public SerializableCls3(String name, NonSerializableClass1 nsc) {
        this.name = name;
        this.nsc = nsc;
    }

    @Override
    public String toString() {
        return "SerializableCls3{" + "name='" + name + '\'' + ", nsc=" + nsc + '}';
    }
}

/**
 * Serializable because of the parent class (which is serializable)
 */
class SerializableCls4 extends SerializableCls1 {
    public SerializableCls4(String name) {
        super(name);
    }
}

public class Main {
    private static final String demoFileName = "objects.ser";

    private static void deleteDemoFile() {
        try {
            Files.deleteIfExists(Path.of(demoFileName));
            System.out.println("\nDemo file deleted");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void demo1() {
        System.out.println("demo1...");

        try (var fout = new ObjectOutputStream(Files.newOutputStream(Path.of(demoFileName)))) {

            var obj = new NonSerializableClass1("nsObject");
            fout.writeObject(obj);

        } catch (IOException e) {
            System.out.println("demo1: IOException"); // NotSerializableException
            //e.printStackTrace();
        }
    }

    private static void demo2() {
        System.out.println("\ndemo2...");

        try (var fout = new ObjectOutputStream(Files.newOutputStream(Path.of(demoFileName)));
             var in = new ObjectInputStream(Files.newInputStream(Path.of(demoFileName)))) {

            var sObj = new SerializableCls1("sObject");
            System.out.println("sObj: " + sObj);
            fout.writeObject(sObj);
            System.out.println("sObj has just been serialized. Now trying to deserialize it...");
            var dsObj = (SerializableCls1) in.readObject();
            System.out.println("dsObj (deserialized object): " + dsObj);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("demo2: IOException | ClassNotFoundException");
            e.printStackTrace();
        }
    }

    private static void demo3() {
        System.out.println("\ndemo3...");

        try (var fout = new ObjectOutputStream(Files.newOutputStream(Path.of(demoFileName)));
             var in = new ObjectInputStream(Files.newInputStream(Path.of(demoFileName)))) {

            var sObj = new SerializableCls2("sObject", 42);
            System.out.println("sObj: " + sObj);
            fout.writeObject(sObj);
            System.out.println("sObj has just been serialized. Now trying to deserialize it...");
            var dsObj = (SerializableCls2) in.readObject();
            System.out.println("dsObj (deserialized object): " + dsObj);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("demo3: IOException | ClassNotFoundException");
            e.printStackTrace();
        }
    }

    private static void demo4() {
        System.out.println("\ndemo4...");

        try (var fout = new ObjectOutputStream(Files.newOutputStream(Path.of(demoFileName)))) {

            var nsObj = new NonSerializableCls2("nsObject", new NonSerializableClass1("Joe"));
            fout.writeObject(nsObj);

        } catch (IOException e) {
            System.out.println("demo4: IOException"); // NotSerializableException
            //e.printStackTrace();
        }
    }

    private static void demo5() {
        System.out.println("\ndemo5...");

        try (var fout = new ObjectOutputStream(Files.newOutputStream(Path.of(demoFileName)));
             var in = new ObjectInputStream(Files.newInputStream(Path.of(demoFileName)))) {

            var sObj = new SerializableCls3("sObject", new NonSerializableClass1("Joe"));
            System.out.println("sObj: " + sObj);
            fout.writeObject(sObj); //
            System.out.println("sObj has just been serialized. Now trying to deserialize it...");
            var dsObj = (SerializableCls3) in.readObject();
            System.out.println("dsObj (deserialized object): " + dsObj);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void demo6() {
        System.out.println("\ndemo6...");

        try (var fout = new ObjectOutputStream(Files.newOutputStream(Path.of(demoFileName)));
             var in = new ObjectInputStream(Files.newInputStream(Path.of(demoFileName)))) {

            var sObj = new SerializableCls4("sObject");
            System.out.println("sObj: " + sObj);
            fout.writeObject(sObj);
            System.out.println("sObj has just been serialized. Now trying to deserialize it...");
            var dsObj = (SerializableCls4) in.readObject();
            System.out.println("dsObj (deserialized object): " + dsObj);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        demo1();
        deleteDemoFile();

        demo2();
        deleteDemoFile();

        demo3();
        deleteDemoFile();

        demo4();
        deleteDemoFile();

        demo5();
        deleteDemoFile();

        demo6();
        deleteDemoFile();
    }
}
