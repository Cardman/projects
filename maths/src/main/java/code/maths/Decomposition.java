package code.maths;
import code.util.CustList;
import code.util.PairEq;

public final class Decomposition {

    private final boolean signum;

    private final CustList<PairEq<LgInt,LgInt>> factors;

    public Decomposition(boolean _signum, CustList<PairEq<LgInt,LgInt>> _factors) {
        signum = _signum;
        factors = _factors;
    }

    public boolean isPositive() {
        return signum == LgInt.SIGNE_POSITIF;
    }

    public CustList<PairEq<LgInt,LgInt>> getFactors() {
        return factors;
    }
}
