package code.expressionlanguage.analyze.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.ForIterativeLoop;
import code.expressionlanguage.analyze.assign.opers.AssOperationNode;
import code.expressionlanguage.analyze.assign.opers.AssUtil;
import code.expressionlanguage.analyze.assign.util.AssignedBooleanVariables;
import code.expressionlanguage.analyze.assign.util.AssignedVariables;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesDesc;
import code.expressionlanguage.analyze.assign.util.Assignment;
import code.expressionlanguage.analyze.assign.util.AssignmentBefore;
import code.expressionlanguage.analyze.assign.util.AssignmentsUtil;
import code.expressionlanguage.analyze.assign.util.SimpleAssignment;
import code.util.*;
import code.util.core.StringUtil;

public final class AssForIterativeLoop extends AssBracedStack implements AssBreakableBlock, AssBuildableElMethod {
    private final String label;
    private final CustList<AssOperationNode> opInit;

    private final CustList<AssOperationNode> opExp;

    private final CustList<AssOperationNode> opStep;
    AssForIterativeLoop(boolean _completeNormally, boolean _completeNormallyGroup, String _label, ForIterativeLoop _for) {
        super(_completeNormally, _completeNormallyGroup);
        opInit = AssUtil.getExecutableNodes(_for.getRootInit());
        opExp = AssUtil.getExecutableNodes(_for.getRootExp());
        opStep = AssUtil.getExecutableNodes(_for.getRootStep());
        label = _label;
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public void buildExpressionLanguage(AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        AssUtil.getSortedDescNodes(_a,opInit.last(),this, _page);
        AssUtil.getSortedDescNodes(_a,opExp.last(),this, _page);
        AssUtil.getSortedDescNodes(_a,opStep.last(),this, _page);
    }

    @Override
    public void setAssignmentBeforeChild(AssignedVariablesBlock _anEl) {
        AssBlock firstChild_ = getFirstChild();
        IdMap<AssBlock, AssignedVariables> id_ = _anEl.getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(this);
        AssignedVariables assBl_ = firstChild_.buildNewAssignedVariable();
        assBl_.getFieldsRootBefore().putAllMap(AssignmentsUtil.assignSimpleBefore(parAss_.getFieldsRoot()));
        assBl_.getVariablesRootBefore().putAllMap(AssignmentsUtil.assignSimpleBefore(parAss_.getVariablesRoot()));
        id_.put(firstChild_, assBl_);
    }

    public void defaultAssignmentBefore2(AssignedVariablesBlock _a, AssOperationNode _root) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(this);
        StringMap<AssignmentBefore> variables_;
        variables_ = new StringMap<AssignmentBefore>();
        vars_.getFieldsBefore().put(_root, AssignmentsUtil.copyBefore(vars_.getFieldsRootBefore()));
        if (vars_.getVariablesRoot().isEmpty()) {
            variables_.putAllMap(AssignmentsUtil.copyBefore(vars_.getVariablesRootBefore()));
        } else {
            variables_.putAllMap(AssignmentsUtil.assignSimpleBefore(vars_.getVariablesRoot()));
        }
        vars_.getVariablesBefore().put(_root, variables_);
    }

