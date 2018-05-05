package code.expressionlanguage.opers.util;


public class BooleanAssignment extends Assignment {

    private boolean assignedAfterWhenFalse;
    private boolean assignedAfterWhenTrue;
    private boolean unassignedAfterWhenFalse;
    private boolean unassignedAfterWhenTrue;
    @Override
    public BooleanAssignment assign() {
        BooleanAssignment ba_ = new BooleanAssignment();
        if (isAssignedAfter()) {
            ba_.setAssignedAfterWhenFalse(true);
            ba_.setAssignedAfterWhenTrue(true);
        }
        if (isUnassignedAfter()) {
            ba_.setUnassignedAfterWhenFalse(true);
            ba_.setUnassignedAfterWhenTrue(true);
        }
        return ba_;
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

}
