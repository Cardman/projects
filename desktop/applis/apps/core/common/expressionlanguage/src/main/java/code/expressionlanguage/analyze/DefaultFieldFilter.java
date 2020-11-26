package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.FieldInfo;
import code.expressionlanguage.analyze.opers.util.FieldResult;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.util.ClassMethodIdAncestor;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class DefaultFieldFilter implements AbstractFieldFilter {
    @Override
    public void tryAddField(FieldInfo _fi, boolean _accessFromSuper, boolean _superClass, int _anc, boolean _static, boolean _aff, String _name, String _glClass, StringMap<FieldResult> _ancestors, String _cl, AnaGeneType _root, StringList _superTypesBase, StringMap<String> _superTypesBaseMap, AnalyzedPageEl _page) {
        OperationNode.tryAddField(_fi, _accessFromSuper, _superClass, _anc, _static, _aff, _name, _glClass, _ancestors, _cl, _root, _superTypesBase, _superTypesBaseMap, _page);
    }

    @Override
    public void fetchParamClassMethods(boolean _accessFromSuper, boolean _superClass, int _anc, MethodAccessKind _kind, ClassMethodIdAncestor _uniqueId, String _glClass, CustList<MethodInfo> _methods, String _cl, StringList _superTypesBase, StringMap<String> _superTypesBaseMap, String _fullName, AnaGeneType _g, AnalyzedPageEl _page) {
        OperationNode.fetchParamClassMethods(_accessFromSuper, _superClass, _anc, _kind, _uniqueId, _glClass, _methods, _cl, _superTypesBase, _superTypesBaseMap, _fullName, _g, _page);
    }

    @Override
    public FieldInfo getFieldInfo(AnaGeneType _anaGeneType, String _fieldName) {
        return ContextUtil.getFieldInfo(_anaGeneType, _fieldName);
    }
}
