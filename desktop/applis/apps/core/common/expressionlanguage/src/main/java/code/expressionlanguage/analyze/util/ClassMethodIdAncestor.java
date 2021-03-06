package code.expressionlanguage.analyze.util;

import code.expressionlanguage.functionid.ClassMethodId;

public final class ClassMethodIdAncestor {
    private final ClassMethodId classMethodId;
    private final int ancestor;

    public ClassMethodIdAncestor(ClassMethodId _classMethodId, int _ancestor) {
        classMethodId = _classMethodId;
        ancestor = _ancestor;
    }

    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }

    public int getAncestor() {
        return ancestor;
    }

}
