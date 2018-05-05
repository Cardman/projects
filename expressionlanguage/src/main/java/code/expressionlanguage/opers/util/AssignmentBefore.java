package code.expressionlanguage.opers.util;

public final class AssignmentBefore {
    private boolean assignedBefore;
    private boolean unassignedBefore;

    public Assignment assignAfter(boolean _boolType) {
        if (_boolType) {
            BooleanAssignment ba_ = new BooleanAssignment();
            if (isAssignedBefore()) {
                ba_.setAssignedAfterWhenFalse(true);
                ba_.setAssignedAfterWhenTrue(true);
            }
            if (isUnassignedBefore()) {
                ba_.setUnassignedAfterWhenFalse(true);
                ba_.setUnassignedAfterWhenTrue(true);
            }
            return ba_;
        }
        SimpleAssignment ba_ = new SimpleAssignment();
        if (isAssignedBefore()) {
            ba_.setAssignedAfter(true);
        }
        if (isUnassignedBefore()) {
            ba_.setUnassignedAfter(true);
        }
        return ba_;
    }
    public boolean isAssignedBefore() {
        return assignedBefore;
    }
    public void setAssignedBefore(boolean _assignedBefore) {
        assignedBefore = _assignedBefore;
    }
    public boolean isUnassignedBefore() {
        return unassignedBefore;
    }
    public void setUnassignedBefore(boolean _unassignedBefore) {
        unassignedBefore = _unassignedBefore;
    }

}
