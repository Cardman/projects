package code.expressionlanguage.exec.annotation;

final class DisplayNb {
    private final String infinity;
    private final String exponent;
    private final String nan;
    private final String unicode;

    DisplayNb(String _infinity, String _exponent, String _nan, String _u) {
        this.infinity = _infinity;
        this.exponent = _exponent;
        this.nan = _nan;
        this.unicode = _u;
    }

    String getExponent() {
        return exponent;
    }

    String getNan() {
        return nan;
    }

    String getInfinity() {
        return infinity;
    }

    String getUnicode() {
        return unicode;
    }
}
