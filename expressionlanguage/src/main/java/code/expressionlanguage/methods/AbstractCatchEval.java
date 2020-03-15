package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.util.ReadWrite;
import code.expressionlanguage.errors.custom.UnexpectedTagName;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.stacks.TryBlockStack;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;

public abstract class AbstractCatchEval extends BracedStack implements Eval {

    protected AbstractCatchEval(OffsetsBlock _offset) {
        super(_offset);
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
    public int getRealLabelOffset() {
        Block p_ = getPreviousSibling();
        while (!(p_ instanceof TryEval)) {
            p_ = p_.getPreviousSibling();
        }
        return ((TryEval)p_).getLabelOffset();
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
        IdMap<Block, AssignedVariables> id_ = _an.getContextEl().getAssignedVariables().getFinalVariables();
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
    public void checkTree(Analyzable _an, AnalyzingEl _anEl) {
        Block pBlock_ = getPreviousSibling();
        if (!(pBlock_ instanceof AbstractCatchEval)) {
            if (!(pBlock_ instanceof TryEval)) {
                UnexpectedTagName un_ = new UnexpectedTagName();
                un_.setFileName(getFile().getFileName());
                un_.setIndexFile(getOffset().getOffsetTrim());
                _an.addError(un_);
            }
        }
    }

    @Override
    public final void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        super.setAssignmentAfter(_an, _anEl);
        Block pBlock_ = getPreviousSibling();
        if (canBeIncrementedCurGroup()) {
            return;
        }
        CustList<Block> prev_ = new CustList<Block>();
        prev_.add(this);
        while (!(pBlock_ instanceof TryEval)) {
            if (!(pBlock_ instanceof Eval)) {
                break;
            }
            prev_.add(pBlock_);
            pBlock_ = pBlock_.getPreviousSibling();
        }
        if (pBlock_ instanceof Eval) {
            prev_.add(pBlock_);
        }
        IdMap<Block, AssignedVariables> id_ = _an.getContextEl().getAssignedVariables().getFinalVariables();
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
        if (ts_.getLastBlock() == this) {
            processBlockAndRemove(_cont);
        } else {
            ts_.setCurrentVisitedBlock(this);
            rw_.setBlock(getNextSibling());
        }
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        String tag_;
        if (_cont.getCoverage().getCatches().getVal(this)) {
            tag_ = "<span class=\"f\">";
        } else {
            tag_ = "<span class=\"n\">";
        }
        int off_ = getOffset().getOffsetTrim();
        _parts.add(new PartOffset(tag_,off_));
        tag_ = "</span>";
        _parts.add(new PartOffset(tag_,off_+ _cont.getKeyWords().getKeyWordCatch().length()));
    }

}
