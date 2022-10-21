package aiki.util;

import code.maths.Rate;

public final class PairRates {
    private final Rate front;
    private final Rate back;
    public PairRates(Rate _f, Rate _b) {
        front = _f;
        back = _b;
    }

    public Rate getBack() {
        return back;
    }

    public Rate getFront() {
        return front;
    }
}
