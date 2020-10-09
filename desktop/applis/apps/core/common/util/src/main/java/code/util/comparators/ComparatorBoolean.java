package code.util.comparators;
import code.util.core.SortConstants;

public final class ComparatorBoolean{

    private ComparatorBoolean(){}

    public static boolean diff(Boolean _e1, Boolean _e2) {
        return _e1 != _e2;
    }

    public static boolean eq(Boolean _e1, Boolean _e2) {
        return _e1 == _e2;
    }

    public static int cmp(boolean _b1, boolean _b2) {
        if (_b1 && _b2) {
            return SortConstants.EQ_CMP;
        }
        if (!_b1 && !_b2) {
            return SortConstants.EQ_CMP;
        }
        if (_b1) {
            return SortConstants.SWAP_SORT;
        }
        return SortConstants.NO_SWAP_SORT;
    }
}
