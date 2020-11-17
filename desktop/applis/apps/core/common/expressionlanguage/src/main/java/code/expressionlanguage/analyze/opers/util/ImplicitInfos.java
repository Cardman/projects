package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.functionid.ClassMethodId;

public final class ImplicitInfos {
    private ClassMethodId idMethod;
    private MemberId memberId = new MemberId();

    public ClassMethodId getIdMethod() {
        return idMethod;
    }

    public void setIdMethod(ClassMethodId _idMethod) {
        this.idMethod = _idMethod;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public void setMemberId(MemberId _memberId) {
        this.memberId = _memberId;
    }

}
