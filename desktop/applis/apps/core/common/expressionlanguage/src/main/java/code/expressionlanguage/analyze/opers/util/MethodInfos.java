package code.expressionlanguage.analyze.opers.util;

import code.util.CustList;

public final class MethodInfos {
    private final CustList<CustList<MethodInfo>> list;
    private final ScopeFilter scopeFilter;

    public MethodInfos(CustList<CustList<MethodInfo>> _l, ScopeFilter _sc) {
        this.list = _l;
        this.scopeFilter = _sc;
    }

    public CustList<CustList<MethodInfo>> getList() {
        return list;
    }

    public ScopeFilter getScopeFilter() {
        return scopeFilter;
    }
}
