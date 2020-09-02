package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.ForMutableIterativeLoop;
import code.expressionlanguage.assign.opers.AssOperationNode;
import code.expressionlanguage.assign.opers.AssUtil;
import code.expressionlanguage.assign.util.*;
import code.expressionlanguage.exec.blocks.ExecForMutableIterativeLoop;
import code.expressionlanguage.analyze.blocks.ForLoopPart;
import code.expressionlanguage.assign.util.Assignment;
import code.expressionlanguage.assign.util.AssignmentBefore;
import code.expressionlanguage.assign.util.AssignmentsUtil;
import code.expressionlanguage.assign.util.SimpleAssignment;
import code.util.*;

public final class AssForMutableIterativeLoop extends AssBracedStack implements AssBuildableElMethod,AssLoop {

    private CustList<AssOperationNode> opInit;

    private CustList<AssOperationNode> opExp;

    private CustList<AssOperationNode> opStep;
    private String label;
    private final boolean finalVar;
    AssForMutableIterativeLoop(boolean _completeNormally, boolean _completeNormallyGroup, boolean _final, String _label,ForMutableIterativeLoop _block) {
        super(_completeNormally,_completeNormallyGroup);
        opInit = AssUtil.getExecutableNodes(_block.getRootInit());
        opExp = AssUtil.getExecutableNodes(_block.getRootExp());
        opStep = AssUtil.getExecutableNodes(_block.getRootStep());
        label = _label;
        finalVar = _final;
    }
    @Override
    protected AssignedVariables buildNewAssignedVariable() {
        return new AssignedBooleanLoopVariables();
    }
    @Override
    public void defaultAssignmentBefore(ContextEl _an, AssignedVariablesBlock _a, AssOperationNode _root) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(this);
        StringMap<AssignmentBefore> fields_;
        StringMap<AssignmentBefore> variables_;
        fields_ = new StringMap<AssignmentBefore>();
        variables_ = new StringMap<AssignmentBefore>();
        if (_an.getAnalyzing().getForLoopPartState() == ForLoopPart.INIT) {
            fields_.putAllMap(AssignmentsUtil.copyBefore(vars_.getFieldsRootBefore()));
            variables_.putAllMap(AssignmentsUtil.copyBefore(vars_.getVariablesRootBefore()));
        } else if (_an.getAnalyzing().getForLoopPartState() == ForLoopPart.CONDITION) {
            if (opInit.isEmpty()) {
                fields_.putAllMap(AssignmentsUtil.copyBefore(vars_.getFieldsRootBefore()));
                variables_.putAllMap(AssignmentsUtil.copyBefore(vars_.getVariablesRootBefore()));
            } else {
                fields_.putAllMap(AssignmentsUtil.assignSimpleBefore(vars_.getFieldsRoot()));
                variables_.putAllMap(AssignmentsUtil.assignSimpleBefore(vars_.getVariablesRoot()));
            }
        } else {
            fields_ = buildAssListFieldBeforeIncrPart(_an, _a);
            variables_ = buildAssListLocVarBeforeIncrPart(_an, _a);
        }
        vars_.getFieldsBefore().put(_root, fields_);
        vars_.getVariablesBefore().put(_root, variables_);
    }

    @Override
    public void setAssignmentBeforeChild(ContextEl _an, AssignedVariablesBlock _a) {
        assignWhenTrue(_an,_a);
    }

    @Override
    public void setAssignmentBeforeNextSibling(ContextEl _an, AssignedVariablesBlock _a) {
        IdMap<AssBlock, AssignedVariables> id_ = _a.getFinalVariables();
        AssignedVariables prevAss_ = id_.getVal(this);
        AssBlock nextSibling_ = getNextSibling();
        AssignedVariables assBl_ = nextSibling_.buildNewAssignedVariable();
        assBl_.getFieldsRootBefore().putAllMap(AssignmentsUtil.assignSimpleBefore(prevAss_.getFieldsRoot()));
        assBl_.getVariablesRootBefore().putAllMap(AssignmentsUtil.assignSimpleBefore(prevAss_.getVariablesRoot()));
        id_.put(nextSibling_, assBl_);
    }

    @Override
    public void defaultAssignmentAfter(ContextEl _an, AssignedVariablesBlock _a, AssOperationNode _root) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(this);
        if (_an.getAnalyzing().getForLoopPartState() == ForLoopPart.INIT) {
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
        processFinalVars(_an, _anEl,allDesc_, varsWhile_, varsHypot_);
        StringMap<SimpleAssignment> fieldsAfter_;
        fieldsAfter_= buildAssListFieldAfterLoop(_an, _anEl);
        varsWhile_.getFieldsRoot().putAllMap(fieldsAfter_);
        StringMap<SimpleAssignment> varsAfter_;
        varsAfter_ = buildAssListLocVarAfterLoop(_an, _anEl);
        varsWhile_.getVariablesRoot().clear();
        varsWhile_.getVariablesRoot().putAllMap(varsAfter_);
    }
    private StringMap<AssignmentBefore> buildAssListFieldAfterInvalHypot(ContextEl _an, AssignedVariablesBlock _anEl) {
        AssBlock last_ = getFirstChild();
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        CustList<AssContinueBlock> continues_ = getContinuables();
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        StringMap<AssignmentBefore> list_;
        list_ = makeHypothesisFields(_an,_anEl);
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
    private StringMap<AssignmentBefore> buildAssListLocVarInvalHypot(ContextEl _an, AssignedVariablesBlock _anEl) {
        AssBlock last_ = getFirstChild();
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        CustList<AssContinueBlock> continues_ = getContinuables();
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        StringMap<AssignmentBefore> varsList_;
        StringMap<AssignmentBefore> list_;
        list_ = makeHypothesisVars(_an,_anEl);
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
    protected StringMap<AssignmentBefore> makeHypothesisFields(ContextEl _an, AssignedVariablesBlock _a) {
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
    protected StringMap<AssignmentBefore> makeHypothesisVars(ContextEl _an, AssignedVariablesBlock _a) {
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

    @Override
    public void buildExpressionLanguage(ContextEl _cont, AssignedVariablesBlock _a) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setForLoopPartState(ForLoopPart.INIT);
        page_.setFinalVariable(finalVar);
        if (!opInit.isEmpty()) {
            AssUtil.getSortedDescNodes(_a,opInit.last(),this,_cont);
        }
        page_.setForLoopPartState(ForLoopPart.CONDITION);
        if (!opExp.isEmpty()) {
            AssUtil.getSortedDescNodes(_a,opExp.last(),this,_cont);
        }
        if (!opExp.isEmpty()) {
            buildConditions(_cont,_a);
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
    public void buildIncrementPart(ContextEl _an, AssignedVariablesBlock _a) {
        AnalyzedPageEl page_ = _an.getAnalyzing();
        page_.setForLoopPartState(ForLoopPart.STEP);
        if (!opStep.isEmpty()) {
            AssUtil.getSortedDescNodes(_a,opStep.last(),this,_an);
        }
    }

    @Override
    public String getRealLabel() {
        return label;
    }
}
