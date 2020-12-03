package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.opers.util.FieldResult;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.opers.util.ScopeFilterField;
import code.expressionlanguage.analyze.opers.util.ScopeFilterType;
import code.expressionlanguage.common.AnaGeneType;
import code.util.CustList;
import code.util.StringMap;

public interface AbstractFieldFilter {
    void tryAddField(ScopeFilterType _scope, ScopeFilterField _scopeField, StringMap<FieldResult> _ancestors, AnalyzedPageEl _page);
    void fetchParamClassMethods(ScopeFilterType _retRef,
                                CustList<MethodInfo> _methods,
                                AnaGeneType _g, AnalyzedPageEl _page);
}
