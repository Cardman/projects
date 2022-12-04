package code.bean.nat;

public final class NaBoSt extends NaNuSt {

    private static final NaBoSt FALSE = new NaBoSt();
    private static final NaBoSt TRUE = new NaBoSt();

    private NaBoSt() {
    }

    public static NaBoSt of(boolean _value) {
        return ofValue(_value);
    }

    private static NaBoSt ofValue(boolean _value) {
        if (_value) {
            return TRUE;
        }
        return FALSE;
    }

    public NaBoSt neg() {
        if (this == TRUE) {
            return FALSE;
        }
        return TRUE;
    }

    public static boolean isTrue(NaSt _other) {
        return _other == TRUE;
    }

    public static boolean isFalse(NaSt _other) {
        return _other == FALSE;
    }
}
