package code.expressionlanguage.analyze.util;

import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.functionid.ClassMethodId;

public final class ClassMethodIdAncestor {
    private final AnaGeneType gt;
    private final ClassMethodId classMethodId;
    private final int ancestor;

    public ClassMethodIdAncestor(AnaGeneType _gene, ClassMethodId _classMethodId, int _ancestor) {
        gt = _gene;
        classMethodId = _classMethodId;
        ancestor = _ancestor;
    }

    public AnaGeneType getGt() {
        return gt;
    }

    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }

    public int getAncestor() {
        return ancestor;
    }

}
