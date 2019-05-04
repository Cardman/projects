package code.maths;

public final class PrimDivisor {
    private LgInt prime;
    private LgInt exponent;

    public PrimDivisor(LgInt _prime, LgInt _exponent) {
        prime = _prime;
        exponent = _exponent;
    }

    public LgInt getPrime() {
        return prime;
    }

    public LgInt getExponent() {
        return exponent;
    }
}
