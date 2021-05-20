package code.formathtml.nat;

import code.expressionlanguage.analyze.AbstractFieldFilter;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.util.CustList;
import code.util.StringMap;

public final class NativeTestFieldFilter implements AbstractFieldFilter {
    @Override
    public void tryAddField(ScopeFilterType _scope, ScopeFilterField _scopeField, StringMap<FieldResult> _ancestors, AnalyzedPageEl _page) {

    }

    @Override
    public void fetchParamClassMethods(ScopeFilterType _retRef, CustList<MethodInfo> _methods, AnalyzedPageEl _page) {
        for (StandardMethod e: ((StandardType) _retRef.getTypeInfo().getRoot()).getMethods()) {
            _methods.add(OperationNode.getMethodInfo(e, 0, _retRef.getFormatted().getFormatted(), _page, e.getId(), e.getImportedReturnType(), new FormattedFilter()));
        }
    }

}
