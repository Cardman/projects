package code.util.comparators;
import code.util.ints.Comparing;

import code.util.CustList;

public final class ComparatorBoolean implements Comparing<Boolean> {

    @Override
    public int compare(Boolean _e1, Boolean _e2) {
        return cmp(_e1, _e2);
    }

    public static boolean diff(Boolean _e1, Boolean _e2) {
        return _e1 != _e2;
    }

    public static boolean eq(Boolean _e1, Boolean _e2) {
        return _e1 == _e2;
    }

    public static int cmp(boolean _b1, boolean _b2) {
        if (_b1 && _b2) {
            return CustList.EQ_CMP;
        }
        if (!_b1 && !_b2) {
            return CustList.EQ_CMP;
        }
        if (_b1) {
            return CustList.SWAP_SORT;
        }
        return CustList.NO_SWAP_SORT;
    }
}
