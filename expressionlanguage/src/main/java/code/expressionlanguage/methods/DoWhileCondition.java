package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ConditionReturn;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.util.ReadWrite;
import code.expressionlanguage.errors.custom.EmptyTagName;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.util.AbstractCoverageResult;
import code.expressionlanguage.methods.util.AssignedVariablesDesc;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.util.*;

public final class DoWhileCondition extends Condition {

    public DoWhileCondition(OffsetStringInfo _condition, OffsetsBlock _offset) {
        super(_condition, _offset);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        LoopBlockStack l_ = (LoopBlockStack) ip_.getLastStack();
        l_.setEvaluatingKeepLoop(true);
        _cont.getLastPage().setGlobalOffset(getOffset().getOffsetTrim());
        _cont.getLastPage().setOffset(0);
        ConditionReturn keep_ = evaluateCondition(_cont);
        if (keep_ == ConditionReturn.CALL_EX) {
            return;
        }
        if (keep_ == ConditionReturn.NO) {
            l_.setFinished(true);
        }
        l_.setEvaluatingKeepLoop(false);
        rw_.setBlock(getPreviousSibling());
    }

    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        Loop previous_ = (Loop) getPreviousSibling();
        boolean abr_ = true;
        Block last_ = getPreviousSibling().getFirstChild();
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        ExecOperationNode op_ = getRoot();
        Argument arg_ = op_.getArgument();
        boolean proc_ = Argument.isTrueValue(arg_);
        if (!proc_) {
            if (_anEl.canCompleteNormallyGroup(last_)) {
                abr_ = false;
            }
        }
        if (!proc_) {
            IdMap<ContinueBlock, Loop> breakables_;
            breakables_ = _anEl.getContinuables();
            for (EntryCust<ContinueBlock, Loop> e: breakables_.entryList()) {
                if (e.getValue() == previous_ && _anEl.isReachable(e.getKey())) {
                    abr_ = false;
                    break;
                }
            }
        }
        IdMap<BreakBlock, BreakableBlock> breakables_;
        breakables_ = _anEl.getBreakables();
        for (EntryCust<BreakBlock, BreakableBlock> e: breakables_.entryList()) {
            if (e.getValue() == previous_ && _anEl.isReachable(e.getKey())) {
                abr_ = false;
                break;
            }
        }
        if (abr_) {
            _anEl.completeAbruptGroup(this);
        }
    }

    @Override
    public void checkTree(Analyzable _an, AnalyzingEl _anEl) {
        if (getFirstChild() != null) {
            EmptyTagName un_ = new EmptyTagName();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            _an.addError(un_);
        }
    }

    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getContextEl().getAssignedVariables().getFinalVariables();
        //by do block
        DoBlock dBlock_ = (DoBlock) getPreviousSibling();
        StringMap<AssignmentBefore> fieldsHypot_;
        CustList<StringMap<AssignmentBefore>> varsHypot_;
        CustList<StringMap<AssignmentBefore>> mutableHypot_;

        AssignedVariablesDesc ass_ = new AssignedVariablesDesc(_an,dBlock_);
        IdMap<Block, AssignedVariables> allDesc_ = ass_.getAllDesc();
        AssignedVariables varsDo_;
        varsDo_ = ass_.getVarsWhile();
        AssignedBooleanVariables varsWhile_;
        varsWhile_ = (AssignedBooleanVariables) id_.getVal(this);
        fieldsHypot_ = buildAssListFieldAfterInvalHypot(_an);
        varsDo_.getFieldsRootBefore().putAllMap(fieldsHypot_);
        varsHypot_ = buildAssListLocVarInvalHypot(_an);
        varsDo_.getVariablesRootBefore().clear();
        varsDo_.getVariablesRootBefore().addAllElts(varsHypot_);
        mutableHypot_ = buildAssListMutableLoopInvalHypot(_an);
        varsDo_.getMutableLoopRootBefore().clear();
        varsDo_.getMutableLoopRootBefore().addAllElts(mutableHypot_);
        processFinalFields(_an, allDesc_, varsDo_, fieldsHypot_);
        processFinalVars(_an, allDesc_, varsDo_, varsHypot_);
        processFinalMutableLoop(_an, allDesc_, varsDo_, mutableHypot_);

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

    protected StringMap<AssignmentBefore> buildAssListFieldAfterInvalHypot(Analyzable _an) {
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getContextEl().getAssignedVariables().getFinalVariables();
        DoBlock dBlock_ = (DoBlock) getPreviousSibling();
        StringMap<AssignmentBefore> list_;
        list_ = dBlock_.makeHypothesisFields(_an);
        StringMap<BooleanAssignment> end_;
        end_ = ((AssignedBooleanVariables) id_.getVal(this)).getFieldsRootAfter();
        return invalidateHypothesis(list_, end_);
    }
    protected CustList<StringMap<AssignmentBefore>> buildAssListLocVarInvalHypot(Analyzable _an) {
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getContextEl().getAssignedVariables().getFinalVariables();
        CustList<StringMap<AssignmentBefore>> varsList_;
        varsList_ = new CustList<StringMap<AssignmentBefore>>();
        DoBlock dBlock_ = (DoBlock) getPreviousSibling();
        CustList<StringMap<AssignmentBefore>> list_;
        list_ = dBlock_.makeHypothesisVars(_an);
        int loopLen_ = list_.size();
        
        CustList<StringMap<BooleanAssignment>> end_;
        end_ = ((AssignedBooleanVariables) id_.getVal(this)).getVariablesRootAfter();
        for (int i = 0; i < loopLen_; i++) {
            StringMap<AssignmentBefore> cond_ = list_.get(i);
            varsList_.add(invalidateHypothesis(cond_, end_.get(i)));
        }
        
        return varsList_;
    }
    protected CustList<StringMap<AssignmentBefore>> buildAssListMutableLoopInvalHypot(Analyzable _an) {
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getContextEl().getAssignedVariables().getFinalVariables();
        CustList<StringMap<AssignmentBefore>> varsList_;
        varsList_ = new CustList<StringMap<AssignmentBefore>>();
        DoBlock dBlock_ = (DoBlock) getPreviousSibling();
        CustList<StringMap<AssignmentBefore>> list_;
        list_ = dBlock_.makeHypothesisMutableLoop(_an);
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

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        ExecOperationNode root_ = getOpCondition().last();
        AbstractCoverageResult result_ = _cont.getCoverage().getCovers().getVal(this).getVal(root_);
        String tag_;
        if (result_.isFullCovered()) {
            tag_ = "<span class=\"f\">";
        } else if (result_.isPartialCovered()) {
            tag_ = "<span class=\"p\">";
        } else {
            tag_ = "<span class=\"n\">";
        }
        int off_ = getOffset().getOffsetTrim();
        _parts.add(new PartOffset(tag_,off_));
        tag_ = "</span>";
        _parts.add(new PartOffset(tag_,off_+ _cont.getKeyWords().getKeyWordWhile().length()));
        super.processReport(_cont,_parts);
    }
}
