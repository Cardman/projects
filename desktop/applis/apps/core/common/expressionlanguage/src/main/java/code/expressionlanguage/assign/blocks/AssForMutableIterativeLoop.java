package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
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
    AssForMutableIterativeLoop(boolean _completeNormally, boolean _completeNormallyGroup, boolean _final, String _label,ExecForMutableIterativeLoop _block) {
        super(_completeNormally,_completeNormallyGroup);
        opInit = AssUtil.getExecutableNodes(_block.getOpInit());
        opExp = AssUtil.getExecutableNodes(_block.getOpExp());
        opStep = AssUtil.getExecutableNodes(_block.getOpStep());
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
        CustList<StringMap<AssignmentBefore>> variables_;
        fields_ = new StringMap<AssignmentBefore>();
        variables_ = new CustList<StringMap<AssignmentBefore>>();
        CustList<StringMap<AssignmentBefore>> mutable_;
        mutable_ = new CustList<StringMap<AssignmentBefore>>();
        if (_an.getAnalyzing().getForLoopPartState() == ForLoopPart.INIT) {
            fields_.putAllMap(AssignmentsUtil.copyBefore(vars_.getFieldsRootBefore()));
            variables_.addAllElts(AssignmentsUtil.copyBefore(vars_.getVariablesRootBefore()));
            mutable_.addAllElts(AssignmentsUtil.copyBefore(vars_.getMutableLoopRootBefore()));
        } else if (_an.getAnalyzing().getForLoopPartState() == ForLoopPart.CONDITION) {
            if (opInit.isEmpty()) {
                fields_.putAllMap(AssignmentsUtil.copyBefore(vars_.getFieldsRootBefore()));
                variables_.addAllElts(AssignmentsUtil.copyBefore(vars_.getVariablesRootBefore()));
                mutable_.addAllElts(AssignmentsUtil.copyBefore(vars_.getMutableLoopRootBefore()));
            } else {
                fields_.putAllMap(AssignmentsUtil.assignSimpleBefore(vars_.getFieldsRoot()));
                variables_.addAllElts(AssignmentsUtil.assignSimpleBefore(vars_.getVariablesRoot()));
                mutable_.addAllElts(AssignmentsUtil.assignSimpleBefore(vars_.getMutableLoopRoot()));
            }
        } else {
            fields_ = buildAssListFieldBeforeIncrPart(_an, _a);
            variables_ = buildAssListLocVarBeforeIncrPart(_an, _a);
            mutable_ = buildAssListMutableLoopBeforeIncrPart(_an, _a);
        }
        vars_.getFieldsBefore().put(_root, fields_);
        vars_.getVariablesBefore().put(_root, variables_);
        vars_.getMutableLoopBefore().put(_root, mutable_);
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
        assBl_.getVariablesRootBefore().addAllElts(AssignmentsUtil.assignSimpleBefore(prevAss_.getVariablesRoot()));
        CustList<StringMap<SimpleAssignment>> mutable_ = prevAss_.getMutableLoopRoot();
        assBl_.getMutableLoopRootBefore().addAllElts(AssignmentsUtil.assignSimpleBefore(mutable_.sub(0,mutable_.size()-1)));
        int nb_ = Math.min(1, mutable_.size());
        for (int i = 0; i< nb_;i++) {
            assBl_.getMutableLoopRootBefore().add(new StringMap<AssignmentBefore>());
        }
        id_.put(nextSibling_, assBl_);
    }

    @Override
    public void defaultAssignmentAfter(ContextEl _an, AssignedVariablesBlock _a, AssOperationNode _root) {
        AssignedVariables vars_ = _a.getFinalVariables().getVal(this);
        if (_an.getAnalyzing().getForLoopPartState() == ForLoopPart.INIT) {
            AssignedBooleanLoopVariables loop_ = (AssignedBooleanLoopVariables) vars_;
            StringMap<Assignment> res_ = vars_.getLastFieldsOrEmpty();
            loop_.getFieldsRootAfterInit().putAllMap(res_);
            CustList<StringMap<Assignment>> varsRes_;
            varsRes_ = vars_.getLastVariablesOrEmpty();
            for (StringMap<Assignment> s: varsRes_) {
                loop_.getVariablesRootAfterInit().add(s);
            }
            CustList<StringMap<Assignment>> mutableRes_;
            mutableRes_ = vars_.getLastMutableLoopOrEmpty();
            for (StringMap<Assignment> s: mutableRes_) {
                loop_.getMutableLoopRootAfterInit().add(s);
            }
        }
        StringMap<Assignment> res_ = vars_.getLastFieldsOrEmpty();
        vars_.getFieldsRoot().putAllMap(AssignmentsUtil.assignClassic(res_));
        CustList<StringMap<Assignment>> varsRes_;
        varsRes_ = vars_.getLastVariablesOrEmpty();
        vars_.getVariablesRoot().clear();
        vars_.getVariablesRoot().addAllElts(AssignmentsUtil.assignClassic(varsRes_));
        CustList<StringMap<Assignment>> mutableRes_;
        mutableRes_ = vars_.getLastMutableLoopOrEmpty();
        vars_.getMutableLoopRoot().clear();
        vars_.getMutableLoopRoot().addAllElts(AssignmentsUtil.assignClassic(mutableRes_));
    }

    @Override
    public void setAssignmentAfter(ContextEl _an, AssignedVariablesBlock _anEl) {
        AssignedVariablesDesc ass_ = new AssignedVariablesDesc(_an,this,_anEl);
        AssignedVariables varsWhile_ = ass_.getVarsWhile();
        IdMap<AssBlock, AssignedVariables> allDesc_ = ass_.getAllDesc();
        StringMap<AssignmentBefore> fieldsHypot_;
        CustList<StringMap<AssignmentBefore>> varsHypot_;
        CustList<StringMap<AssignmentBefore>> mutableHypot_;
        fieldsHypot_ = buildAssListFieldAfterInvalHypot(_an, _anEl);
        varsWhile_.getFieldsRootBefore().putAllMap(fieldsHypot_);
        varsHypot_ = buildAssListLocVarInvalHypot(_an, _anEl);
        varsWhile_.getVariablesRootBefore().clear();
        varsWhile_.getVariablesRootBefore().addAllElts(varsHypot_);
        mutableHypot_ = buildAssListMutableLoopInvalHypot(_an, _anEl);
        varsWhile_.getMutableLoopRootBefore().clear();
        varsWhile_.getMutableLoopRootBefore().addAllElts(mutableHypot_);
        processFinalFields(_an, allDesc_, varsWhile_, fieldsHypot_);
        processFinalVars(_an, _anEl,allDesc_, varsWhile_, varsHypot_);
        processFinalMutableLoop(_an, _anEl,allDesc_, varsWhile_, mutableHypot_);
        StringMap<SimpleAssignment> fieldsAfter_;
        fieldsAfter_= buildAssListFieldAfterLoop(_an, _anEl);
        varsWhile_.getFieldsRoot().putAllMap(fieldsAfter_);
        CustList<StringMap<SimpleAssignment>> varsAfter_;
        CustList<StringMap<SimpleAssignment>> mutableAfter_;
        varsAfter_ = buildAssListLocVarAfterLoop(_an, _anEl);
        varsWhile_.getVariablesRoot().clear();
        varsWhile_.getVariablesRoot().addAllElts(varsAfter_);
        mutableAfter_ = buildAssListMutableLoopAfterLoop(_an, _anEl);
        varsWhile_.getMutableLoopRoot().clear();
        varsWhile_.getMutableLoopRoot().addAllElts(mutableAfter_);
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
    private CustList<StringMap<AssignmentBefore>> buildAssListLocVarInvalHypot(ContextEl _an, AssignedVariablesBlock _anEl) {
        AssBlock last_ = getFirstChild();
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        CustList<AssContinueBlock> continues_ = getContinuables();
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        CustList<StringMap<AssignmentBefore>> varsList_;
        varsList_ = new CustList<StringMap<AssignmentBefore>>();
        CustList<StringMap<AssignmentBefore>> list_;
        list_ = makeHypothesisVars(_an,_anEl);
        int contLen_ = continues_.size();
        int loopLen_ = list_.size();
        for (int i = 0; i < loopLen_; i++) {
            CustList<StringMap<AssignmentBefore>> breakAss_;
            breakAss_ = new CustList<StringMap<AssignmentBefore>>();
            for (int j = 0; j < contLen_; j++) {
                AssContinueBlock br_ = continues_.get(j);
                AssignedVariables ass_ = id_.getVal(br_);
                CustList<StringMap<AssignmentBefore>> vars_ = ass_.getVariablesRootBefore();
                breakAss_.add(AssignmentsUtil.getOrEmptyBefore(vars_,i));
            }
            StringMap<AssignmentBefore> cond_ = list_.get(i);
            if (last_.isCompleteNormallyGroup()) {
                AssignedVariables ass_ = id_.getVal(last_);
                CustList<StringMap<SimpleAssignment>> v_ = ass_.getVariablesRoot();
                varsList_.add(invalidateHypothesis(cond_, AssignmentsUtil.getOrEmptySimple(v_,i), breakAss_));
            } else {
                varsList_.add(invalidateHypothesis(cond_, new StringMap<SimpleAssignment>(), breakAss_));
            }
        }

        return varsList_;
    }
    private CustList<StringMap<AssignmentBefore>> buildAssListMutableLoopInvalHypot(ContextEl _an, AssignedVariablesBlock _anEl) {
        AssBlock last_ = getFirstChild();
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        CustList<AssContinueBlock> continues_ = getContinuables();
        IdMap<AssBlock, AssignedVariables> id_;
        id_ = _anEl.getFinalVariables();
        CustList<StringMap<AssignmentBefore>> varsList_;
        varsList_ = new CustList<StringMap<AssignmentBefore>>();
        CustList<StringMap<AssignmentBefore>> list_;
        list_ = makeHypothesisMutableLoop(_an,_anEl);
        int contLen_ = continues_.size();
        int loopLen_ = list_.size();
        for (int i = 0; i < loopLen_; i++) {
            CustList<StringMap<AssignmentBefore>> breakAss_;
            breakAss_ = new CustList<StringMap<AssignmentBefore>>();
            for (int j = 0; j < contLen_; j++) {
                AssContinueBlock br_ = continues_.get(j);
                AssignedVariables ass_ = id_.getVal(br_);
                CustList<StringMap<AssignmentBefore>> vars_ = ass_.getMutableLoopRootBefore();
                breakAss_.add(AssignmentsUtil.getOrEmptyBefore(vars_,i));
            }
            StringMap<AssignmentBefore> cond_ = list_.get(i);
            if (last_.isCompleteNormallyGroup()) {
                AssignedVariables ass_ = id_.getVal(last_);
                CustList<StringMap<SimpleAssignment>> v_ = ass_.getMutableLoopRoot();
                varsList_.add(invalidateHypothesis(cond_, AssignmentsUtil.getOrEmptySimple(v_,i), breakAss_));
            } else {
                varsList_.add(invalidateHypothesis(cond_, new StringMap<SimpleAssignment>(), breakAss_));
            }
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
    protected CustList<StringMap<AssignmentBefore>> makeHypothesisVars(ContextEl _an, AssignedVariablesBlock _a) {
        AssignedBooleanLoopVariables vars_ = (AssignedBooleanLoopVariables) _a.getFinalVariables().getVal(this);
        CustList<StringMap<AssignmentBefore>> variables_;
        variables_ = new CustList<StringMap<AssignmentBefore>>();
        if (opInit.isEmpty()) {
            for (StringMap<AssignmentBefore> s: vars_.getVariablesRootBefore()) {
                StringMap<AssignmentBefore> sm_ = AssignmentsUtil.getHypoAssignmentBefore(s);
                variables_.add(sm_);
            }
        } else {
            for (StringMap<Assignment> s: vars_.getVariablesRootAfterInit()) {
                StringMap<AssignmentBefore> sm_ = AssignmentsUtil.getHypoAssignmentAfter(s);
                variables_.add(sm_);
            }
        }
        return variables_;
    }

    @Override
    protected CustList<StringMap<AssignmentBefore>> makeHypothesisMutableLoop(ContextEl _an, AssignedVariablesBlock _a) {
        AssignedBooleanLoopVariables vars_ = (AssignedBooleanLoopVariables) _a.getFinalVariables().getVal(this);
        CustList<StringMap<AssignmentBefore>> variables_;
        variables_ = new CustList<StringMap<AssignmentBefore>>();
        if (opInit.isEmpty()) {
            for (StringMap<AssignmentBefore> s: vars_.getMutableLoopRootBefore()) {
                StringMap<AssignmentBefore> sm_ = AssignmentsUtil.getHypoAssignmentBefore(s);
                variables_.add(sm_);
            }
        } else {
            for (StringMap<Assignment> s: vars_.getMutableLoopRootAfterInit()) {
                StringMap<AssignmentBefore> sm_ = AssignmentsUtil.getHypoAssignmentAfter(s);
                variables_.add(sm_);
            }
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
                res_.getVariablesRootAfter().addAllElts(AssignmentsUtil.conditionBefore(res_.getVariablesRootBefore()));
                res_.getMutableLoopRootAfter().addAllElts(AssignmentsUtil.conditionBefore(res_.getMutableLoopRootBefore()));
            } else {
                res_.getFieldsRootAfter().putAllMap(AssignmentsUtil.conditionAfter(res_.getLastFieldsOrEmpty()));
                res_.getVariablesRootAfter().addAllElts(AssignmentsUtil.conditionAfter(res_.getLastVariablesOrEmpty()));
                res_.getMutableLoopRootAfter().addAllElts(AssignmentsUtil.conditionAfter(res_.getLastMutableLoopOrEmpty()));
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
