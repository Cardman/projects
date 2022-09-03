package aiki.facade;

import code.maths.LgInt;


public final class LgIntFieldComparator extends BigNbFieldComparator<LgInt> {

    @Override
    protected int cmp(LgInt _first, LgInt _second) {
        return _first.cmp(_second);
    }
}
