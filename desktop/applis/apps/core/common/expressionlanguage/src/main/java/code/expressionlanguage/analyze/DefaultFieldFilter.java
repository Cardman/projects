package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.AnaGeneType;
import code.util.CustList;
import code.util.StringMap;

public final class DefaultFieldFilter implements AbstractFieldFilter {
    @Override
    public void tryAddField(ScopeFilterType _scope, FieldInfo _fi, boolean _aff, String _name, StringMap<FieldResult> _ancestors, AnaGeneType _root, AnalyzedPageEl _page) {
        OperationNode.tryAddField(_fi, _aff, _name, _ancestors, _root, _page, _scope);
    }

    @Override
    public void fetchParamClassMethods(ScopeFilterType _retRef, CustList<MethodInfo> _methods, AnaGeneType _g, AnalyzedPageEl _page) {
        OperationNode.fetchParamClassMethods(_retRef, _methods, _g, _page);
    }

    @Override
    public FieldInfo getFieldInfo(AnaGeneType _anaGeneType, String _fieldName) {
        return ContextUtil.getFieldInfo(_anaGeneType, _fieldName);
    }
}
