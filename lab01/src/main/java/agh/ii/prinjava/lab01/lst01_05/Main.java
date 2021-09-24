package agh.ii.prinjava.lab01.lst01_05;

public class Main {
    /**
     * The call sequence is as follows:
     * <ol>
     *     <li>Anonymous static block (1)</li>
     *     <li>Anonymous static block (2)</li>
     *     <li>Anonymous block (1)</li>
     *     <li>Anonymous block (2)</li>
     *     <li>HelloObjectInit(double) constructor</li>
     *     <li>HelloObjectInit()</li>
     *     <li>Anonymous block (1)</li>
     *     <li>Anonymous block (2)</li>
     *     <li>HelloObjectInit(double) constructor</li>
     * </ol>
     * <p>
     * Note that:
     * <ul>
     *   <li>static blocks are executed only once, when the program starts (the corresponding class is loaded);
     *      they are executed even if no instances are created</li>
     *   <li>normal blocks are executed whenever a new instance is created</li>
     * </ul>
     */
    public static void main(String[] args) {
        HelloObjectInit hoe0; // not initialized!
        HelloObjectInit hoe1 = new HelloObjectInit();
        HelloObjectInit hoe2 = new HelloObjectInit(1.0);
    }
}
