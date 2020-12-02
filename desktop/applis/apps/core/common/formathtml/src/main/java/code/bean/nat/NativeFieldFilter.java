package code.bean.nat;

import code.expressionlanguage.analyze.AbstractFieldFilter;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.FieldInfo;
import code.expressionlanguage.analyze.opers.util.FieldResult;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.opers.util.ScopeFilterType;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class NativeFieldFilter implements AbstractFieldFilter {
    @Override
    public void tryAddField(ScopeFilterType _scope, FieldInfo _fi, boolean _aff, String _name, StringMap<FieldResult> _ancestors, AnaGeneType _root, AnalyzedPageEl _page) {
        OperationNode.addFieldInfo(_fi,0,_ancestors,_fi, false, _scope.getFullName());
    }

    @Override
    public void fetchParamClassMethods(ScopeFilterType _retRef, CustList<MethodInfo> _methods, AnaGeneType _g, AnalyzedPageEl _page) {
        for (StandardMethod e: ((StandardType) _g).getMethods()) {
            _methods.add(OperationNode.getMethodInfo(e,false,0, _retRef.getFormatted(), _page, e.getId(), e.getImportedReturnType(), e.getImportedReturnType()));
        }
    }

    @Override
    public FieldInfo getFieldInfo(AnaGeneType _anaGeneType, String _fieldName) {
        if (_anaGeneType == null) {
            return null;
        }
        for (StandardField f: ((StandardType) _anaGeneType).getFields()) {
            if (!StringUtil.quickEq(f.getFieldName(), _fieldName)) {
                continue;
            }
            String type_ = f.getImportedClassName();
            Accessed a_ = new Accessed(AccessEnum.PUBLIC,"", null);
            return FieldInfo.newFieldMetaInfo(_fieldName, _anaGeneType.getFullName(), type_, true, false, a_,-1);
        }
        return null;
    }
}
