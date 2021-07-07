package code.expressionlanguage.common;

public final class DisplayedStrings {
    private String trueString;
    private String falseString;
    private String nullString;
    private String nullCoverString;
    private String notNullCoverString;

    private String staticString;
    private String staticCallString;
    private String infinity;
    private String exponent;
    private String nan;

    public String getTrueString() {
        return trueString;
    }
    public void setTrueString(String _trueString) {
        trueString = _trueString;
    }
    public String getFalseString() {
        return falseString;
    }
    public void setFalseString(String _falseString) {
        falseString = _falseString;
    }
    public String getNullString() {
        return nullString;
    }
    public void setNullString(String _nullString) {
        nullString = _nullString;
    }

    public String getNullCoverString() {
        return nullCoverString;
    }

    public void setNullCoverString(String _nullCoverString) {
        nullCoverString = _nullCoverString;
    }

    public String getNotNullCoverString() {
        return notNullCoverString;
    }

    public void setNotNullCoverString(String _notNullCoverString) {
        notNullCoverString = _notNullCoverString;
    }
    public String getStaticString() {
        return staticString;
    }

    public void setStaticString(String _staticString) {
        staticString = _staticString;
    }

    public String getStaticCallString() {
        return staticCallString;
    }

    public void setStaticCallString(String _staticCallString) {
        staticCallString = _staticCallString;
    }

    public String getInfinity() {
        return infinity;
    }

    public void setInfinity(String _infinity) {
        this.infinity = _infinity;
    }

    public String getExponent() {
        return exponent;
    }

    public void setExponent(String _exponent) {
        this.exponent = _exponent;
    }

    public String getNan() {
        return nan;
    }

    public void setNan(String _nan) {
        this.nan = _nan;
    }
}
