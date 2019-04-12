package code.maths;
import code.util.EqList;
import code.util.PairEq;

public final class Decomposition {

    private final boolean signum;

    private final EqList<PairEq<LgInt,LgInt>> factors;

    public Decomposition(boolean _signum, EqList<PairEq<LgInt,LgInt>> _factors) {
        signum = _signum;
        factors = _factors;
    }

    public boolean isPositive() {
        return signum == LgInt.SIGNE_POSITIF;
    }

    public EqList<PairEq<LgInt,LgInt>> getFactors() {
        return factors;
    }
}
