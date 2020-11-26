package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.opers.util.FieldInfo;
import code.expressionlanguage.analyze.opers.util.FieldResult;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.util.ClassMethodIdAncestor;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public interface AbstractFieldFilter {
    void tryAddField(FieldInfo _fi, boolean _accessFromSuper, boolean _superClass, int _anc, boolean _static, boolean _aff, String _name, String _glClass, StringMap<FieldResult> _ancestors, String _cl, AnaGeneType _root, StringList _superTypesBase, StringMap<String> _superTypesBaseMap, AnalyzedPageEl _page);
    void fetchParamClassMethods(boolean _accessFromSuper, boolean _superClass, int _anc, MethodAccessKind _kind,
                                ClassMethodIdAncestor _uniqueId, String _glClass, CustList<MethodInfo> _methods,
                                String _cl, StringList _superTypesBase, StringMap<String> _superTypesBaseMap, String _fullName, AnaGeneType _g, AnalyzedPageEl _page);
    FieldInfo getFieldInfo(AnaGeneType _anaGeneType, String _fieldName);
}
