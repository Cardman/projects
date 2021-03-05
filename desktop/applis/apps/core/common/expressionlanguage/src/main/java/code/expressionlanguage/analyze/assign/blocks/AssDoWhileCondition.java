package code.expressionlanguage.analyze.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.ConditionBlock;
import code.expressionlanguage.analyze.assign.util.AssignedBooleanVariables;
import code.expressionlanguage.analyze.assign.util.AssignedVariables;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesDesc;
import code.expressionlanguage.analyze.assign.util.AssignmentBefore;
import code.expressionlanguage.analyze.assign.util.BooleanAssignment;
import code.expressionlanguage.analyze.assign.util.SimpleAssignment;
import code.util.*;
import code.util.core.StringUtil;

public final class AssDoWhileCondition extends AssCondition {
    AssDoWhileCondition(boolean _completeNormally, boolean _completeNormallyGroup, ConditionBlock _c) {
        super(_completeNormally, _completeNormallyGroup, _c);
    }

    @Override
    public void setAssignmentAfter(AssignedVariablesBlock _anEl, AnalyzedPageEl _page) {
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        //by do block
        AssDoBlock dBlock_ = (AssDoBlock) getPreviousSibling();
        StringMap<AssignmentBefore> fieldsHypot_;
        StringMap<AssignmentBefore> varsHypot_;

        AssignedVariablesDesc ass_ = new AssignedVariablesDesc(dBlock_,_anEl);
        IdMap<AssBlock, AssignedVariables> allDesc_ = ass_.getAllDesc();
        AssignedVariables varsDo_;
        varsDo_ = ass_.getVarsWhile();
        AssignedBooleanVariables varsWhile_;
        varsWhile_ = (AssignedBooleanVariables) id_.getVal(this);
        fieldsHypot_ = buildAssListFieldAfterInvalHypot(_anEl);
        varsDo_.getFieldsRootBefore().putAllMap(fieldsHypot_);
        varsHypot_ = buildAssListLocVarInvalHypot(_anEl);
        varsDo_.getVariablesRootBefore().clear();
        varsDo_.getVariablesRootBefore().putAllMap(varsHypot_);
        processFinalFields(allDesc_, varsDo_, fieldsHypot_, _page);
        processFinalVars(_anEl, allDesc_, varsDo_, varsHypot_, _page);

        StringMap<SimpleAssignment> fieldsAfter_;
        StringMap<SimpleAssignment> varsAfter_;
        fieldsAfter_= buildAssListFieldAfterLoop(_anEl);
        varsDo_.getFieldsRoot().putAllMap(fieldsAfter_);
        varsAfter_ = buildAssListLocVarAfterLoop(_anEl);
        varsWhile_.getVariablesRoot().clear();
        varsWhile_.getVariablesRoot().putAllMap(varsAfter_);
    }

    protected StringMap<AssignmentBefore> buildAssListFieldAfterInvalHypot(AssignedVariablesBlock _anEl) {
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        AssDoBlock dBlock_ = (AssDoBlock) getPreviousSibling();
        StringMap<AssignmentBefore> list_;
        list_ = dBlock_.makeHypothesisFields(_anEl);
        StringMap<BooleanAssignment> end_;
        end_ = ((AssignedBooleanVariables) id_.getVal(this)).getFieldsRootAfter();
        return invalidateHypothesis(list_, end_);
    }
    protected StringMap<AssignmentBefore> buildAssListLocVarInvalHypot(AssignedVariablesBlock _anEl) {
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        StringMap<AssignmentBefore> varsList_;
        varsList_ = new StringMap<AssignmentBefore>();
        AssDoBlock dBlock_ = (AssDoBlock) getPreviousSibling();
        StringMap<AssignmentBefore> list_;
        list_ = dBlock_.makeHypothesisVars(_anEl);
        int loopLen_ = list_.size();

        StringMap<BooleanAssignment> end_;
        end_ = ((AssignedBooleanVariables) id_.getVal(this)).getVariablesRootAfter();
        StringMap<AssignmentBefore> cond_ = list_;
        varsList_=invalidateHypothesis(cond_, end_);

        return varsList_;
    }

    private static StringMap<AssignmentBefore> invalidateHypothesis(StringMap<AssignmentBefore> _loop, StringMap<BooleanAssignment> _last) {
        StringMap<AssignmentBefore> out_ = new StringMap<AssignmentBefore>();
        for (EntryCust<String,AssignmentBefore> e: _loop.entryList()) {
            String key_ = e.getKey();
            AssignmentBefore ass_ = e.getValue().copy();
            for (EntryCust<String,BooleanAssignment> f: _last.entryList()) {
                if (!StringUtil.quickEq(f.getKey(),key_)) {
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
