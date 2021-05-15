package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.functionid.ClassMethodId;

public final class ClassMethodIdMemberIdTypeFct {
    private AnaFormattedRootBlock implicit;
    private MemberId memberId = new MemberId();
    private AnaTypeFct function;

    public void infos(ClassMethodIdReturn _id) {
        implicit = _id.getFormattedType();
        memberId = _id.getMemberId();
        function = _id.getPair();
    }

    public void infos(OperatorConverter _id, AnalyzedPageEl _page) {
        String className_ = _id.getFormattedType().getFormatted();
        if (AnaTypeUtil.isPrimitive(className_, _page)) {
            return;
        }
        implicit = _id.getFormattedType();
        memberId = _id.getMemberId();
        function = _id.getFunction();
    }

    public void infos(AnaFormattedRootBlock _id, MemberId _memberId, AnaTypeFct _function) {
        implicit = _id;
        memberId = _memberId;
        function = _function;
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
