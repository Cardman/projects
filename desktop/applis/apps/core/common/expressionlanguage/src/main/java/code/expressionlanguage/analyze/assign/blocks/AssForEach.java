package code.expressionlanguage.analyze.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.ForEachLoop;
import code.expressionlanguage.analyze.blocks.ForEachTable;
import code.expressionlanguage.analyze.assign.opers.AssOperationNode;
import code.expressionlanguage.analyze.assign.opers.AssUtil;
import code.expressionlanguage.analyze.assign.util.AssignedBooleanVariables;
import code.expressionlanguage.analyze.assign.util.AssignedVariables;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesDesc;
import code.expressionlanguage.analyze.assign.util.AssignmentBefore;
import code.expressionlanguage.analyze.assign.util.AssignmentsUtil;
import code.expressionlanguage.analyze.assign.util.SimpleAssignment;
import code.util.*;
import code.util.core.StringUtil;

public final class AssForEach extends AssBracedStack implements AssBreakableBlock, AssBuildableElMethod {
    private final String label;
    private final CustList<AssOperationNode> opList;
    AssForEach(boolean _completeNormally, boolean _completeNormallyGroup, String _label, ForEachLoop _for) {
        super(_completeNormally, _completeNormallyGroup);
        label = _label;
        opList = AssUtil.getExecutableNodes(_for.getRoot());
    }
    AssForEach(boolean _completeNormally, boolean _completeNormallyGroup, String _label, ForEachTable _for) {
        super(_completeNormally, _completeNormallyGroup);
        label = _label;
        opList = AssUtil.getExecutableNodes(_for.getRoot());
    }

    @Override
    public void setAssignmentBeforeChild(AssignedVariablesBlock _a) {
        AssBlock firstChild_ = getFirstChild();
        IdMap<AssBlock, AssignedVariables> id_ = _a.getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(this);
        AssignedVariables assBl_ = firstChild_.buildNewAssignedVariable();
        assBl_.getFieldsRootBefore().putAllMap(AssignmentsUtil.assignSimpleBefore(parAss_.getFieldsRoot()));
        assBl_.getVariablesRootBefore().putAllMap(AssignmentsUtil.assignSimpleBefore(parAss_.getVariablesRoot()));
        id_.put(firstChild_, assBl_);
    }

