package agh.ii.prinjava.lab02.lst02_05;

/**
 * Enums are a good example of the use of nested classes
 */
class Price {
    private long value;
    private Currency currency;

    enum Currency {
        EUR, GBP, USD
    }
}
