package agh.ii.prinjava.lab02.lst02_09;

final class I31Factory {
    private I31Factory() {
    }

    static I31 build(String type) { // <- Enum (instead of string) would be better here!
        return switch (type) {
            case "I31Impl1" -> new I31Impl1();
            case "I31Impl2" -> new I31Impl2();
            default -> throw new IllegalArgumentException();
        };
    }
}
