package code.expressionlanguage.opers.util;


public class BooleanAssignment extends Assignment {

    private boolean assignedAfterWhenFalse;
    private boolean assignedAfterWhenTrue;
    private boolean unassignedAfterWhenFalse;
    private boolean unassignedAfterWhenTrue;
    public BooleanAssignment copy() {
        BooleanAssignment cp_;
        cp_ = new BooleanAssignment();
        cp_.setAssignedAfterWhenFalse(assignedAfterWhenFalse);
        cp_.setAssignedAfterWhenTrue(assignedAfterWhenTrue);
        cp_.setUnassignedAfterWhenFalse(unassignedAfterWhenFalse);
        cp_.setUnassignedAfterWhenTrue(unassignedAfterWhenTrue);
        return cp_;
    }

    public boolean isAssignedAfterWhenFalse() {
        return assignedAfterWhenFalse;
    }
    public void setAssignedAfterWhenFalse(boolean _assignedAfterWhenFalse) {
        assignedAfterWhenFalse = _assignedAfterWhenFalse;
    }
    public boolean isAssignedAfterWhenTrue() {
        return assignedAfterWhenTrue;
    }
    public void setAssignedAfterWhenTrue(boolean _assignedAfterWhenTrue) {
        assignedAfterWhenTrue = _assignedAfterWhenTrue;
    }
    public boolean isUnassignedAfterWhenFalse() {
        return unassignedAfterWhenFalse;
    }
    public void setUnassignedAfterWhenFalse(boolean _unassignedAfterWhenFalse) {
        unassignedAfterWhenFalse = _unassignedAfterWhenFalse;
    }
    public boolean isUnassignedAfterWhenTrue() {
        return unassignedAfterWhenTrue;
    }
    public void setUnassignedAfterWhenTrue(boolean _unassignedAfterWhenTrue) {
        unassignedAfterWhenTrue = _unassignedAfterWhenTrue;
    }
    @Override
    public boolean isAssignedAfter() {
        return isAssignedAfterWhenFalse() && isAssignedAfterWhenTrue();
    }
    @Override
    public boolean isUnassignedAfter() {
        return isUnassignedAfterWhenFalse() && isUnassignedAfterWhenTrue();
    }

    @Override
    public BooleanAssignment toBoolAssign() {
        return this;
    }

}
