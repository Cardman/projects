package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.common.symbol.CommonOperSymbol;

public final class ClassMethodIdMemberIdTypeFct {
    private AnaFormattedRootBlock implicit = AnaFormattedRootBlock.defValue();
    private MemberId memberId = new MemberId();
    private AnaTypeFct function = new AnaTypeFct();
    private CommonOperSymbol symbol;

    public void infos(ClassMethodIdReturn _id) {
        implicit = _id.getFormattedType();
        memberId = _id.getParametrableContent().getMemberId();
        function = _id.getParametrableContent().getPair();
    }

    public void infos(OperatorConverter _id) {
        CommonOperSymbol v_ = _id.getFct().getVirtualCall();
        if (v_ != null) {
            symbol = v_;
            return;
        }
        infos(_id.getFct());
    }

    public CommonOperSymbol getSymbol() {
        return symbol;
    }

    public void setSymbol(CommonOperSymbol _s) {
        this.symbol = _s;
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
