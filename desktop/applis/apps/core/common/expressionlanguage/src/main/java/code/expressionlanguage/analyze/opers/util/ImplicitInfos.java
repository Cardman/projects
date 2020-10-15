package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.functionid.ClassMethodId;

public final class ImplicitInfos {
    private ClassMethodId idMethod;
    private int rootNumber = -1;
    private int memberNumber = -1;

    public ClassMethodId getIdMethod() {
        return idMethod;
    }

    public void setIdMethod(ClassMethodId _idMethod) {
        this.idMethod = _idMethod;
    }

    public int getRootNumber() {
        return rootNumber;
    }

    public void setRootNumber(int _rootNumber) {
        this.rootNumber = _rootNumber;
    }

    public int getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(int _memberNumber) {
        this.memberNumber = _memberNumber;
    }
}
