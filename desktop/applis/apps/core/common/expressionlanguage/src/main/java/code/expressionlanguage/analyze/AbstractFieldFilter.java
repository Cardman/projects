package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.opers.util.FieldInfo;
import code.expressionlanguage.analyze.opers.util.FieldResult;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.opers.util.ScopeFilterType;
import code.expressionlanguage.common.AnaGeneType;
import code.util.CustList;
import code.util.StringMap;

public interface AbstractFieldFilter {
    void tryAddField(ScopeFilterType _scope, FieldInfo _fi, boolean _aff, String _name, StringMap<FieldResult> _ancestors, AnaGeneType _root, AnalyzedPageEl _page);
    void fetchParamClassMethods(ScopeFilterType _retRef,
                                CustList<MethodInfo> _methods,
                                AnaGeneType _g, AnalyzedPageEl _page);
    FieldInfo getFieldInfo(AnaGeneType _anaGeneType, String _fieldName);
}
