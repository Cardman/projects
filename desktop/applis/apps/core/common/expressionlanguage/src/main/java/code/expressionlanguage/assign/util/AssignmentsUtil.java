package code.expressionlanguage.assign.util;

import code.util.EntryCust;
import code.util.StringMap;

public final class AssignmentsUtil {
    private AssignmentsUtil() {
    }

    public static StringMap<BooleanAssignment> conditionAfter(StringMap<Assignment> _cond) {
        StringMap<BooleanAssignment> out_ = new StringMap<BooleanAssignment>();
        for (EntryCust<String, Assignment> e: _cond.entryList()) {
            BooleanAssignment ba_ = new BooleanAssignment();
            ba_.setAssignedAfterWhenFalse(true);
            ba_.setUnassignedAfterWhenFalse(true);
            ba_.setAssignedAfterWhenTrue(e.getValue().isAssignedAfter());
            ba_.setUnassignedAfterWhenTrue(e.getValue().isUnassignedAfter());
            out_.put(e.getKey(), ba_);
        }
        return out_;
    }

    public static StringMap<BooleanAssignment> conditionBefore(StringMap<AssignmentBefore> _cond) {
        StringMap<BooleanAssignment> out_ = new StringMap<BooleanAssignment>();
        for (EntryCust<String, AssignmentBefore> e: _cond.entryList()) {
            BooleanAssignment ba_ = new BooleanAssignment();
            ba_.setAssignedAfterWhenFalse(true);
            ba_.setUnassignedAfterWhenFalse(true);
            ba_.setAssignedAfterWhenTrue(e.getValue().isAssignedBefore());
            ba_.setUnassignedAfterWhenTrue(e.getValue().isUnassignedBefore());
            out_.put(e.getKey(), ba_);
        }
        return out_;
    }
    public static StringMap<AssignmentBefore> getHypoAssignmentAfter(StringMap<Assignment> _s) {
        StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
        for (EntryCust<String,Assignment> e: _s.entryList()) {
            Assignment ass_ = e.getValue();
            AssignmentBefore h_ = new AssignmentBefore();
            if (ass_.isAssignedAfter()) {
                h_.setAssignedBefore(true);
            } else {
                h_.setUnassignedBefore(true);
            }
            sm_.put(e.getKey(), h_);
        }
        return sm_;
    }

    public static StringMap<AssignmentBefore> getHypoAssignmentBefore(StringMap<AssignmentBefore> _s) {
        StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
        for (EntryCust<String,AssignmentBefore> e: _s.entryList()) {
            AssignmentBefore ass_ = e.getValue();
            AssignmentBefore h_ = new AssignmentBefore();
            if (ass_.isAssignedBefore()) {
                h_.setAssignedBefore(true);
            } else {
                h_.setUnassignedBefore(true);
            }
            sm_.put(e.getKey(), h_);
        }
        return sm_;
    }

    public static StringMap<Assignment> and(StringMap<Assignment> _last, StringMap<Assignment> _befLast) {
        StringMap<Assignment> out_ = new StringMap<Assignment>();
        for (EntryCust<String, Assignment> e: _last.entryList()) {
            BooleanAssignment b_ = e.getValue().toBoolAssign();
            BooleanAssignment p_ = _befLast.getVal(e.getKey()).toBoolAssign();
            BooleanAssignment r_ = b_.and(p_);
            out_.put(e.getKey(), r_);
        }
        return out_;
    }

    public static StringMap<Assignment> or(StringMap<Assignment> _last, StringMap<Assignment> _befLast) {
        StringMap<Assignment> out_ = new StringMap<Assignment>();
        for (EntryCust<String, Assignment> e: _last.entryList()) {
            BooleanAssignment b_ = e.getValue().toBoolAssign();
            BooleanAssignment p_ = _befLast.getVal(e.getKey()).toBoolAssign();
            BooleanAssignment r_ = b_.or(p_);
            out_.put(e.getKey(), r_);
        }
        return out_;
    }

    public static StringMap<Assignment> neg(StringMap<Assignment> _ass) {
        StringMap<Assignment> out_ = new StringMap<Assignment>();
        for (EntryCust<String, Assignment> e: _ass.entryList()) {
            BooleanAssignment b_ = e.getValue().toBoolAssign();
            BooleanAssignment r_ = b_.neg();
            out_.put(e.getKey(), r_);
        }
        return out_;
    }

    public static StringMap<AssignmentBefore> assignBoolWhenTrue(StringMap<BooleanAssignment> _ass) {
        StringMap<AssignmentBefore> out_ = new StringMap<AssignmentBefore>();
        for (EntryCust<String, BooleanAssignment> e: _ass.entryList()) {
            BooleanAssignment b_ = e.getValue().toBoolAssign();
            AssignmentBefore a_ = b_.copyWhenTrue();
            out_.put(e.getKey(), a_);
        }
        return out_;
    }

