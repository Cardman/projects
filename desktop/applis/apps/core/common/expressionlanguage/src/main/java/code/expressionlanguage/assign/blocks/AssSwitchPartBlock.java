package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.util.AssignedVariables;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.assign.util.Assignment;
import code.expressionlanguage.assign.util.AssignmentBefore;
import code.expressionlanguage.assign.util.AssignmentsUtil;
import code.expressionlanguage.assign.util.SimpleAssignment;
import code.util.*;

public final class AssSwitchPartBlock extends AssBracedStack {
    private boolean def;
    AssSwitchPartBlock(boolean _completeNormally, boolean _completeNormallyGroup, boolean _def) {
        super(_completeNormally, _completeNormallyGroup);
        def = _def;
    }

    @Override
    public void setAssignmentBeforeNextSibling(ContextEl _an, AssignedVariablesBlock _a) {
        IdMap<AssBlock, AssignedVariables> id_ = _a.getFinalVariables();
        AssBlock nextSibling_ = getNextSibling();
        AssignedVariables assBl_ = nextSibling_.buildNewAssignedVariable();
        assBl_.getFieldsRootBefore().putAllMap(buildFieldsSwitchPart(_an, _a));
        assBl_.getVariablesRootBefore().addAllElts(buildVariablesSwitchPart(_an, _a));
        assBl_.getVariablesRootBefore().add(new StringMap<AssignmentBefore>());
        assBl_.getMutableLoopRootBefore().addAllElts(buildMutableLoopSwitchPart(_an, _a));
        assBl_.getMutableLoopRootBefore().add(new StringMap<AssignmentBefore>());
        id_.put(nextSibling_, assBl_);
    }
    protected CustList<StringMap<AssignmentBefore>> buildVariablesSwitchPart(ContextEl _an, AssignedVariablesBlock _anEl){
        AssBracedBlock br_ = getParent();
        IdMap<AssBlock, AssignedVariables> id_ = _anEl.getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(br_);
        AssignedVariables prevAss_ = id_.getVal(this);
        CustList<StringMap<AssignmentBefore>> out_;
        out_ = new CustList<StringMap<AssignmentBefore>>();
        CustList<StringMap<Assignment>> assSwitchs_ = parAss_.getLastVariablesOrEmpty();
        int len_ = assSwitchs_.size();
        for (int i = 0; i < len_; i++) {
            StringMap<Assignment> assSwitch_ = assSwitchs_.get(i);
            StringMap<SimpleAssignment> current_;
            if (isCompleteNormally()) {
                CustList<StringMap<SimpleAssignment>> map_ = prevAss_.getVariablesRoot();
                current_ = AssignmentsUtil.getOrEmptySimple(map_,i);
            } else {
                current_ = new StringMap<SimpleAssignment>();
            }
            out_.add(buildSwitchPart(assSwitch_, current_));
        }
        return out_;
    }
    protected CustList<StringMap<AssignmentBefore>> buildMutableLoopSwitchPart(ContextEl _an, AssignedVariablesBlock _anEl){
        AssBracedBlock br_ = getParent();
        IdMap<AssBlock, AssignedVariables> id_ = _anEl.getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(br_);
        AssignedVariables prevAss_ = id_.getVal(this);
        CustList<StringMap<AssignmentBefore>> out_;
        out_ = new CustList<StringMap<AssignmentBefore>>();
        CustList<StringMap<Assignment>> assSwitchs_ = parAss_.getLastMutableLoopOrEmpty();
        int len_ = assSwitchs_.size();
        for (int i = 0; i < len_; i++) {
            StringMap<Assignment> assSwitch_ = assSwitchs_.get(i);
            StringMap<SimpleAssignment> current_;
            if (isCompleteNormally()) {
                CustList<StringMap<SimpleAssignment>> map_ = prevAss_.getMutableLoopRoot();
                current_ = AssignmentsUtil.getOrEmptySimple(map_,i);
            } else {
                current_ = new StringMap<SimpleAssignment>();
            }
            out_.add(buildSwitchPart(assSwitch_, current_));
        }
        return out_;
    }
    protected StringMap<AssignmentBefore> buildFieldsSwitchPart(ContextEl _an, AssignedVariablesBlock _anEl){
        AssBracedBlock br_ = getParent();
        IdMap<AssBlock, AssignedVariables> id_ = _anEl.getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(br_);
        AssignedVariables prevAss_ = id_.getVal(this);
        StringMap<SimpleAssignment> current_;
        if (isCompleteNormally()) {
            current_ = prevAss_.getFieldsRoot();
        } else {
            current_ = new StringMap<SimpleAssignment>();
        }
        StringMap<Assignment> assSwitch_ = parAss_.getLastFieldsOrEmpty();
        return buildSwitchPart(assSwitch_, current_);
    }
    protected static StringMap<AssignmentBefore> buildSwitchPart(StringMap<Assignment> _assSwitch,
                                                                 StringMap<SimpleAssignment> _current){
        StringMap<AssignmentBefore> out_;
        out_ = new StringMap<AssignmentBefore>();
        for (EntryCust<String, Assignment> e: _assSwitch.entryList()) {
            String key_ = e.getKey();
            Assignment ba_ = e.getValue();
            AssignmentBefore ab_ = new AssignmentBefore();
            boolean ass_ = ba_.isAssignedAfter();
            boolean unass_ = ba_.isUnassignedAfter();
            for (EntryCust<String, SimpleAssignment> f: _current.entryList()) {
                if (!StringList.quickEq(f.getKey(), key_)) {
                    continue;
                }
                if (!f.getValue().isAssignedAfter()) {
                    ass_ = false;
                }
                if (!f.getValue().isUnassignedAfter()) {
                    unass_ = false;
                }
            }
            ab_.setAssignedBefore(ass_);
            ab_.setUnassignedBefore(unass_);
            out_.put(e.getKey(), ab_);
        }
        return out_;
    }
    public boolean isDef() {
        return def;
    }
}
