package code.expressionlanguage.functionid;

import code.util.ints.Equallable;

public final class MethodIdAncestor implements Equallable<MethodIdAncestor> {
    private final MethodId classMethodId;
    private final int ancestor;

    public MethodIdAncestor(MethodId _classMethodId, int _ancestor) {
        classMethodId = _classMethodId;
        ancestor = _ancestor;
    }

    public MethodId getClassMethodId() {
        return classMethodId;
    }

    @Override
    public boolean eq(MethodIdAncestor _g) {
        return ancestor == _g.ancestor&&classMethodId.eq(_g.classMethodId);
    }
}
