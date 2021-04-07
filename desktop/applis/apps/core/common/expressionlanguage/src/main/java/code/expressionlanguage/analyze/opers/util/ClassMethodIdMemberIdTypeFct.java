package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.functionid.ClassMethodId;

public final class ClassMethodIdMemberIdTypeFct {
    private String className="";
    private ClassMethodId implicit;
    private MemberId memberId = new MemberId();
    private AnaTypeFct function;

    public void infos(ClassMethodIdReturn _id) {
        String className_ = _id.getId().getClassName();
        className = className_;
        implicit = new ClassMethodId(className_,_id.getRealId());
        memberId = _id.getMemberId();
        function = _id.getPair();
    }

    public void infos(OperatorConverter _id, AnalyzedPageEl _page) {
        ClassMethodId symbol_ = _id.getSymbol();
        String className_ = symbol_.getClassName();
        if (AnaTypeUtil.isPrimitive(className_, _page)) {
            return;
        }
        implicit = symbol_;
        className = className_;
        memberId = _id.getMemberId();
        function = _id.getFunction();
    }

    public void infos(ClassMethodId _id, MemberId _memberId, AnaTypeFct _function) {
        implicit = _id;
        memberId = _memberId;
        function = _function;
    }
    public ClassMethodId getImplicit() {
        return implicit;
    }

    public String getClassName() {
        return className;
    }
    public MemberId getMemberId() {
        return memberId;
    }

    public AnaTypeFct getFunction() {
        return function;
    }
}
