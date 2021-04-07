package code.bean.nat;

import code.expressionlanguage.analyze.AbstractFieldFilter;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.*;
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
    public void tryAddField(ScopeFilterType _scope, ScopeFilterField _scopeField, StringMap<FieldResult> _ancestors, AnalyzedPageEl _page) {
        AnaGeneType root_ = _scopeField.getRoot();
        String name_ = _scopeField.getName();
        for (StandardField f: ((StandardType) root_).getFields()) {
            if (StringUtil.quickEq(f.getFieldName(), name_)) {
                String type_ = f.getImportedClassName();
                Accessed a_ = new Accessed(AccessEnum.PUBLIC, "", null);
                FieldInfo fi_ = FieldInfo.newFieldMetaInfo(name_, root_.getFullName(), type_, true, false, a_, -1);
                OperationNode.addFieldInfo(fi_, 0, _ancestors, fi_, false, _scope.getFullName());
                return;
            }
        }
    }

    @Override
    public void fetchParamClassMethods(ScopeFilterType _retRef, CustList<MethodInfo> _methods, AnaGeneType _g, AnalyzedPageEl _page) {
        for (StandardMethod e: ((StandardType) _g).getMethods()) {
            _methods.add(OperationNode.getMethodInfo(e,false,0, _retRef.getFormatted(), _page, e.getId(), e.getImportedReturnType(), new FormattedFilter()));
        }
    }

}
