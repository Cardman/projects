package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;

public final class OperatorConverter {
    private MemberId memberId = new MemberId();
    private AnaTypeFct function;
    private AnaFormattedRootBlock formattedTypeTest;
    private MemberId memberIdTest = new MemberId();
    private AnaTypeFct functionTest;
    private AnaFormattedRootBlock formattedType;

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

    public MemberId getMemberIdTest() {
        return memberIdTest;
    }

    public void setMemberIdTest(MemberId _memberIdTest) {
        this.memberIdTest = _memberIdTest;
    }

    public AnaTypeFct getFunctionTest() {
        return functionTest;
    }

    public void setFunctionTest(AnaTypeFct _functionTest) {
        this.functionTest = _functionTest;
    }

    public AnaFormattedRootBlock getFormattedTypeTest() {
        return formattedTypeTest;
    }

    public void setFormattedTypeTest(AnaFormattedRootBlock _formattedTypeTest) {
        this.formattedTypeTest = _formattedTypeTest;
    }

    public AnaFormattedRootBlock getFormattedType() {
        return formattedType;
    }

    public void setFormattedType(AnaFormattedRootBlock _formattedType) {
        this.formattedType = _formattedType;
    }
}
