package agh.ii.prinjava.lab02.lst02_04;

record Actor(String name, String surname) {
    @Override
    public String toString() {
        return name + " " + surname;
    }
}

/**
 * An enum class is a class declared with abbreviated syntax that defines a small set of named class instances.
 * <p>A classic example of an enumerated type that defines a list of enumerated values corresponding
 * to the seasons of the year.
 */
enum Season {
    SPRING, SUMMER, AUTUMN, WINTER
}

enum TroisCouleurs {
    BLEU, BLANC, ROUGE
}

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

/**
 * Just for help in the understanding of the internals of enum types
 */
final class ThreeColorsEnumLike {
    public final static ThreeColorsEnumLike BLUE =
            new ThreeColorsEnumLike("BLUE", new Actor("Juliette", "Binoche"));

    public final static ThreeColorsEnumLike WHITE =
            new ThreeColorsEnumLike("WHITE", new Actor("Zbigniew", "Zamachowski"));

    public final static ThreeColorsEnumLike RED =
            new ThreeColorsEnumLike("BLUE", new Actor("Irene", "Jacob"));

    // The constructors Should be private
    private ThreeColorsEnumLike(String name, Actor leadingActor) {
        this.name = name;
        this.leadingActor = leadingActor;
    }

    // Why this should be static?
    private final static ThreeColorsEnumLike[] values = {BLUE, WHITE, RED};

    public static ThreeColorsEnumLike[] values() {
        return values;
    }

    // And why this should not?
    private final String name;

    public String name() {
        return name;
    }

    private final Actor leadingActor;

    public Actor getLeadingActor() {
        return leadingActor;
    }
}

public class Main {
    private static Actor acteurPrincipal(TroisCouleurs film) {
        return switch (film) {
            case BLEU -> new Actor("Juliette", "Binoche");
            case BLANC -> new Actor("Zbigniew", "Zamachowski");
            case ROUGE -> new Actor("Irene", "Jacob");
        };
    }

    private static String movieDesc(String movie, Actor leadActor) {
        return "The leadingActor in " + movie + " is " + leadActor.toString();
    }

    public static void demo1() {
        for (ThreeColors cl : ThreeColors.values()) {
            System.out.println(movieDesc(cl.name(), cl.getLeadingActor()));
        }
    }

    public static void demo2() {
        for (ThreeColorsEnumLike cl : ThreeColorsEnumLike.values()) {
            System.out.println(movieDesc(cl.name(), cl.getLeadingActor()));
        }
    }

    public static void demo3() {
        for (TroisCouleurs cl : TroisCouleurs.values()) {
            System.out.println(movieDesc(cl.name(), acteurPrincipal(cl)));
        }
    }

    public static void main(String[] args) {
        demo1();
        demo2();
        demo3();
    }
}
