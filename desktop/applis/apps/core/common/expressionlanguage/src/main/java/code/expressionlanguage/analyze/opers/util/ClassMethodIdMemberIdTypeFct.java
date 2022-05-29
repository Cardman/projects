package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;

public final class ClassMethodIdMemberIdTypeFct {
    private AnaFormattedRootBlock implicit;
    private MemberId memberId = new MemberId();
    private AnaTypeFct function;

    public void infos(ClassMethodIdReturn _id) {
        implicit = _id.getFormattedType();
        memberId = _id.getParametrableContent().getMemberId();
        function = _id.getParametrableContent().getPair();
    }

    public void infos(OperatorConverter _id, AnalyzedPageEl _page) {
        ClassMethodIdReturn fct_ = _id.getFct();
        String className_ = fct_.getFormattedType().getFormatted();
        if (AnaTypeUtil.isPrimitive(className_, _page)) {
            return;
        }
        infos(fct_);
    }

    public AnaFormattedRootBlock getImplicit() {
        return implicit;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public AnaTypeFct getFunction() {
        return function;
    }
}
