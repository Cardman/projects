package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.ForIterativeLoop;
import code.expressionlanguage.assign.opers.AssOperationNode;
import code.expressionlanguage.assign.opers.AssUtil;
import code.expressionlanguage.assign.util.AssignedBooleanVariables;
import code.expressionlanguage.assign.util.AssignedVariables;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.assign.util.AssignedVariablesDesc;
import code.expressionlanguage.exec.blocks.ExecForIterativeLoop;
import code.expressionlanguage.assign.util.Assignment;
import code.expressionlanguage.assign.util.AssignmentBefore;
import code.expressionlanguage.assign.util.AssignmentsUtil;
import code.expressionlanguage.assign.util.SimpleAssignment;
import code.util.*;

public final class AssForIterativeLoop extends AssBracedStack implements AssLoop, AssBuildableElMethod {
    private String label;
    private CustList<AssOperationNode> opInit;

    private CustList<AssOperationNode> opExp;

    private CustList<AssOperationNode> opStep;
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
    public void buildExpressionLanguage(ContextEl _cont, AssignedVariablesBlock _a) {
        AssUtil.getSortedDescNodes(_a,opInit.last(),this,_cont);
        AssUtil.getSortedDescNodes(_a,opExp.last(),this,_cont);
        AssUtil.getSortedDescNodes(_a,opStep.last(),this,_cont);
    }

    @Override
    public void setAssignmentBeforeChild(ContextEl _an, AssignedVariablesBlock _anEl) {
        AssBlock firstChild_ = getFirstChild();
        IdMap<AssBlock, AssignedVariables> id_ = _anEl.getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(this);
        AssignedVariables assBl_ = firstChild_.buildNewAssignedVariable();
        assBl_.getFieldsRootBefore().putAllMap(AssignmentsUtil.assignSimpleBefore(parAss_.getFieldsRoot()));
        assBl_.getVariablesRootBefore().putAllMap(AssignmentsUtil.assignSimpleBefore(parAss_.getVariablesRoot()));
        id_.put(firstChild_, assBl_);
    }

    @Override
    public void defaultAssignmentBefore(ContextEl _an, AssignedVariablesBlock _a, AssOperationNode _root) {
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

    @Override
    public void defaultAssignmentAfter(ContextEl _an, AssignedVariablesBlock _a, boolean _root) {
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
    public void setAssignmentAfter(ContextEl _an, AssignedVariablesBlock _anEl) {
        AssignedVariablesDesc ass_ = new AssignedVariablesDesc(_an,this,_anEl);
        AssignedVariables varsWhile_ = ass_.getVarsWhile();
        IdMap<AssBlock, AssignedVariables> allDesc_ = ass_.getAllDesc();
        StringMap<AssignmentBefore> fieldsHypot_;
        StringMap<AssignmentBefore> varsHypot_;
        fieldsHypot_ = buildAssListFieldAfterInvalHypot(_an, _anEl);
        varsWhile_.getFieldsRootBefore().putAllMap(fieldsHypot_);
        varsHypot_ = buildAssListLocVarInvalHypot(_an, _anEl);
        varsWhile_.getVariablesRootBefore().clear();
        varsWhile_.getVariablesRootBefore().putAllMap(varsHypot_);
        processFinalFields(_an, allDesc_, varsWhile_, fieldsHypot_);
        processFinalVars(_an,_anEl, allDesc_, varsWhile_, varsHypot_);
        StringMap<SimpleAssignment> fieldsAfter_;
        StringMap<SimpleAssignment> varsAfter_;
        fieldsAfter_= buildAssListFieldAfterLoop(_an, _anEl);
        varsWhile_.getFieldsRoot().putAllMap(fieldsAfter_);
        varsAfter_ = buildAssListLocVarAfterLoop(_an, _anEl);
        varsWhile_.getVariablesRoot().clear();
        varsWhile_.getVariablesRoot().putAllMap(varsAfter_);
    }
    protected StringMap<AssignmentBefore> buildAssListFieldAfterInvalHypot(ContextEl _an, AssignedVariablesBlock _anEl) {
        AssBlock first_ = getFirstChild();
        AssBlock last_ = first_;
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        CustList<AssContinueBlock> continues_ = getContinuables();
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        StringMap<AssignmentBefore> list_;
        list_ = first_.makeHypothesisFields(_an,_anEl);
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
    protected StringMap<AssignmentBefore> buildAssListLocVarInvalHypot(ContextEl _an, AssignedVariablesBlock _anEl) {
        AssBlock first_ = getFirstChild();
        AssBlock last_ = first_;
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        CustList<AssContinueBlock> continues_ = getContinuables();
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        StringMap<AssignmentBefore> list_;
        list_ = first_.makeHypothesisVars(_an,_anEl);
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
            return(invalidateHypothesis(list_, v_, breakAss_));
        } else {
            return(invalidateHypothesis(list_, new StringMap<SimpleAssignment>(), breakAss_));
        }
    }

    private static StringMap<AssignmentBefore> invalidateHypothesis(StringMap<AssignmentBefore> _loop, StringMap<SimpleAssignment> _last,
                                                                    CustList<StringMap<AssignmentBefore>> _continuable) {
        StringMap<AssignmentBefore> out_ = new StringMap<AssignmentBefore>();
        for (EntryCust<String,AssignmentBefore> e: _loop.entryList()) {
            String key_ = e.getKey();
            AssignmentBefore ass_ = e.getValue().copy();
            for (StringMap<AssignmentBefore> c: _continuable) {
                for (EntryCust<String,AssignmentBefore> f:c.entryList()) {
                    if (!StringList.quickEq(f.getKey(), key_)) {
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

}
