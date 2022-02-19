package code.expressionlanguage.common;

public final class DefTypePairHash implements AbstractTypePairHash {
    @Override
    public boolean areTypePairs(Matching _m, Matching _n) {
        return AbstractInheritProcess.areTypePairs(_m, _n);
    }
}