    @Override
    public void setAssignmentAfter(AssignedVariablesBlock _anEl, AnalyzedPageEl _page) {
        AssignedVariablesDesc assForEach_ = new AssignedVariablesDesc(this,_anEl);
        AssignedVariables varsWhileForEach_ = assForEach_.getVarsWhile();
        IdMap<AssBlock, AssignedVariables> allDesc_ = assForEach_.getAllDesc();
        StringMap<AssignmentBefore> fieldsHypot_;
        StringMap<AssignmentBefore> varsHypot_;
        fieldsHypot_ = buildAssListFieldAfterInvalHypot(_anEl);
        varsWhileForEach_.getFieldsRootBefore().putAllMap(fieldsHypot_);
        varsHypot_ = buildAssListLocVarInvalHypot(_anEl);
        varsWhileForEach_.getVariablesRootBefore().clear();
        varsWhileForEach_.getVariablesRootBefore().putAllMap(varsHypot_);
        processFinalFields(allDesc_, varsWhileForEach_, fieldsHypot_, _page);
        processFinalVars(_anEl, allDesc_, varsWhileForEach_, varsHypot_, _page);
        StringMap<SimpleAssignment> fieldsAfter_;
        StringMap<SimpleAssignment> varsAfter_;
        fieldsAfter_= buildAssListFieldAfterLoop(_anEl);
        varsWhileForEach_.getFieldsRoot().putAllMap(fieldsAfter_);
        varsAfter_ = buildAssListLocVarAfterLoop(_anEl);
        varsWhileForEach_.getVariablesRoot().clear();
        varsWhileForEach_.getVariablesRoot().putAllMap(varsAfter_);
    }
    public StringMap<AssignmentBefore> buildAssListFieldAfterInvalHypot(AssignedVariablesBlock _anEl) {
        AssBlock first_ = getFirstChild();
        AssBlock last_ = first_;
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        CustList<AssContinueBlock> continues_ = getContinuables();
        IdMap<AssBlock, AssignedVariables> idForEach2_;
        idForEach2_ = _anEl.getFinalVariables();
        StringMap<AssignmentBefore> list_;
        list_ = first_.makeHypothesisFields(_anEl);
        int contLen_ = continues_.size();
        CustList<StringMap<AssignmentBefore>> breakAss_;
        breakAss_ = new CustList<StringMap<AssignmentBefore>>();
        for (int j = 0; j < contLen_; j++) {
            AssContinueBlock br_ = continues_.get(j);
            AssignedVariables assForEach_ = idForEach2_.getVal(br_);
            StringMap<AssignmentBefore> vars_ = assForEach_.getFieldsRootBefore();
            breakAss_.add(vars_);
        }
        if (last_.isCompleteNormallyGroup()) {
            AssignedVariables ass_ = idForEach2_.getVal(last_);
            StringMap<SimpleAssignment> vForEach_ = ass_.getFieldsRoot();
            return invalidateHypothesis(list_, vForEach_, breakAss_);
        }
        return invalidateHypothesis(list_, new StringMap<SimpleAssignment>(), breakAss_);
    }
    public StringMap<AssignmentBefore> buildAssListLocVarInvalHypot(AssignedVariablesBlock _anEl) {
        AssBlock first_ = getFirstChild();
        AssBlock last_ = first_;
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        CustList<AssContinueBlock> continues_ = getContinuables();
        IdMap<AssBlock, AssignedVariables> idForEach_;
        idForEach_ = _anEl.getFinalVariables();
        StringMap<AssignmentBefore> list_;
        list_ = first_.makeHypothesisVars(_anEl);
        int contLen_ = continues_.size();
        CustList<StringMap<AssignmentBefore>> breakAssFo_;
        breakAssFo_ = new CustList<StringMap<AssignmentBefore>>();
        for (int j = 0; j < contLen_; j++) {
            AssContinueBlock br_ = continues_.get(j);
            AssignedVariables ass_ = idForEach_.getVal(br_);
            StringMap<AssignmentBefore> vars_ = ass_.getVariablesRootBefore();
            breakAssFo_.add(vars_);
        }
        if (last_.isCompleteNormallyGroup()) {
            AssignedVariables ass_ = idForEach_.getVal(last_);
            StringMap<SimpleAssignment> v_ = ass_.getVariablesRoot();
            return(invalidateHypothesis(list_, v_, breakAssFo_));
        } else {
            return(invalidateHypothesis(list_, new StringMap<SimpleAssignment>(), breakAssFo_));
        }
    }

    private static StringMap<AssignmentBefore> invalidateHypothesis(StringMap<AssignmentBefore> _loop, StringMap<SimpleAssignment> _last,
                                                                    CustList<StringMap<AssignmentBefore>> _continuableFor) {
        StringMap<AssignmentBefore> out_ = new StringMap<AssignmentBefore>();
        for (EntryCust<String,AssignmentBefore> e: _loop.entryList()) {
            extracted(_last, _continuableFor, out_, e);
        }
        return out_;
    }

    private static void extracted(StringMap<SimpleAssignment> _last, CustList<StringMap<AssignmentBefore>> _continuable, StringMap<AssignmentBefore> _out, EntryCust<String, AssignmentBefore> _e) {
        String keyForEach_ = _e.getKey();
        AssignmentBefore assForEach2_ = _e.getValue().copy();
        for (StringMap<AssignmentBefore> c: _continuable) {
            for (EntryCust<String,AssignmentBefore> f:c.entryList()) {
                if (!StringUtil.quickEq(f.getKey(), keyForEach_)) {
                    continue;
                }
                if (!f.getValue().isUnassignedBefore()) {
                    assForEach2_.setUnassignedBefore(false);
                }
            }
        }
        if (_last.contains(keyForEach_) && !_last.getVal(keyForEach_).isUnassignedAfter()) {
            assForEach2_.setUnassignedBefore(false);
        }
        _out.put(keyForEach_, assForEach2_);
    }

    @Override
    protected AssignedVariables buildNewAssignedVariable() {
        return new AssignedBooleanVariables();
    }
    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public void buildExpressionLanguage(AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        AssUtil.getSortedDescNodes(_a,opList.last(),this, _page);
    }
}
