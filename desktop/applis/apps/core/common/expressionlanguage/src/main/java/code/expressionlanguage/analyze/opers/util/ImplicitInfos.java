package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.functionid.ClassMethodId;

public final class ImplicitInfos {
    private ClassMethodId idMethod;
    private int rootNumber = -1;
    private int memberNumber = -1;

    public ClassMethodId getIdMethod() {
        return idMethod;
    }

    public void setIdMethod(ClassMethodId idMethod) {
        this.idMethod = idMethod;
    }

    public int getRootNumber() {
        return rootNumber;
    }

    public void setRootNumber(int rootNumber) {
        this.rootNumber = rootNumber;
    }

    public int getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(int memberNumber) {
        this.memberNumber = memberNumber;
    }
}
