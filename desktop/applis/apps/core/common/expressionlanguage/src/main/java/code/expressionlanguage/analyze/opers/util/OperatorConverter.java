package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.functionid.ClassMethodId;

public final class OperatorConverter {
    private ClassMethodId symbol;
    private int rootNumber = -1;
    private int memberNumber = -1;
    private ClassMethodId test;
    private int rootNumberTest = -1;
    private int memberNumberTest = -1;

    public ClassMethodId getSymbol() {
        return symbol;
    }

    public void setSymbol(ClassMethodId _symbol) {
        symbol = _symbol;
    }

    public int getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(int memberNumber) {
        this.memberNumber = memberNumber;
    }

    public int getRootNumber() {
        return rootNumber;
    }

    public void setRootNumber(int rootNumber) {
        this.rootNumber = rootNumber;
    }

    public ClassMethodId getTest() {
        return test;
    }

    public void setTest(ClassMethodId _test) {
        test = _test;
    }

    public int getRootNumberTest() {
        return rootNumberTest;
    }

    public void setRootNumberTest(int rootNumberTest) {
        this.rootNumberTest = rootNumberTest;
    }

    public int getMemberNumberTest() {
        return memberNumberTest;
    }

    public void setMemberNumberTest(int memberNumberTest) {
        this.memberNumberTest = memberNumberTest;
    }

}
