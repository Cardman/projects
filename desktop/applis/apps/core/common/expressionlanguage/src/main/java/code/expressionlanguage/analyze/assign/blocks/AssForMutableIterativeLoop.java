package code.expressionlanguage.analyze.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.ForMutableIterativeLoop;
import code.expressionlanguage.analyze.assign.opers.AssOperationNode;
import code.expressionlanguage.analyze.assign.opers.AssUtil;
import code.expressionlanguage.analyze.assign.util.*;
import code.expressionlanguage.analyze.blocks.ForLoopPart;
import code.expressionlanguage.analyze.assign.util.Assignment;
import code.expressionlanguage.analyze.assign.util.AssignmentBefore;
import code.expressionlanguage.analyze.assign.util.AssignmentsUtil;
import code.expressionlanguage.analyze.assign.util.SimpleAssignment;
import code.util.*;
import code.util.core.StringUtil;

public final class AssForMutableIterativeLoop extends AssBracedStack implements AssBuildableElMethod,AssLoop {

    private CustList<AssOperationNode> opInit;

    private CustList<AssOperationNode> opExp;

    private CustList<AssOperationNode> opStep;
    private String label;
    AssForMutableIterativeLoop(boolean _completeNormally, boolean _completeNormallyGroup, String _label,ForMutableIterativeLoop _block) {
        super(_completeNormally,_completeNormallyGroup);
        opInit = AssUtil.getExecutableNodes(_block.getRootInit());
        opExp = AssUtil.getExecutableNodes(_block.getRootExp());
        opStep = AssUtil.getExecutableNodes(_block.getRootStep());
        label = _label;
    }
    @Override
    protected AssignedVariables buildNewAssignedVariable() {
        return new AssignedBooleanLoopVariables();
    }
    @Override
    public void defaultAssignmentBefore(AssignedVariablesBlock _a, AssOperationNode _root, AnalyzedPageEl _page) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(this);
        StringMap<AssignmentBefore> fields_;
        StringMap<AssignmentBefore> variables_;
        fields_ = new StringMap<AssignmentBefore>();
        variables_ = new StringMap<AssignmentBefore>();
        if (_page.getForLoopPartState() == ForLoopPart.INIT) {
            fields_.putAllMap(AssignmentsUtil.copyBefore(vars_.getFieldsRootBefore()));
            variables_.putAllMap(AssignmentsUtil.copyBefore(vars_.getVariablesRootBefore()));
        } else if (_page.getForLoopPartState() == ForLoopPart.CONDITION) {
            if (opInit.isEmpty()) {
                fields_.putAllMap(AssignmentsUtil.copyBefore(vars_.getFieldsRootBefore()));
                variables_.putAllMap(AssignmentsUtil.copyBefore(vars_.getVariablesRootBefore()));
            } else {
                fields_.putAllMap(AssignmentsUtil.assignSimpleBefore(vars_.getFieldsRoot()));
                variables_.putAllMap(AssignmentsUtil.assignSimpleBefore(vars_.getVariablesRoot()));
            }
        } else {
            fields_ = buildAssListFieldBeforeIncrPart(_a);
            variables_ = buildAssListLocVarBeforeIncrPart(_a);
        }
        vars_.getFieldsBefore().put(_root, fields_);
        vars_.getVariablesBefore().put(_root, variables_);
    }

    @Override
    public void setAssignmentBeforeChild(AssignedVariablesBlock _a) {
        assignWhenTrue(_a);
    }

    @Override
    public void setAssignmentBeforeNextSibling(AssignedVariablesBlock _a) {
        IdMap<AssBlock, AssignedVariables> id_ = _a.getFinalVariables();
        AssignedVariables prevAss_ = id_.getVal(this);
        AssBlock nextSibling_ = getNextSibling();
        AssignedVariables assBl_ = nextSibling_.buildNewAssignedVariable();
        assBl_.getFieldsRootBefore().putAllMap(AssignmentsUtil.assignSimpleBefore(prevAss_.getFieldsRoot()));
        assBl_.getVariablesRootBefore().putAllMap(AssignmentsUtil.assignSimpleBefore(prevAss_.getVariablesRoot()));
        id_.put(nextSibling_, assBl_);
    }

    @Override
    public void defaultAssignmentAfter(AssignedVariablesBlock _a, boolean _root, AnalyzedPageEl _page) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(this);
        if (_page.getForLoopPartState() == ForLoopPart.INIT) {
            AssignedBooleanLoopVariables loop_ = (AssignedBooleanLoopVariables) vars_;
            StringMap<Assignment> res_ = vars_.getLastFieldsOrEmpty();
            loop_.getFieldsRootAfterInit().putAllMap(res_);
            StringMap<Assignment> varsRes_;
            varsRes_ = vars_.getLastVariablesOrEmpty();
            loop_.getVariablesRootAfterInit().putAllMap(varsRes_);
        }
        StringMap<Assignment> res_ = vars_.getLastFieldsOrEmpty();
        vars_.getFieldsRoot().putAllMap(AssignmentsUtil.assignClassic(res_));
        StringMap<Assignment> varsRes_;
        varsRes_ = vars_.getLastVariablesOrEmpty();
        vars_.getVariablesRoot().clear();
        vars_.getVariablesRoot().putAllMap(AssignmentsUtil.assignClassic(varsRes_));
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
    private StringMap<AssignmentBefore> buildAssListFieldAfterInvalHypot(AssignedVariablesBlock _anEl) {
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
    private StringMap<AssignmentBefore> buildAssListLocVarInvalHypot(AssignedVariablesBlock _anEl) {
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

    @Override
    protected StringMap<AssignmentBefore> makeHypothesisFields(AssignedVariablesBlock _a) {
        AssignedBooleanLoopVariables vars_ = (AssignedBooleanLoopVariables) _a.getFinalVariables().getVal(this);
        StringMap<AssignmentBefore> fields_;
        if (opInit.isEmpty()) {
            fields_ = AssignmentsUtil.getHypoAssignmentBefore(vars_.getFieldsRootBefore());
        } else {
            fields_ = AssignmentsUtil.getHypoAssignmentAfter(vars_.getFieldsRootAfterInit());
        }
        return fields_;
    }

    @Override
    protected StringMap<AssignmentBefore> makeHypothesisVars(AssignedVariablesBlock _a) {
        AssignedBooleanLoopVariables vars_ = (AssignedBooleanLoopVariables) _a.getFinalVariables().getVal(this);
        StringMap<AssignmentBefore> variables_;
        if (opInit.isEmpty()) {
            variables_ = AssignmentsUtil.getHypoAssignmentBefore(vars_.getVariablesRootBefore());
        } else {
            variables_ = AssignmentsUtil.getHypoAssignmentAfter(vars_.getVariablesRootAfterInit());
        }
        return variables_;
    }

    private static StringMap<AssignmentBefore> invalidateHypothesis(StringMap<AssignmentBefore> _loop, StringMap<SimpleAssignment> _last,
                                                                    CustList<StringMap<AssignmentBefore>> _continuable) {
        StringMap<AssignmentBefore> out_ = new StringMap<AssignmentBefore>();
        for (EntryCust<String,AssignmentBefore> e: _loop.entryList()) {
            String key_ = e.getKey();
            AssignmentBefore ass_ = e.getValue().copy();
            for (StringMap<AssignmentBefore> c: _continuable) {
                for (EntryCust<String,AssignmentBefore> f:c.entryList()) {
                    if (!StringUtil.quickEq(f.getKey(), key_)) {
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
    public void buildExpressionLanguage(AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        _page.setForLoopPartState(ForLoopPart.INIT);
        if (!opInit.isEmpty()) {
            AssUtil.getSortedDescNodes(_a,opInit.last(),this, _page);
        }
        _page.setForLoopPartState(ForLoopPart.CONDITION);
        if (!opExp.isEmpty()) {
            AssUtil.getSortedDescNodes(_a,opExp.last(),this, _page);
        }
        if (!opExp.isEmpty()) {
            buildConditions(_a);
        } else {
            AssignedBooleanVariables res_ = (AssignedBooleanVariables) _a.getFinalVariables().getVal(this);
            if (opInit.isEmpty()) {
                res_.getFieldsRootAfter().putAllMap(AssignmentsUtil.conditionBefore(res_.getFieldsRootBefore()));
                res_.getVariablesRootAfter().putAllMap(AssignmentsUtil.conditionBefore(res_.getVariablesRootBefore()));
            } else {
                res_.getFieldsRootAfter().putAllMap(AssignmentsUtil.conditionAfter(res_.getLastFieldsOrEmpty()));
                res_.getVariablesRootAfter().putAllMap(AssignmentsUtil.conditionAfter(res_.getLastVariablesOrEmpty()));
            }
        }
    }
    public void buildIncrementPart(AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        _page.setForLoopPartState(ForLoopPart.STEP);
        if (!opStep.isEmpty()) {
            AssUtil.getSortedDescNodes(_a,opStep.last(),this, _page);
        }
    }

    @Override
    public String getRealLabel() {
        return label;
    }
}
