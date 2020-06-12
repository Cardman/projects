package code.expressionlanguage.assign.util;


public final class BooleanAssignment extends Assignment {

    private boolean assignedAfterWhenFalse;
    private boolean assignedAfterWhenTrue;
    private boolean unassignedAfterWhenFalse;
    private boolean unassignedAfterWhenTrue;
    public AssignmentBefore copyWhenTrue() {
        AssignmentBefore ab_ = new AssignmentBefore();
        if (isAssignedAfterWhenTrue()) {
            ab_.setAssignedBefore(true);
        }
        if (isUnassignedAfterWhenTrue()) {
            ab_.setUnassignedBefore(true);
        }
        return ab_;
    }
    public AssignmentBefore copyWhenFalse() {
        AssignmentBefore ab_ = new AssignmentBefore();
        if (isAssignedAfterWhenFalse()) {
            ab_.setAssignedBefore(true);
        }
        if (isUnassignedAfterWhenFalse()) {
            ab_.setUnassignedBefore(true);
        }
        return ab_;
    }
    public BooleanAssignment copy() {
        BooleanAssignment cp_;
        cp_ = new BooleanAssignment();
        cp_.setAssignedAfterWhenFalse(assignedAfterWhenFalse);
        cp_.setAssignedAfterWhenTrue(assignedAfterWhenTrue);
        cp_.setUnassignedAfterWhenFalse(unassignedAfterWhenFalse);
        cp_.setUnassignedAfterWhenTrue(unassignedAfterWhenTrue);
        return cp_;
    }
    public BooleanAssignment neg() {
        BooleanAssignment r_ = new BooleanAssignment();
        if (isAssignedAfterWhenTrue()) {
            r_.setAssignedAfterWhenFalse(true);
        }
        if (isUnassignedAfterWhenTrue()) {
            r_.setUnassignedAfterWhenFalse(true);
        }
        if (isAssignedAfterWhenFalse()) {
            r_.setAssignedAfterWhenTrue(true);
        }
        if (isUnassignedAfterWhenFalse()) {
            r_.setUnassignedAfterWhenTrue(true);
        }
        return r_;
    }
    public BooleanAssignment or(BooleanAssignment _o) {
        BooleanAssignment r_ = new BooleanAssignment();
        if (isAssignedAfterWhenFalse()) {
            r_.setAssignedAfterWhenFalse(true);
        }
        if (isUnassignedAfterWhenFalse()) {
            r_.setUnassignedAfterWhenFalse(true);
        }
        if (isAssignedAfterWhenTrue() && _o.isAssignedAfterWhenTrue()) {
            r_.setAssignedAfterWhenTrue(true);
        }
        if (isUnassignedAfterWhenTrue() && _o.isUnassignedAfterWhenTrue()) {
            r_.setUnassignedAfterWhenTrue(true);
        }
        return r_;
    }

    public BooleanAssignment and(BooleanAssignment _o) {
        BooleanAssignment r_ = new BooleanAssignment();
        if (isAssignedAfterWhenTrue()) {
            r_.setAssignedAfterWhenTrue(true);
        }
        if (isUnassignedAfterWhenTrue()) {
            r_.setUnassignedAfterWhenTrue(true);
        }
        if (isAssignedAfterWhenFalse() && _o.isAssignedAfterWhenFalse()) {
            r_.setAssignedAfterWhenFalse(true);
        }
        if (isUnassignedAfterWhenFalse() && _o.isUnassignedAfterWhenFalse()) {
            r_.setUnassignedAfterWhenFalse(true);
        }
        return r_;
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