    public static StringMap<AssignmentBefore> assignBoolWhenFalse(StringMap<BooleanAssignment> _ass) {
        StringMap<AssignmentBefore> out_ = new StringMap<AssignmentBefore>();
        for (EntryCust<String, BooleanAssignment> e: _ass.entryList()) {
            BooleanAssignment b_ = e.getValue().toBoolAssign();
            AssignmentBefore a_ = b_.copyWhenFalse();
            out_.put(e.getKey(), a_);
        }
        return out_;
    }

    public static StringMap<AssignmentBefore> assignWhenTrue(StringMap<Assignment> _ass) {
        StringMap<AssignmentBefore> out_ = new StringMap<AssignmentBefore>();
        for (EntryCust<String, Assignment> e: _ass.entryList()) {
            BooleanAssignment b_ = e.getValue().toBoolAssign();
            AssignmentBefore a_ = b_.copyWhenTrue();
            out_.put(e.getKey(), a_);
        }
        return out_;
    }

    public static StringMap<AssignmentBefore> assignWhenFalse(StringMap<Assignment> _ass) {
        StringMap<AssignmentBefore> out_ = new StringMap<AssignmentBefore>();
        for (EntryCust<String, Assignment> e: _ass.entryList()) {
            BooleanAssignment b_ = e.getValue().toBoolAssign();
            AssignmentBefore a_ = b_.copyWhenFalse();
            out_.put(e.getKey(), a_);
        }
        return out_;
    }

    public static StringMap<AssignmentBefore> assignBefore(StringMap<Assignment> _ass) {
        StringMap<AssignmentBefore> out_ = new StringMap<AssignmentBefore>();
        for (EntryCust<String, Assignment> e: _ass.entryList()) {
            AssignmentBefore a_ = e.getValue().assignBefore();
            out_.put(e.getKey(), a_);
        }
        return out_;
    }

    public static StringMap<AssignmentBefore> assignSimpleBefore(StringMap<SimpleAssignment> _ass) {
        StringMap<AssignmentBefore> out_ = new StringMap<AssignmentBefore>();
        for (EntryCust<String, SimpleAssignment> e: _ass.entryList()) {
            AssignmentBefore a_ = e.getValue().assignBefore();
            out_.put(e.getKey(), a_);
        }
        return out_;
    }

    public static StringMap<SimpleAssignment> assignClassic(StringMap<Assignment> _ass) {
        StringMap<SimpleAssignment> out_ = new StringMap<SimpleAssignment>();
        for (EntryCust<String, Assignment> e: _ass.entryList()) {
            SimpleAssignment b_ = e.getValue().assignClassic();
            out_.put(e.getKey(), b_);
        }
        return out_;
    }

    public static StringMap<Assignment> assignGene(boolean _bool,StringMap<Assignment> _ass) {
        StringMap<Assignment> out_ = new StringMap<Assignment>();
        for (EntryCust<String, Assignment> e: _ass.entryList()) {
            Assignment b_ = e.getValue().assign(_bool);
            out_.put(e.getKey(), b_);
        }
        return out_;
    }

    public static StringMap<SimpleAssignment> assignAfterClassic(StringMap<AssignmentBefore> _ass) {
        StringMap<SimpleAssignment> out_ = new StringMap<SimpleAssignment>();
        for (EntryCust<String, AssignmentBefore> e: _ass.entryList()) {
            SimpleAssignment b_ = e.getValue().assignAfterClassic();
            out_.put(e.getKey(), b_);
        }
        return out_;
    }

    public static StringMap<Assignment> assignAfter(boolean _bool,StringMap<AssignmentBefore> _ass) {
        StringMap<Assignment> out_ = new StringMap<Assignment>();
        for (EntryCust<String, AssignmentBefore> e: _ass.entryList()) {
            Assignment b_ = e.getValue().assignAfter(_bool);
            out_.put(e.getKey(), b_);
        }
        return out_;
    }

    public static StringMap<AssignmentBefore> copyBefore(StringMap<AssignmentBefore> _ass) {
        StringMap<AssignmentBefore> out_ = new StringMap<AssignmentBefore>();
        for (EntryCust<String, AssignmentBefore> e: _ass.entryList()) {
            AssignmentBefore b_ = e.getValue().copy();
            out_.put(e.getKey(), b_);
        }
        return out_;
    }

    public static StringMap<BooleanAssignment> toBoolAssign(StringMap<Assignment> _ass) {
        StringMap<BooleanAssignment> out_ = new StringMap<BooleanAssignment>();
        for (EntryCust<String, Assignment> e: _ass.entryList()) {
            BooleanAssignment b_ = e.getValue().toBoolAssign().copy();
            out_.put(e.getKey(), b_);
        }
        return out_;
    }
}
