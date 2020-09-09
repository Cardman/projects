package code.expressionlanguage.analyze.util;


import code.expressionlanguage.functionid.MethodId;

public final class MethodIdAncestor {
    private final MethodId classMethodId;
    private final int ancestor;

    public MethodIdAncestor(MethodId _classMethodId, int _ancestor) {
        classMethodId = _classMethodId;
        ancestor = _ancestor;
    }

    public MethodId getClassMethodId() {
        return classMethodId;
    }

    public boolean eq(MethodIdAncestor _g) {
        return ancestor == _g.ancestor&&classMethodId.eq(_g.classMethodId);
    }
}
