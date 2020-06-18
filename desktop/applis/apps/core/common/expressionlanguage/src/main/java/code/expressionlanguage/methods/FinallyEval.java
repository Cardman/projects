package code.expressionlanguage.methods;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecFinallyEval;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.exec.stacks.AbruptCallingFinally;
import code.expressionlanguage.exec.stacks.ExceptionCallingFinally;
import code.expressionlanguage.exec.stacks.TryBlockStack;
import code.util.*;

public final class FinallyEval extends BracedBlock implements Eval {

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
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        ExecFinallyEval exec_ = new ExecFinallyEval(getOffset());
        page_.getBlockToWrite().appendChild(exec_);
        page_.getAnalysisAss().getMappingMembers().put(exec_,this);
        page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
        _cont.getCoverage().putBlockOperations(_cont, exec_,this);
    }

    @Override
    public void checkTree(ContextEl _an, AnalyzingEl _anEl) {
        Block pBlock_ = getPreviousSibling();
        if (!(pBlock_ instanceof AbstractCatchEval)) {
            if (!(pBlock_ instanceof TryEval)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(getFile().getFileName());
                un_.setIndexFile(getOffset().getOffsetTrim());
                un_.buildError(_an.getAnalysisMessages().getUnexpectedCatchElseFinally(),
                        _an.getKeyWords().getKeyWordFinally(),
                        StringList.join(
                                new StringList(
                                        _an.getKeyWords().getKeyWordCatch(),
                                        _an.getKeyWords().getKeyWordTry()
                                ),
                                "|"));
                //key word len
                _an.addError(un_);
            }
        }
    }


    @Override
    public void reach(ContextEl _an, AnalyzingEl _anEl) {
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
