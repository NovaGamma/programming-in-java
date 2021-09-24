package agh.ii.prinjava.lab02.exc02_01.impl;

import agh.ii.prinjava.lab02.exc02_01.StackOfInts;

/**
 * Stack of integers factory
 */
public final class StackOfIntsFtry {
    private StackOfIntsFtry() {
    }

    enum Impln {
        /**
         * Array Based
         */
        ARRAY_B,

        /**
         * (Custom) Linked List Based
         */
        L_LIST_B //
    }

    static StackOfInts create(Impln s) {
        return switch (s) {
            case ARRAY_B -> new ArrayBasedImpl();
            case L_LIST_B -> new LinkedListBasedImpl();
        };
    }
}
