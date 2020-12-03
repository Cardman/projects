package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.AnaGeneType;
import code.util.CustList;
import code.util.StringMap;

public final class DefaultFieldFilter implements AbstractFieldFilter {
    @Override
    public void tryAddField(ScopeFilterType _scope, ScopeFilterField _scopeField, StringMap<FieldResult> _ancestors, AnalyzedPageEl _page) {
        FieldInfo fi_ = ContextUtil.getFieldInfo(_scopeField.getRoot(), _scopeField.getRootName(), _scopeField.getName());
        if (fi_ == null) {
            return;
        }
        OperationNode.tryAddField(fi_, _ancestors, _page, _scope, _scopeField);
    }

    @Override
    public void fetchParamClassMethods(ScopeFilterType _retRef, CustList<MethodInfo> _methods, AnaGeneType _g, AnalyzedPageEl _page) {
        OperationNode.fetchParamClassMethods(_retRef, _methods, _g, _page);
    }

}
