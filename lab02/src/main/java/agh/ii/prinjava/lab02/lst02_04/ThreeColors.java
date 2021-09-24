package agh.ii.prinjava.lab02.lst02_04;

enum ThreeColors {
    // This must be written in the first line
    BLUE(new Actor("Juliette", "Binoche")),
    WHITE(new Actor("Zbigniew", "Zamachowski")),
    RED(new Actor("Irene", "Jacob"));

    private Actor leadingActor;

    /**
     * The constructor of an enumeration is always private (you can omit the private modifier),
     * It is a syntax error to declare an enum constructor as public or protected.
     */
    ThreeColors(Actor leadingActor) {
        this.leadingActor = leadingActor;
    }

    public Actor getLeadingActor() {
        return leadingActor;
    }
}
