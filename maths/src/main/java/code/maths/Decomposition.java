package code.maths;
import code.util.EqList;
import code.util.PairEq;

public final class Decomposition {

    private final boolean prime;

    private final EqList<PairEq<LgInt,LgInt>> factors;

    public Decomposition(boolean _prime, EqList<PairEq<LgInt,LgInt>> _factors) {
        prime = _prime;
        factors = _factors;
    }

    public boolean isPrime() {
        return prime;
    }

    public EqList<PairEq<LgInt,LgInt>> getFactors() {
        return factors;
    }
}
