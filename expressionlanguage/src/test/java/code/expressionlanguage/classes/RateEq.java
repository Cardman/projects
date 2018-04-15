package code.expressionlanguage.classes;
import code.util.StringList;
import code.util.comparators.ComparatorBoolean;

public final class RateEq {

    private static final String ZERO = "0";

    private final boolean zero;

    private final String string;

    public RateEq(String _value) {
        string = _value;
        zero = StringList.quickEq(_value,ZERO);
    }

    public RateEq(RateEq _rateEq) {
        string = _rateEq.string;
        zero = _rateEq.zero;
    }

    public boolean eq(RateEq _obj) {
        if (ComparatorBoolean.diff(zero, _obj.zero)) {
            return false;
        }
        return StringList.quickEq(string, _obj.string);
    }

    public static RateEq newRate(String _value) {
        return new RateEq(_value);
    }

    public boolean isZero() {
        return zero;
    }
}
