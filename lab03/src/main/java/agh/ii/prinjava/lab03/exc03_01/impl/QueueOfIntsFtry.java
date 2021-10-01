package agh.ii.prinjava.lab03.exc03_01.impl;

import agh.ii.prinjava.lab03.exc03_01.QueueOfInts;

final class QueueOfIntsFtry {
    private QueueOfIntsFtry() {
    }

    enum Impln {
        /**
         * (Custom) Linked List Based
         */
        L_LIST_B
    }

    static QueueOfInts create(Impln s) {
        return switch (s) {
            case L_LIST_B -> new LinkedListBasedImpl();
        };
    }
}
