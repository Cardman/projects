package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.util.LocalThrowing;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.stacks.AbruptCallingFinally;
import code.expressionlanguage.stacks.ExceptionCallingFinally;
import code.expressionlanguage.stacks.TryBlockStack;
import code.util.*;

public final class FinallyEval extends BracedStack implements Eval {

    public FinallyEval(OffsetsBlock _offset) {
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
    public void buildExpressionLanguage(ContextEl _cont) {
        buildEmptyEl(_cont);
    }

    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
    }

    @Override
    public void checkTree(Analyzable _an, AnalyzingEl _anEl) {
        Block pBlock_ = getPreviousSibling();
        if (!(pBlock_ instanceof AbstractCatchEval)) {
            if (!(pBlock_ instanceof TryEval)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(getFile().getFileName());
                un_.setIndexFile(getOffset().getOffsetTrim());
                un_.buildError(_an.getContextEl().getAnalysisMessages().getUnexpectedCatchElseFinally(),
                        _an.getContextEl().getKeyWords().getKeyWordFinally(),
                        StringList.join(
                                new StringList(
                                        _an.getContextEl().getKeyWords().getKeyWordCatch(),
                                        _an.getContextEl().getKeyWords().getKeyWordTry()
                                ),
                                "|"));
                //key word len
                _an.addError(un_);
            }
        }
    }

    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        super.setAssignmentAfter(_an, _anEl);
        Block pBlock_ = getPreviousSibling();
        CustList<Block> prev_ = new CustList<Block>();
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
        CustList<StringMap<SimpleAssignment>> mutableVars_;
        after_ = buildAssFieldsAfterFinally(prev_, _an, _anEl);
        assTar_.getFieldsRoot().putAllMap(after_);
        afterVars_ = buildAssVariablesAfterFinally(prev_, _an, _anEl);
        assTar_.getVariablesRoot().clear();
        assTar_.getVariablesRoot().addAllElts(afterVars_);
        mutableVars_ = buildAssMutableLoopAfterFinally(prev_, _an, _anEl);
        assTar_.getMutableLoopRoot().clear();
        assTar_.getMutableLoopRoot().addAllElts(mutableVars_);
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {

    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        TryBlockStack ts_ = (TryBlockStack) ip_.getLastStack();
        ts_.setCurrentVisitedBlock(this);
        if (ts_.isVisitedFinally()) {
            processBlockAndRemove(_cont);
            return;
        }
        ts_.setVisitedFinally(true);
        ip_.getReadWrite().setBlock(getFirstChild());
    }

    @Override
    public void exitStack(ContextEl _context) {
        AbstractPageEl ip_ = _context.getLastPage();
        TryBlockStack tryStack_ = (TryBlockStack) ip_.getLastStack();
        AbruptCallingFinally call_ = tryStack_.getCalling();
        if (call_ != null) {
            CallingFinally callingFinally_ = call_.getCallingFinally();
            if (call_ instanceof ExceptionCallingFinally) {
                _context.setException(((ExceptionCallingFinally)call_).getException());
            }
            callingFinally_.removeBlockFinally(_context);
        }
    }

    @Override
    public void reach(Analyzable _an, AnalyzingEl _anEl) {
        Block p_ = getPreviousSibling();
        while (!(p_ instanceof TryEval)) {
            if (p_ == null) {
                break;
            }
            p_ = p_.getPreviousSibling();
        }
        if (p_ == null) {
            _anEl.reach(this);
            return;
        }
        if (_anEl.isReachable(p_)) {
            _anEl.reach(this);
        } else {
            _anEl.unreach(this);
        }
    }
    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        CustList<Block> group_ = new CustList<Block>();
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
        if (!_anEl.canCompleteNormally(this)) {
            for (Block b: group_) {
                _anEl.completeAbruptGroup(b);
            }
            _anEl.completeAbruptGroup(this);
            return;
        }
        IdMap<BreakBlock, BreakableBlock> breakables_ = _anEl.getBreakables();
        boolean existBreak_ = false;
        for (EntryCust<BreakBlock, BreakableBlock> b: breakables_.entryList()) {
            if (b.getValue() == this) {
                if (_anEl.isReachable(b.getKey())) {
                    existBreak_ = true;
                }
            }
        }
        if (existBreak_) {
            //because break instructions cancel all abrupt instructions in the previous blocks
            //there exists a break instruction that break the try statement
            return;
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
            _anEl.completeAbruptGroup(this);
        }
    }

}
