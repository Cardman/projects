package code.expressionlanguage.analyze.opers.util;

public final class MemberId {
    private int rootNumber = -1;
    private int memberNumber = -1;

    public int getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(int _memberNumber) {
        this.memberNumber = _memberNumber;
    }

    public int getRootNumber() {
        return rootNumber;
    }

    public void setRootNumber(int _rootNumber) {
        this.rootNumber = _rootNumber;
    }
}
