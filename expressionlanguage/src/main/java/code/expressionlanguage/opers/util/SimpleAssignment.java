package code.expressionlanguage.opers.util;


public class SimpleAssignment extends Assignment {

    private boolean assignedAfter;
    private boolean unassignedAfter;
    @Override
    public SimpleAssignment assign() {
        SimpleAssignment ba_ = new SimpleAssignment();
        if (isAssignedAfter()) {
            ba_.setAssignedAfter(true);
        }
        if (isUnassignedAfter()) {
            ba_.setUnassignedAfter(true);
        }
        return ba_;
    }
    @Override
    public boolean isAssignedAfter() {
        return assignedAfter;
    }
    public void setAssignedAfter(boolean _assignedAfter) {
        assignedAfter = _assignedAfter;
    }
    @Override
    public boolean isUnassignedAfter() {
        return unassignedAfter;
    }
    public void setUnassignedAfter(boolean _unassignedAfter) {
        unassignedAfter = _unassignedAfter;
    }

}
