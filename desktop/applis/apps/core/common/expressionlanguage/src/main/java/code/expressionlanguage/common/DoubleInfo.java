package code.expressionlanguage.common;

public final class DoubleInfo {
    private final double value;
    private final boolean valid;

    public DoubleInfo() {
        this (Double.POSITIVE_INFINITY,false);
    }

    public DoubleInfo(double _value) {
        this (_value,true);
    }
    private DoubleInfo(double _value, boolean _valid) {
        value = _value;
        valid = _valid;
    }

    public boolean outOfRange(double _min, double _max) {
        if (!valid) {
            return true;
        }
        return value < _min || value > _max;
    }
    public boolean isValid() {
        return valid;
    }

    public double getValue() {
        return value;
    }
}
