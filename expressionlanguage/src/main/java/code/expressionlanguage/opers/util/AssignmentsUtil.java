package code.expressionlanguage.opers.util;

import code.util.EntryCust;
import code.util.StringMap;
import code.util.CustList;
import com.sun.org.apache.xpath.internal.operations.Bool;

public final class AssignmentsUtil {
    private AssignmentsUtil() {
    }
    public static StringMap<Assignment> getOrEmpty(CustList<StringMap<Assignment>> _list, int _i) {
        if (_list.isValidIndex(_i)) {
            return _list.get(_i);
        }
        return new StringMap<Assignment>();
    }
    public static StringMap<AssignmentBefore> getOrEmptyBefore(CustList<StringMap<AssignmentBefore>> _list, int _i) {
        if (_list.isValidIndex(_i)) {
            return _list.get(_i);
        }
        return new StringMap<AssignmentBefore>();
    }
    public static StringMap<BooleanAssignment> getOrEmptyBool(CustList<StringMap<BooleanAssignment>> _list, int _i) {
        if (_list.isValidIndex(_i)) {
            return _list.get(_i);
        }
        return new StringMap<BooleanAssignment>();
    }
    public static StringMap<SimpleAssignment> getOrEmptySimple(CustList<StringMap<SimpleAssignment>> _list, int _i) {
        if (_list.isValidIndex(_i)) {
            return _list.get(_i);
        }
        return new StringMap<SimpleAssignment>();
    }
    public static CustList<StringMap<BooleanAssignment>> conditionAfter(CustList<StringMap<Assignment>> _cond) {
        CustList<StringMap<BooleanAssignment>> out_ = new CustList<StringMap<BooleanAssignment>>();
        for (StringMap<Assignment> e: _cond) {
            out_.add(conditionAfter(e));
        }
        return out_;
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
    public static CustList<StringMap<BooleanAssignment>> conditionBefore(CustList<StringMap<AssignmentBefore>> _cond) {
        CustList<StringMap<BooleanAssignment>> out_ = new CustList<StringMap<BooleanAssignment>>();
        for (StringMap<AssignmentBefore> e: _cond) {
            out_.add(conditionBefore(e));
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
    public static CustList<StringMap<Assignment>> and(CustList<StringMap<Assignment>> _last, CustList<StringMap<Assignment>> _befLast) {
        CustList<StringMap<Assignment>> out_ = new CustList<StringMap<Assignment>>();
        for (StringMap<Assignment> e: _last) {
            int index_ = out_.size();
            out_.add(and(e, _befLast.get(index_)));
        }
        return out_;
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
    public static CustList<StringMap<Assignment>> or(CustList<StringMap<Assignment>> _last, CustList<StringMap<Assignment>> _befLast) {
        CustList<StringMap<Assignment>> out_ = new CustList<StringMap<Assignment>>();
        for (StringMap<Assignment> e: _last) {
            int index_ = out_.size();
            out_.add(or(e, _befLast.get(index_)));
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
    public static CustList<StringMap<Assignment>> neg(CustList<StringMap<Assignment>> _ass) {
        CustList<StringMap<Assignment>> out_ = new CustList<StringMap<Assignment>>();
        for (StringMap<Assignment> e: _ass) {
            out_.add(neg(e));
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
    public static CustList<StringMap<AssignmentBefore>> assignBoolWhenTrue(CustList<StringMap<BooleanAssignment>> _ass) {
        CustList<StringMap<AssignmentBefore>> out_ = new CustList<StringMap<AssignmentBefore>>();
        for (StringMap<BooleanAssignment> e: _ass) {
            out_.add(assignBoolWhenTrue(e));
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
    public static CustList<StringMap<AssignmentBefore>> assignBoolWhenFalse(CustList<StringMap<BooleanAssignment>> _ass) {
        CustList<StringMap<AssignmentBefore>> out_ = new CustList<StringMap<AssignmentBefore>>();
        for (StringMap<BooleanAssignment> e: _ass) {
            out_.add(assignBoolWhenFalse(e));
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
    public static CustList<StringMap<AssignmentBefore>> assignWhenTrue(CustList<StringMap<Assignment>> _ass) {
        CustList<StringMap<AssignmentBefore>> out_ = new CustList<StringMap<AssignmentBefore>>();
        for (StringMap<Assignment> e: _ass) {
            out_.add(assignWhenTrue(e));
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
    public static CustList<StringMap<AssignmentBefore>> assignWhenFalse(CustList<StringMap<Assignment>> _ass) {
        CustList<StringMap<AssignmentBefore>> out_ = new CustList<StringMap<AssignmentBefore>>();
        for (StringMap<Assignment> e: _ass) {
            out_.add(assignWhenFalse(e));
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
    public static CustList<StringMap<AssignmentBefore>> assignBefore(CustList<StringMap<Assignment>> _ass) {
        CustList<StringMap<AssignmentBefore>> out_ = new CustList<StringMap<AssignmentBefore>>();
        for (StringMap<Assignment> e: _ass) {
            out_.add(assignBefore(e));
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
    public static CustList<StringMap<AssignmentBefore>> assignSimpleBefore(CustList<StringMap<SimpleAssignment>> _ass) {
        CustList<StringMap<AssignmentBefore>> out_ = new CustList<StringMap<AssignmentBefore>>();
        for (StringMap<SimpleAssignment> e: _ass) {
            out_.add(assignSimpleBefore(e));
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
    public static CustList<StringMap<SimpleAssignment>> assignClassic(CustList<StringMap<Assignment>> _ass) {
        CustList<StringMap<SimpleAssignment>> out_ = new CustList<StringMap<SimpleAssignment>>();
        for (StringMap<Assignment> e: _ass) {
            out_.add(assignClassic(e));
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
    public static CustList<StringMap<Assignment>> assignGene(boolean _bool,CustList<StringMap<Assignment>> _ass) {
        CustList<StringMap<Assignment>> out_ = new CustList<StringMap<Assignment>>();
        for (StringMap<Assignment> e: _ass) {
            out_.add(assignGene(_bool,e));
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
    public static CustList<StringMap<SimpleAssignment>> assignAfterClassic(CustList<StringMap<AssignmentBefore>> _ass) {
        CustList<StringMap<SimpleAssignment>> out_ = new CustList<StringMap<SimpleAssignment>>();
        for (StringMap<AssignmentBefore> e: _ass) {
            out_.add(assignAfterClassic(e));
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
    public static CustList<StringMap<Assignment>> assignAfter(boolean _bool,CustList<StringMap<AssignmentBefore>> _ass) {
        CustList<StringMap<Assignment>> out_ = new CustList<StringMap<Assignment>>();
        for (StringMap<AssignmentBefore> e: _ass) {
            out_.add(assignAfter(_bool,e));
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
    public static CustList<StringMap<AssignmentBefore>> copyBefore(CustList<StringMap<AssignmentBefore>> _ass) {
        CustList<StringMap<AssignmentBefore>> out_ = new CustList<StringMap<AssignmentBefore>>();
        for (StringMap<AssignmentBefore> e: _ass) {
            out_.add(copyBefore(e));
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
    public static CustList<StringMap<BooleanAssignment>> toBoolAssign(CustList<StringMap<Assignment>> _ass) {
        CustList<StringMap<BooleanAssignment>> out_ = new CustList<StringMap<BooleanAssignment>>();
        for (StringMap<Assignment> e: _ass) {
            out_.add(toBoolAssign(e));
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