    public void defaultAssignmentAfter(AssignedVariablesBlock _a) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(this);
        StringMap<Assignment> res_ = vars_.getLastFieldsOrEmpty();
        vars_.getFieldsRoot().putAllMap(AssignmentsUtil.assignClassic(res_));
        StringMap<Assignment> varsRes_;
        varsRes_ = vars_.getLastVariablesOrEmpty();
        vars_.getVariablesRoot().putAllMap(AssignmentsUtil.assignClassic(varsRes_));
    }
    @Override
    protected AssignedVariables buildNewAssignedVariable() {
        return new AssignedBooleanVariables();
    }
    @Override
    public void setAssignmentAfter(AssignedVariablesBlock _anEl, AnalyzedPageEl _page) {
        AssignedVariablesDesc assForIt_ = new AssignedVariablesDesc(this,_anEl);
        AssignedVariables varsWhileForIt_ = assForIt_.getVarsWhile();
        IdMap<AssBlock, AssignedVariables> allDesc_ = assForIt_.getAllDesc();
        StringMap<AssignmentBefore> fieldsHypot_;
        StringMap<AssignmentBefore> varsHypot_;
        fieldsHypot_ = buildAssListFieldAfterInvalHypot(_anEl);
        varsWhileForIt_.getFieldsRootBefore().putAllMap(fieldsHypot_);
        varsHypot_ = buildAssListLocVarInvalHypot(_anEl);
        varsWhileForIt_.getVariablesRootBefore().clear();
        varsWhileForIt_.getVariablesRootBefore().putAllMap(varsHypot_);
        processFinalFields(allDesc_, varsWhileForIt_, fieldsHypot_, _page);
        processFinalVars(_anEl, allDesc_, varsWhileForIt_, varsHypot_, _page);
        StringMap<SimpleAssignment> fieldsAfter_;
        StringMap<SimpleAssignment> varsAfter_;
        fieldsAfter_= buildAssListFieldAfterLoop(_anEl);
        varsWhileForIt_.getFieldsRoot().putAllMap(fieldsAfter_);
        varsAfter_ = buildAssListLocVarAfterLoop(_anEl);
        varsWhileForIt_.getVariablesRoot().clear();
        varsWhileForIt_.getVariablesRoot().putAllMap(varsAfter_);
    }
    public StringMap<AssignmentBefore> buildAssListFieldAfterInvalHypot(AssignedVariablesBlock _anEl) {
        AssBlock first_ = getFirstChild();
        AssBlock last_ = first_;
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        CustList<AssContinueBlock> continuesForIt_ = getContinuables();
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        StringMap<AssignmentBefore> list_;
        list_ = first_.makeHypothesisFields(_anEl);
        int contLen_ = continuesForIt_.size();
        CustList<StringMap<AssignmentBefore>> breakAssForIt2_;
        breakAssForIt2_ = new CustList<StringMap<AssignmentBefore>>();
        for (int j = 0; j < contLen_; j++) {
            AssContinueBlock br_ = continuesForIt_.get(j);
            AssignedVariables ass_ = id_.getVal(br_);
            StringMap<AssignmentBefore> vars_ = ass_.getFieldsRootBefore();
            breakAssForIt2_.add(vars_);
        }
        if (last_.isCompleteNormallyGroup()) {
            AssignedVariables ass_ = id_.getVal(last_);
            StringMap<SimpleAssignment> v_ = ass_.getFieldsRoot();
            return invalidateHypothesis(list_, v_, breakAssForIt2_);
        }
        return invalidateHypothesis(list_, new StringMap<SimpleAssignment>(), breakAssForIt2_);
    }
    public StringMap<AssignmentBefore> buildAssListLocVarInvalHypot(AssignedVariablesBlock _anEl) {
        AssBlock first_ = getFirstChild();
        AssBlock lastForIt3_ = first_;
        while (lastForIt3_.getNextSibling() != null) {
            lastForIt3_ = lastForIt3_.getNextSibling();
        }
        CustList<AssContinueBlock> continuesForIt_ = getContinuables();
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        StringMap<AssignmentBefore> list_;
        list_ = first_.makeHypothesisVars(_anEl);
        int contLen_ = continuesForIt_.size();
        CustList<StringMap<AssignmentBefore>> breakAss_;
        breakAss_ = new CustList<StringMap<AssignmentBefore>>();
        for (int j = 0; j < contLen_; j++) {
            AssContinueBlock br_ = continuesForIt_.get(j);
            AssignedVariables ass_ = id_.getVal(br_);
            StringMap<AssignmentBefore> vars_ = ass_.getVariablesRootBefore();
            breakAss_.add(vars_);
        }
        if (lastForIt3_.isCompleteNormallyGroup()) {
            AssignedVariables ass_ = id_.getVal(lastForIt3_);
            StringMap<SimpleAssignment> v_ = ass_.getVariablesRoot();
            return(invalidateHypothesis(list_, v_, breakAss_));
        } else {
            return(invalidateHypothesis(list_, new StringMap<SimpleAssignment>(), breakAss_));
        }
    }

    private static StringMap<AssignmentBefore> invalidateHypothesis(StringMap<AssignmentBefore> _loop, StringMap<SimpleAssignment> _last,
                                                                    CustList<StringMap<AssignmentBefore>> _continuable) {
        StringMap<AssignmentBefore> out_ = new StringMap<AssignmentBefore>();
        for (EntryCust<String,AssignmentBefore> e: _loop.entryList()) {
            extracted(_last, _continuable, out_, e);
        }
        return out_;
    }

    private static void extracted(StringMap<SimpleAssignment> _last, CustList<StringMap<AssignmentBefore>> _continuable, StringMap<AssignmentBefore> _out, EntryCust<String, AssignmentBefore> _e) {
        String keyForIt_ = _e.getKey();
        AssignmentBefore assForIt_ = _e.getValue().copy();
        for (StringMap<AssignmentBefore> c: _continuable) {
            for (EntryCust<String,AssignmentBefore> f:c.entryList()) {
                if (!StringUtil.quickEq(f.getKey(), keyForIt_)) {
                    continue;
                }
                if (!f.getValue().isUnassignedBefore()) {
                    assForIt_.setUnassignedBefore(false);
                }
            }
        }
        if (_last.contains(keyForIt_) && !_last.getVal(keyForIt_).isUnassignedAfter()) {
            assForIt_.setUnassignedBefore(false);
        }
        _out.put(keyForIt_, assForIt_);
    }

}
