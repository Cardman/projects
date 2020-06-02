package code.expressionlanguage.opers.util;

import code.util.CustList;

public final class MethodIdAncestors {
    private final MethodIdAncestor classMethodId;
    private CustList<MethodInfo> methodInfos = new CustList<MethodInfo>();

    public MethodIdAncestors(MethodIdAncestor _classMethodId) {
        classMethodId = _classMethodId;
    }

    public MethodIdAncestor getClassMethodId() {
        return classMethodId;
    }

    public CustList<MethodInfo> getMethodInfos() {
        return methodInfos;
    }
}
