package code.expressionlanguage.opers.util;


public abstract class Assignment {

    public abstract Assignment assign();

    public AssignmentBefore assignBefore() {
        AssignmentBefore ba_ = new AssignmentBefore();
        if (isAssignedAfter()) {
            ba_.setAssignedBefore(true);
        }
        if (isUnassignedAfter()) {
            ba_.setUnassignedBefore(true);
        }
        return ba_;
    }

    public Assignment assignChange(boolean _boolean, boolean _ass, boolean _una) {
        if (!_ass && !_una) {
            return assign(_boolean);
        }
        return assign(_boolean, _ass, _una);
    }

    public static Assignment assign(boolean _boolean, boolean _ass, boolean _una) {
        if (_boolean) {
            BooleanAssignment ba_ = new BooleanAssignment();
            if (_ass) {
                ba_.setAssignedAfterWhenFalse(true);
                ba_.setAssignedAfterWhenTrue(true);
            }
            if (_una) {
                ba_.setUnassignedAfterWhenFalse(true);
                ba_.setUnassignedAfterWhenTrue(true);
            }
            return ba_;
        }
        SimpleAssignment ba_ = new SimpleAssignment();
        if (_ass) {
            ba_.setAssignedAfter(true);
        }
        if (_una) {
            ba_.setUnassignedAfter(true);
        }
        return ba_;
    }

    public Assignment assign(boolean _boolean) {
        if (_boolean) {
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
        SimpleAssignment ba_ = new SimpleAssignment();
        if (isAssignedAfter()) {
            ba_.setAssignedAfter(true);
        }
        if (isUnassignedAfter()) {
            ba_.setUnassignedAfter(true);
        }
        return ba_;
    }

    public abstract boolean isAssignedAfter();
    public abstract boolean isUnassignedAfter();
}
