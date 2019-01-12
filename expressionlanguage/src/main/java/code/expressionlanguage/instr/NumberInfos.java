package code.expressionlanguage.instr;

public final class NumberInfos {

    private boolean positive;

    private StringBuilder intPart;

    private boolean dotted;

    private StringBuilder decimalPart;

    private StringBuilder exponentialPart;

    private char suffix;

    private int firstPrintable;

    private int firstDigit;

    private int base;

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

    public void setDotted(boolean _dotted) {
        dotted = _dotted;
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

    public char getSuffix() {
        return suffix;
    }

    public void setSuffix(char _suffix) {
        suffix = _suffix;
    }

    public void setFirstPrintable(int _firstPrintable) {
        firstPrintable = _firstPrintable;
    }

    public void setFirstDigit(int _firstDigit) {
        firstDigit = _firstDigit;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int _base) {
        base = _base;
    }

}
