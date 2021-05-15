package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;

public final class ImplicitInfos {
    private AnaFormattedRootBlock idMethod;
    private MemberId memberId = new MemberId();
    private AnaTypeFct function;

    public AnaFormattedRootBlock getIdMethod() {
        return idMethod;
    }

    public void setIdMethod(AnaFormattedRootBlock _idMethod) {
        this.idMethod = _idMethod;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public void setMemberId(MemberId _memberId) {
        this.memberId = _memberId;
    }

    public AnaTypeFct getFunction() {
        return function;
    }

    public void setFunction(AnaTypeFct _function) {
        this.function = _function;
    }
}
