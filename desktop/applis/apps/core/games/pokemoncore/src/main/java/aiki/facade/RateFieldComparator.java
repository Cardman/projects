package aiki.facade;

import code.maths.Rate;


public final class RateFieldComparator extends BigNbFieldComparator<Rate> {

    @Override
    protected int cmp(Rate _first, Rate _second) {
        return _first.cmp(_second);
    }
}
