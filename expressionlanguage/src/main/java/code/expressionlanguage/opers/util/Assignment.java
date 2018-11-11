package code.expressionlanguage.opers.util;


public abstract class Assignment {

    public abstract BooleanAssignment toBoolAssign();

    public static Assignment ternary(Assignment _a, Assignment _b, boolean _toBoolean) {
        if (!_toBoolean) {
            SimpleAssignment r_ = new SimpleAssignment();
            if (_a.isAssignedAfter() && _b.isAssignedAfter()) {
                r_.setAssignedAfter(true);
            }
            if (_a.isUnassignedAfter() && _b.isUnassignedAfter()) {
                r_.setUnassignedAfter(true);
            }
            return r_;
        }
        BooleanAssignment a_ = _a.toBoolAssign();
        BooleanAssignment b_ = _b.toBoolAssign();
        BooleanAssignment r_ = new BooleanAssignment();
        if (a_.isAssignedAfterWhenTrue() && b_.isAssignedAfterWhenTrue()) {
            r_.setAssignedAfterWhenTrue(true);
        }
        if (a_.isAssignedAfterWhenFalse() && b_.isAssignedAfterWhenFalse()) {
            r_.setAssignedAfterWhenFalse(true);
        }
        if (a_.isUnassignedAfterWhenTrue() && b_.isUnassignedAfterWhenTrue()) {
            r_.setUnassignedAfterWhenTrue(true);
        }
        if (a_.isUnassignedAfterWhenFalse() && b_.isUnassignedAfterWhenFalse()) {
            r_.setUnassignedAfterWhenFalse(true);
        }
        return r_;
    }
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
        return assignClassic();
    }

    public SimpleAssignment assignClassic() {
        boolean ass_ = isAssignedAfter();
        boolean una_ = isUnassignedAfter();
        return assignClassic(ass_, una_);
    }
    public static SimpleAssignment assignClassic(boolean _ass, boolean _una) {
        SimpleAssignment ba_ = new SimpleAssignment();
        if (_ass) {
            ba_.setAssignedAfter(true);
        }
        if (_una) {
            ba_.setUnassignedAfter(true);
        }
        return ba_;
    }
    public abstract boolean isAssignedAfter();
    public abstract boolean isUnassignedAfter();
}
