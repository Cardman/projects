package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.util.AssignedBooleanVariables;
import code.expressionlanguage.assign.util.AssignedVariables;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.assign.util.AssignedVariablesDesc;
import code.expressionlanguage.exec.blocks.ExecCondition;
import code.expressionlanguage.assign.util.AssignmentBefore;
import code.expressionlanguage.assign.util.BooleanAssignment;
import code.expressionlanguage.assign.util.SimpleAssignment;
import code.util.*;

public final class AssDoWhileCondition extends AssCondition {
    AssDoWhileCondition(boolean _completeNormally, boolean _completeNormallyGroup, ExecCondition _c) {
        super(_completeNormally, _completeNormallyGroup, _c);
    }

    @Override
    public void setAssignmentAfter(ContextEl _an, AssignedVariablesBlock _anEl) {
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        //by do block
        AssDoBlock dBlock_ = (AssDoBlock) getPreviousSibling();
        StringMap<AssignmentBefore> fieldsHypot_;
        CustList<StringMap<AssignmentBefore>> varsHypot_;
        CustList<StringMap<AssignmentBefore>> mutableHypot_;

        AssignedVariablesDesc ass_ = new AssignedVariablesDesc(_an,dBlock_,_anEl);
        IdMap<AssBlock, AssignedVariables> allDesc_ = ass_.getAllDesc();
        AssignedVariables varsDo_;
        varsDo_ = ass_.getVarsWhile();
        AssignedBooleanVariables varsWhile_;
        varsWhile_ = (AssignedBooleanVariables) id_.getVal(this);
        fieldsHypot_ = buildAssListFieldAfterInvalHypot(_an,_anEl);
        varsDo_.getFieldsRootBefore().putAllMap(fieldsHypot_);
        varsHypot_ = buildAssListLocVarInvalHypot(_an,_anEl);
        varsDo_.getVariablesRootBefore().clear();
        varsDo_.getVariablesRootBefore().addAllElts(varsHypot_);
        mutableHypot_ = buildAssListMutableLoopInvalHypot(_an,_anEl);
        varsDo_.getMutableLoopRootBefore().clear();
        varsDo_.getMutableLoopRootBefore().addAllElts(mutableHypot_);
        processFinalFields(_an, allDesc_, varsDo_, fieldsHypot_);
        processFinalVars(_an,_anEl, allDesc_, varsDo_, varsHypot_);
        processFinalMutableLoop(_an,_anEl, allDesc_, varsDo_, mutableHypot_);

        StringMap<SimpleAssignment> fieldsAfter_;
        CustList<StringMap<SimpleAssignment>> varsAfter_;
        CustList<StringMap<SimpleAssignment>> mutableAfter_;
        fieldsAfter_= buildAssListFieldAfterLoop(_an, _anEl);
        varsDo_.getFieldsRoot().putAllMap(fieldsAfter_);
        varsAfter_ = buildAssListLocVarAfterLoop(_an, _anEl);
        varsWhile_.getVariablesRoot().clear();
        varsWhile_.getVariablesRoot().addAllElts(varsAfter_);
        mutableAfter_ = buildAssListMutableLoopAfterLoop(_an, _anEl);
        varsWhile_.getMutableLoopRoot().clear();
        varsWhile_.getMutableLoopRoot().addAllElts(mutableAfter_);
    }

    protected StringMap<AssignmentBefore> buildAssListFieldAfterInvalHypot(ContextEl _an, AssignedVariablesBlock _anEl) {
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        AssDoBlock dBlock_ = (AssDoBlock) getPreviousSibling();
        StringMap<AssignmentBefore> list_;
        list_ = dBlock_.makeHypothesisFields(_an,_anEl);
        StringMap<BooleanAssignment> end_;
        end_ = ((AssignedBooleanVariables) id_.getVal(this)).getFieldsRootAfter();
        return invalidateHypothesis(list_, end_);
    }
    protected CustList<StringMap<AssignmentBefore>> buildAssListLocVarInvalHypot(ContextEl _an, AssignedVariablesBlock _anEl) {
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        CustList<StringMap<AssignmentBefore>> varsList_;
        varsList_ = new CustList<StringMap<AssignmentBefore>>();
        AssDoBlock dBlock_ = (AssDoBlock) getPreviousSibling();
        CustList<StringMap<AssignmentBefore>> list_;
        list_ = dBlock_.makeHypothesisVars(_an,_anEl);
        int loopLen_ = list_.size();

        CustList<StringMap<BooleanAssignment>> end_;
        end_ = ((AssignedBooleanVariables) id_.getVal(this)).getVariablesRootAfter();
        for (int i = 0; i < loopLen_; i++) {
            StringMap<AssignmentBefore> cond_ = list_.get(i);
            varsList_.add(invalidateHypothesis(cond_, end_.get(i)));
        }

        return varsList_;
    }
    protected CustList<StringMap<AssignmentBefore>> buildAssListMutableLoopInvalHypot(ContextEl _an, AssignedVariablesBlock _anEl) {
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        CustList<StringMap<AssignmentBefore>> varsList_;
        varsList_ = new CustList<StringMap<AssignmentBefore>>();
        AssDoBlock dBlock_ = (AssDoBlock) getPreviousSibling();
        CustList<StringMap<AssignmentBefore>> list_;
        list_ = dBlock_.makeHypothesisMutableLoop(_an,_anEl);
        int loopLen_ = list_.size();

        CustList<StringMap<BooleanAssignment>> end_;
        end_ = ((AssignedBooleanVariables) id_.getVal(this)).getMutableLoopRootAfter();
        for (int i = 0; i < loopLen_; i++) {
            StringMap<AssignmentBefore> cond_ = list_.get(i);
            varsList_.add(invalidateHypothesis(cond_, end_.get(i)));
        }

        return varsList_;
    }
    private static StringMap<AssignmentBefore> invalidateHypothesis(StringMap<AssignmentBefore> _loop, StringMap<BooleanAssignment> _last) {
        StringMap<AssignmentBefore> out_ = new StringMap<AssignmentBefore>();
        for (EntryCust<String,AssignmentBefore> e: _loop.entryList()) {
            String key_ = e.getKey();
            AssignmentBefore ass_ = e.getValue().copy();
            for (EntryCust<String,BooleanAssignment> f: _last.entryList()) {
                if (!StringList.quickEq(f.getKey(),key_)) {
                    continue;
                }
                if (!f.getValue().isUnassignedAfterWhenTrue()) {
                    ass_.setUnassignedBefore(false);
                }
            }
            out_.put(key_, ass_);
        }
        return out_;
    }
}
