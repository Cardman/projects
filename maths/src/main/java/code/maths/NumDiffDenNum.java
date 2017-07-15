package code.maths;

public final class NumDiffDenNum {

    private final LgInt numerator;

    private final LgInt diffDenNumerator;

    public NumDiffDenNum(LgInt _numerator, LgInt _diffDenNumerator) {
        numerator = _numerator;
        diffDenNumerator = _diffDenNumerator;
    }

    public LgInt getNumerator() {
        return numerator;
    }

    public LgInt getDiffDenNumerator() {
        return diffDenNumerator;
    }
}
