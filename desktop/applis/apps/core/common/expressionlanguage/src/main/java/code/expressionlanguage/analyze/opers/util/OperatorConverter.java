package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;
import code.expressionlanguage.functionid.ClassMethodId;

public final class OperatorConverter {
    private ClassMethodId symbol;
    private MemberId memberId = new MemberId();
    private AnaTypeFct function;
    private ClassMethodId test;
    private MemberId memberIdTest = new MemberId();
    private AnaTypeFct functionTest;

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

    public AnaTypeFct getFunction() {
        return function;
    }

    public void setFunction(AnaTypeFct _function) {
        this.function = _function;
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

    public AnaTypeFct getFunctionTest() {
        return functionTest;
    }

    public void setFunctionTest(AnaTypeFct _functionTest) {
        this.functionTest = _functionTest;
    }
}
