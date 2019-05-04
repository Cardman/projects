package code.maths;
import code.util.CustList;

public final class Decomposition {

    private final boolean signum;

    private final CustList<PrimDivisor> factors;

    public Decomposition(boolean _signum, CustList<PrimDivisor> _factors) {
        signum = _signum;
        factors = _factors;
    }

    public boolean isPositive() {
        return signum == LgInt.SIGNE_POSITIF;
    }

    public CustList<PrimDivisor> getFactors() {
        return factors;
    }
}
