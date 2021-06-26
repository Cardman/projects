package code.expressionlanguage.exec.annotation;

final class DisplayNb {
    private final String infinity;
    private final String exponent;
    private final String nan;

    DisplayNb(String _infinity, String _exponent, String _nan) {
        this.infinity = _infinity;
        this.exponent = _exponent;
        this.nan = _nan;
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
}
