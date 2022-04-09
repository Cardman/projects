package code.util.comparators;
import code.util.core.BoolVal;
import code.util.core.SortConstants;

public final class ComparatorBoolean{

    private ComparatorBoolean(){}

    public static boolean diff(boolean _e1, boolean _e2) {
        return _e1 != _e2;
    }

    public static BoolVal of(boolean _b){
        if (eq(_b,true)) {
            return BoolVal.TRUE;
        }
        return BoolVal.FALSE;
    }
    public static boolean eq(boolean _e1, boolean _e2) {
        return _e1 == _e2;
    }
    public static int cmp(BoolVal _b1, BoolVal _b2) {
        if (_b1 == BoolVal.TRUE && _b2== BoolVal.TRUE) {
            return SortConstants.EQ_CMP;
        }
        if (_b1 != BoolVal.TRUE && _b2 != BoolVal.TRUE) {
            return SortConstants.EQ_CMP;
        }
        if (_b1 == BoolVal.TRUE) {
            return SortConstants.SWAP_SORT;
        }
        return SortConstants.NO_SWAP_SORT;
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
