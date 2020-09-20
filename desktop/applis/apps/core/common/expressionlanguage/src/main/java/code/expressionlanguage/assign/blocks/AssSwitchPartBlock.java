package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.assign.util.AssignedVariables;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.assign.util.Assignment;
import code.expressionlanguage.assign.util.AssignmentBefore;
import code.expressionlanguage.assign.util.SimpleAssignment;
import code.util.*;

public final class AssSwitchPartBlock extends AssBracedStack {
    private boolean def;
    AssSwitchPartBlock(boolean _completeNormally, boolean _completeNormallyGroup, boolean _def) {
        super(_completeNormally, _completeNormallyGroup);
        def = _def;
    }

    @Override
    public void setAssignmentBeforeNextSibling(AssignedVariablesBlock _a) {
        IdMap<AssBlock, AssignedVariables> id_ = _a.getFinalVariables();
        AssBlock nextSibling_ = getNextSibling();
        AssignedVariables assBl_ = nextSibling_.buildNewAssignedVariable();
        assBl_.getFieldsRootBefore().putAllMap(buildFieldsSwitchPart(_a));
        assBl_.getVariablesRootBefore().putAllMap(buildVariablesSwitchPart(_a));
        id_.put(nextSibling_, assBl_);
    }
    protected StringMap<AssignmentBefore> buildVariablesSwitchPart(AssignedVariablesBlock _anEl){
        AssBracedBlock br_ = getParent();
        IdMap<AssBlock, AssignedVariables> id_ = _anEl.getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(br_);
        AssignedVariables prevAss_ = id_.getVal(this);
        StringMap<Assignment> assSwitchs_ = parAss_.getLastVariablesOrEmpty();
        StringMap<Assignment> assSwitch_ = assSwitchs_;
        StringMap<SimpleAssignment> current_;
        if (isCompleteNormally()) {
            StringMap<SimpleAssignment> map_ = prevAss_.getVariablesRoot();
            current_ = map_;
        } else {
            current_ = new StringMap<SimpleAssignment>();
        }
        return buildSwitchPart(assSwitch_, current_);
    }

    protected StringMap<AssignmentBefore> buildFieldsSwitchPart(AssignedVariablesBlock _anEl){
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
