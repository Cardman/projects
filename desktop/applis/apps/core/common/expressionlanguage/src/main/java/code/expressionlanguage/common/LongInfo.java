package code.expressionlanguage.common;

public final class LongInfo {
    private final long value;
    private final boolean valid;

    public LongInfo() {
        this (0,false);
    }

    public LongInfo(long _value) {
        this (_value,true);
    }
    private LongInfo(long _value, boolean _valid) {
        value = _value;
        valid = _valid;
    }

    public boolean outOfRange(long _min, long _max) {
        if (!valid) {
            return true;
        }
        return value < _min || value > _max;
    }
    public boolean isValid() {
        return valid;
    }

    public long getValue() {
        return value;
    }
}
