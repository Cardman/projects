package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.WhileCondition;
import code.expressionlanguage.assign.util.AssignedVariables;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.assign.util.AssignedVariablesDesc;
import code.expressionlanguage.assign.util.AssignmentBefore;
import code.expressionlanguage.assign.util.SimpleAssignment;
import code.util.*;

public final class AssWhileCondition extends AssCondition implements AssLoop {
    private String label;
    AssWhileCondition(boolean _completeNormally, boolean _completeNormallyGroup, String _label,WhileCondition _c) {
        super(_completeNormally, _completeNormallyGroup, _c);
        label = _label;
    }

    @Override
    public void setAssignmentBeforeChild(AssignedVariablesBlock _a) {
        assignWhenTrue(_a);
    }

    @Override
    public void setAssignmentAfter(AssignedVariablesBlock _anEl, AnalyzedPageEl _page) {
        AssignedVariablesDesc ass_ = new AssignedVariablesDesc(this,_anEl);
        AssignedVariables varsWhile_ = ass_.getVarsWhile();
        IdMap<AssBlock, AssignedVariables> allDesc_ = ass_.getAllDesc();
        StringMap<AssignmentBefore> fieldsHypot_;
        StringMap<AssignmentBefore> varsHypot_;
        fieldsHypot_ = buildAssListFieldAfterInvalHypot(_anEl);
        varsWhile_.getFieldsRootBefore().putAllMap(fieldsHypot_);
        varsHypot_ = buildAssListLocVarInvalHypot(_anEl);
        varsWhile_.getVariablesRootBefore().clear();
        varsWhile_.getVariablesRootBefore().putAllMap(varsHypot_);
        processFinalFields(allDesc_, varsWhile_, fieldsHypot_, _page);
        processFinalVars(_anEl,allDesc_, varsWhile_, varsHypot_, _page);
        StringMap<SimpleAssignment> fieldsAfter_;
        fieldsAfter_= buildAssListFieldAfterLoop(_anEl);
        varsWhile_.getFieldsRoot().putAllMap(fieldsAfter_);
        StringMap<SimpleAssignment> varsAfter_;
        varsAfter_ = buildAssListLocVarAfterLoop(_anEl);
        varsWhile_.getVariablesRoot().clear();
        varsWhile_.getVariablesRoot().putAllMap(varsAfter_);
    }

    protected StringMap<AssignmentBefore> buildAssListFieldAfterInvalHypot(AssignedVariablesBlock _anEl) {
        AssBlock last_ = getFirstChild();
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        CustList<AssContinueBlock> continues_ = getContinuables();
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        StringMap<AssignmentBefore> list_;
        list_ = makeHypothesisFields(_anEl);
        int contLen_ = continues_.size();
        CustList<StringMap<AssignmentBefore>> breakAss_;
        breakAss_ = new CustList<StringMap<AssignmentBefore>>();
        for (int j = 0; j < contLen_; j++) {
            AssContinueBlock br_ = continues_.get(j);
            AssignedVariables ass_ = id_.getVal(br_);
            StringMap<AssignmentBefore> vars_ = ass_.getFieldsRootBefore();
            breakAss_.add(vars_);
        }
        if (last_.isCompleteNormallyGroup()) {
            AssignedVariables ass_ = id_.getVal(last_);
            StringMap<SimpleAssignment> v_ = ass_.getFieldsRoot();
            return invalidateHypothesis(list_, v_, breakAss_);
        }
        return invalidateHypothesis(list_, new StringMap<SimpleAssignment>(), breakAss_);
    }
    protected StringMap<AssignmentBefore> buildAssListLocVarInvalHypot(AssignedVariablesBlock _anEl) {
        AssBlock last_ = getFirstChild();
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        CustList<AssContinueBlock> continues_ = getContinuables();
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        StringMap<AssignmentBefore> varsList_;
        StringMap<AssignmentBefore> list_;
        list_ = makeHypothesisVars(_anEl);
        int contLen_ = continues_.size();
        CustList<StringMap<AssignmentBefore>> breakAss_;
        breakAss_ = new CustList<StringMap<AssignmentBefore>>();
        for (int j = 0; j < contLen_; j++) {
            AssContinueBlock br_ = continues_.get(j);
            AssignedVariables ass_ = id_.getVal(br_);
            StringMap<AssignmentBefore> vars_ = ass_.getVariablesRootBefore();
            breakAss_.add(vars_);
        }
        if (last_.isCompleteNormallyGroup()) {
            AssignedVariables ass_ = id_.getVal(last_);
            StringMap<SimpleAssignment> v_ = ass_.getVariablesRoot();
            varsList_=invalidateHypothesis(list_, v_, breakAss_);
        } else {
            varsList_=invalidateHypothesis(list_, new StringMap<SimpleAssignment>(), breakAss_);
        }

        return varsList_;
    }

    private static StringMap<AssignmentBefore> invalidateHypothesis(StringMap<AssignmentBefore> _loop, StringMap<SimpleAssignment> _last,
                                                                    CustList<StringMap<AssignmentBefore>> _continuable) {
        StringMap<AssignmentBefore> out_ = new StringMap<AssignmentBefore>();
        for (EntryCust<String,AssignmentBefore> e: _loop.entryList()) {
            String key_ = e.getKey();
            AssignmentBefore ass_ = e.getValue().copy();
            for (StringMap<AssignmentBefore> c: _continuable) {
                for (EntryCust<String,AssignmentBefore> f: c.entryList()) {
                    if (!StringList.quickEq(f.getKey(),key_)) {
                        continue;
                    }
                    if (!f.getValue().isUnassignedBefore()) {
                        ass_.setUnassignedBefore(false);
                    }
                }
            }
            if (_last.contains(key_)) {
                if (!_last.getVal(key_).isUnassignedAfter()) {
                    ass_.setUnassignedBefore(false);
                }
            }
            out_.put(key_, ass_);
        }
        return out_;
    }
    @Override
    public String getRealLabel() {
        return label;
    }
}
