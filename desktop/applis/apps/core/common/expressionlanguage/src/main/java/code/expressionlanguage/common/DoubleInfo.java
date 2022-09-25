package code.expressionlanguage.common;

import code.util.core.NumberUtil;

public final class DoubleInfo {
    private final double value;
    private final boolean valid;
    private final boolean zero;

    public DoubleInfo() {
        this (Double.POSITIVE_INFINITY,false,false);
    }

    public DoubleInfo(double _value) {
        this (_value,true,false);
    }

    public DoubleInfo(double _value, boolean _zero) {
        this (_value,true,_zero);
    }
    private DoubleInfo(double _value, boolean _valid, boolean _zero) {
        value = _value;
        valid = _valid;
        zero = _zero;
    }

    public boolean outOfRange(double _min, double _max) {
        double absValue_ = NumberUtil.abs(value);
        return !isValid() || !zero&&(absValue_ < _min || absValue_ > _max);
    }
    public boolean isValid() {
        return valid;
    }

    public double getValue() {
        return value;
    }
}
