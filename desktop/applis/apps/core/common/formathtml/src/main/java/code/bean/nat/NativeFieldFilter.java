package code.bean.nat;

import code.expressionlanguage.analyze.AbstractFieldFilter;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class NativeFieldFilter implements AbstractFieldFilter {
    @Override
    public void tryAddField(ScopeFilterType _scope, ScopeFilterField _scopeField, StringMap<FieldResult> _ancestors, AnalyzedPageEl _page) {
        AnaGeneType root_ = _scope.getTypeInfo().getRoot();
        if (!(root_ instanceof SpecialNatClass)) {
            return;
        }
        String name_ = _scopeField.getName();
        for (StandardField f: ((SpecialNatClass) root_).getFields()) {
            if (StringUtil.quickEq(f.getFieldName(), name_)) {
                String type_ = f.getImportedClassName();
                FieldResult res_ = new FieldResult();
                res_.setFileName("");
                String declaringBaseClass_ = StringExpUtil.getIdFromAllTypes(root_.getFullName());
                ClassField classField_ = new ClassField(declaringBaseClass_, name_);
                res_.setValOffset(-1);
                res_.setClassField(classField_);
                res_.setDeclaringClass(root_.getFullName());
                res_.setStaticField(false);
                res_.setFinalField(false);
                res_.setType(type_);
                res_.setRealType(type_);
                res_.setAnc(0);
                res_.setStatus(SearchingMemberStatus.UNIQ);
                _ancestors.addEntry(_scope.getFullName(),res_);
                return;
            }
        }
    }

    @Override
    public void fetchParamClassMethods(ScopeFilterType _retRef, CustList<MethodInfo> _methods, AnalyzedPageEl _page) {
        for (StandardMethod e: ((StandardType) _retRef.getTypeInfo().getRoot()).getMethods()) {
            _methods.add(OperationNode.getMethodInfo(e, 0, _retRef.getFormatted().getFormatted(), _page, e.getId(), e.getImportedReturnType(), new FormattedFilter()));
        }
    }

}
