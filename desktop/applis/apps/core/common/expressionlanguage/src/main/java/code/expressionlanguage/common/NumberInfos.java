package code.expressionlanguage.common;

public final class NumberInfos {

    public static final int WRAP_DOUBLE=1;
    public static final int WRAP_FLOAT=2;
    public static final int WRAP_LONG=3;
    public static final int WRAP_INT=4;
    public static final int WRAP_CHAR=5;
    public static final int WRAP_SHORT=6;
    public static final int WRAP_BYTE=7;
    public static final int PRIM_DOUBLE=8;
    public static final int PRIM_FLOAT=9;
    public static final int PRIM_LONG=10;
    public static final int PRIM_INT=11;
    public static final int PRIM_CHAR=12;
    public static final int PRIM_SHORT=13;
    public static final int PRIM_BYTE=14;
    private boolean positive;
    private boolean error;

    private StringBuilder intPart;

    private StringBuilder decimalPart;

    private StringBuilder exponentialPart;

    private int suffix;

    private int base;

    public boolean isError() {
        return error;
    }

    public void setError(boolean _error) {
        error = _error;
    }

    public boolean isPositive() {
        return positive;
    }

    public void setPositive(boolean _positive) {
        positive = _positive;
    }

    public StringBuilder getIntPart() {
        return intPart;
    }

    public void setIntPart(StringBuilder _intPart) {
        intPart = _intPart;
    }

    public StringBuilder getDecimalPart() {
        return decimalPart;
    }

    public void setDecimalPart(StringBuilder _decimalPart) {
        decimalPart = _decimalPart;
    }

    public StringBuilder getExponentialPart() {
        return exponentialPart;
    }

    public void setExponentialPart(StringBuilder _exponentialPart) {
        exponentialPart = _exponentialPart;
    }

    public int getSuffix() {
        return suffix;
    }

    public void setSuffix(int _suffix) {
        suffix = _suffix;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int _base) {
        base = _base;
    }

}
