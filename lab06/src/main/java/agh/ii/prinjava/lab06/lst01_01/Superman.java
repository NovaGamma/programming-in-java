package agh.ii.prinjava.lab06.lst01_01;

/**
 * Functional interface is just a specific type of interface, so it can be implemented
 */
class Superman implements Flyable {
    private static final Superman INSTANCE = new Superman();

    private Superman() {
    }

    public static Superman getInstance() {
        return INSTANCE;
    }

    @Override
    public void flyTo(double latitude, double longitude) {
        System.out.println("Superman is flying to (" + latitude + ", " + longitude + ")");
    }
}
