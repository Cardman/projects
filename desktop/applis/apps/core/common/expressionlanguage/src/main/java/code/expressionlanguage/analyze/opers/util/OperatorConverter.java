package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.functionid.ClassMethodId;

public final class OperatorConverter {
    private ClassMethodId symbol;
    private MemberId memberId = new MemberId();
    private ClassMethodId test;
    private MemberId memberIdTest = new MemberId();

    public ClassMethodId getSymbol() {
        return symbol;
    }

    public void setSymbol(ClassMethodId _symbol) {
        symbol = _symbol;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public void setMemberId(MemberId _memberId) {
        this.memberId = _memberId;
    }

    public ClassMethodId getTest() {
        return test;
    }

    public void setTest(ClassMethodId _test) {
        test = _test;
    }

    public MemberId getMemberIdTest() {
        return memberIdTest;
    }

    public void setMemberIdTest(MemberId _memberIdTest) {
        this.memberIdTest = _memberIdTest;
    }

}
