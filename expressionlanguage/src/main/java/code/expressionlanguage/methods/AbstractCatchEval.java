package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.util.ReadWrite;
import code.expressionlanguage.errors.custom.UnexpectedTagName;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.stacks.TryBlockStack;
import code.expressionlanguage.structs.NullStruct;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;

public abstract class AbstractCatchEval extends BracedStack implements Eval {

    protected AbstractCatchEval(ContextEl _importingPage,
            BracedBlock _m, OffsetsBlock _offset) {
        super(_importingPage, _m, _offset);
    }

    @Override
    public String getRealLabel() {
        Block p_ = getPreviousSibling();
        while (!(p_ instanceof TryEval)) {
            if (p_ == null) {
                return EMPTY_STRING;
            }
            p_ = p_.getPreviousSibling();
        }
        return ((TryEval)p_).getLabel();
    }

    @Override
    public final void abruptGroup(AnalyzingEl _anEl) {
        if (canBeIncrementedCurGroup()) {
            return;
        }
        CustList<Block> group_ = new CustList<Block>();
        group_.add(this);
        Block p_ = getPreviousSibling();
        while (!(p_ instanceof TryEval)) {
            if (p_ == null) {
                break;
            }
            group_.add(p_);
            p_ = p_.getPreviousSibling();
        }
        if (p_ != null) {
            group_.add(p_);
        }
        boolean canCmpNormally_ = false;
        for (Block b: group_) {
            if (_anEl.canCompleteNormally(b)) {
                canCmpNormally_ = true;
                break;
            }
        }
        if (!canCmpNormally_) {
            for (Block b: group_) {
                _anEl.completeAbruptGroup(b);
            }
        }
    }

    @Override
    public final void setAssignmentBeforeNextSibling(Analyzable _an, AnalyzingEl _anEl) {
        if (!canBeIncrementedCurGroup()) {
            super.setAssignmentBeforeNextSibling(_an, _anEl);
            return;
        }
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        Block nextSibling_ = getNextSibling();
        boolean finClause_ = nextSibling_ instanceof FinallyEval;
        Block try_ = this;
        CustList<AbstractCatchEval> catch_ = new CustList<AbstractCatchEval>();
        while (try_ instanceof AbstractCatchEval) {
            catch_.add((AbstractCatchEval) try_);
            try_ = try_.getPreviousSibling();
        }
        if (!(try_ instanceof TryEval)) {
            super.setAssignmentBeforeNextSibling(_an, _anEl);
            return;
        }
        TryEval tryBl_ = (TryEval)try_;
        AssignedVariables assBl_ = nextSibling_.buildNewAssignedVariable();
        if (finClause_) {
            assBl_.getFieldsRootBefore().putAllMap(buildAssFieldsBefNextCatchFinally(tryBl_,_an, _anEl, catch_));
            assBl_.getVariablesRootBefore().addAllElts(buildAssVarsBefNextCatchFinally(tryBl_,_an, _anEl, catch_));
            assBl_.getMutableLoopRootBefore().addAllElts(buildAssMutableLoopBefNextCatchFinally(tryBl_,_an, _anEl, catch_));
        } else {
            assBl_.getFieldsRootBefore().putAllMap(buildAssFieldsBefNextCatchFinally(tryBl_,_an, _anEl, new CustList<AbstractCatchEval>()));
            assBl_.getVariablesRootBefore().addAllElts(buildAssVarsBefNextCatchFinally(tryBl_,_an, _anEl, new CustList<AbstractCatchEval>()));
            assBl_.getMutableLoopRootBefore().addAllElts(buildAssMutableLoopBefNextCatchFinally(tryBl_,_an, _anEl, new CustList<AbstractCatchEval>()));
        }
        id_.put(nextSibling_, assBl_);
    }

    @Override
    public final void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        super.setAssignmentAfter(_an, _anEl);
        Block pBlock_ = getPreviousSibling();
        if (!(pBlock_ instanceof AbstractCatchEval)) {
            if (!(pBlock_ instanceof TryEval)) {
                UnexpectedTagName un_ = new UnexpectedTagName();
                un_.setFileName(getFile().getFileName());
                un_.setIndexFile(getOffset().getOffsetTrim());
                _an.getClasses().addError(un_);
            }
        }
        if (canBeIncrementedCurGroup()) {
            return;
        }
        CustList<Block> prev_ = new CustList<Block>();
        prev_.add(this);
        while (!(pBlock_ instanceof TryEval)) {
            if (pBlock_ == null) {
                break;
            }
            if (pBlock_ instanceof Eval) {
                prev_.add(pBlock_);
            }
            pBlock_ = pBlock_.getPreviousSibling();
        }
        if (pBlock_ != null) {
            prev_.add(pBlock_);
        }
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables assTar_ = id_.getVal(this);
        StringMap<SimpleAssignment> after_;
        CustList<StringMap<SimpleAssignment>> afterVars_;
        after_ =buildAssFieldsAfterTry(prev_, _an, _anEl);
        assTar_.getFieldsRoot().putAllMap(after_);
        afterVars_ = buildAssVariablesAfterTry(prev_, _an, _anEl);
        assTar_.getVariablesRoot().clear();
        assTar_.getVariablesRoot().addAllElts(afterVars_);
        CustList<StringMap<SimpleAssignment>> afterMutable_;
        afterMutable_ = buildAssMutableLoopAfterTry(prev_, _an, _anEl);
        assTar_.getMutableLoopRoot().clear();
        assTar_.getMutableLoopRoot().addAllElts(afterMutable_);
    }

    private boolean canBeIncrementedCurGroup() {
        Block next_ = getNextSibling();
        return next_ instanceof AbstractCatchEval || next_ instanceof FinallyEval;
    }

    @Override
    public final void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        TryBlockStack ts_ = (TryBlockStack) ip_.getLastStack();
        ts_.setException(NullStruct.NULL_VALUE);
        if (ts_.getLastBlock() == this) {
            ip_.removeLastBlock();
            processBlock(_cont);
        } else {
            ts_.setCurrentBlock(this);
            rw_.setBlock(getNextSibling());
        }
    }

    @Override
    public void processToFinally(AbstractPageEl _ip, TryBlockStack _stack) {
        removeLocalVars(_ip);
        BracedBlock br_ = _stack.getLastBlock();
        if (br_ instanceof FinallyEval) {
            _ip.clearCurrentEls();
            _ip.getReadWrite().setBlock(br_);
            _ip.setFinallyToProcess(true);
            return;
        }
        _ip.removeLastBlock();
    }
}
