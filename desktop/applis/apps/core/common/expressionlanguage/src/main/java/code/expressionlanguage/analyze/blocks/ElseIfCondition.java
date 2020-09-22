package code.expressionlanguage.analyze.blocks;
import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.exec.blocks.ExecCondition;
import code.expressionlanguage.exec.blocks.ExecElseIfCondition;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;
import code.util.StringList;

public final class ElseIfCondition extends Condition implements BlockCondition {

    private int delta;

    public ElseIfCondition(OffsetStringInfo _condition, OffsetsBlock _offset, int _delta) {
        super(_condition, _offset);
        delta = _delta;
    }

    @Override
    protected ExecCondition newCondition(String _condition, int _conditionOffset,CustList<ExecOperationNode> _ops) {
        return new ExecElseIfCondition(getOffset(), _conditionOffset,_ops);
    }

    @Override
    public String getRealLabel() {
        Block p_ = getPreviousSibling();
        while (!(p_ instanceof IfCondition)) {
            if (p_ == null) {
                return EMPTY_STRING;
            }
            p_ = p_.getPreviousSibling();
        }
        return ((IfCondition)p_).getLabel();
    }

    @Override
    public int getRealLabelOffset() {
        Block p_ = getPreviousSibling();
        while (!(p_ instanceof IfCondition)) {
            p_ = p_.getPreviousSibling();
        }
        return ((IfCondition)p_).getLabelOffset();
    }

    private boolean canBeIncrementedCurGroup() {
        Block next_ = getNextSibling();
        return next_ instanceof ElseIfCondition || next_ instanceof ElseCondition;
    }
    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        if (canBeIncrementedCurGroup()) {
            return;
        }
        Argument arg_ = getArgument();
        boolean abr_ = Argument.isTrueValue(arg_);
        if (!abr_) {
            return;
        }
        CustList<Block> group_ = getConditionBlocks();
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
    public void checkTree(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        Block pBlock_ = getPreviousSibling();
        if (!(pBlock_ instanceof IfCondition)) {
            if (!(pBlock_ instanceof ElseIfCondition)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(getFile().getFileName());
                un_.setIndexFile(getOffset().getOffsetTrim());
                un_.buildError(_page.getAnalysisMessages().getUnexpectedCatchElseFinally(),
                        _page.getKeyWords().getKeyWordElseif(),
                        StringList.join(
                                new StringList(
                                        _page.getKeyWords().getKeyWordIf(),
                                        _page.getKeyWords().getKeyWordElseif()
                                ),
                                "|"));
                //key word len
                getErrorsBlock().add(un_.getBuiltError());
                setReachableError(true);
                _page.addLocError(un_);
            }
        }
    }

    @Override
    public boolean accessibleCondition() {
        Argument arg_ = getArgument();
        return Argument.isNotFalseValue(arg_);
    }
    @Override
    public boolean accessibleForNext() {
        Argument arg_ = getArgument();
        return !Argument.isTrueValue(arg_);
    }

    @Override
    public void reach(AnalyzingEl _anEl, AnalyzedPageEl _page) {
        Block p_ = getPreviousSibling();
        if (_anEl.isReachable(p_) && p_.accessibleForNext()) {
            _anEl.reach(this);
        } else {
            _anEl.unreach(this);
        }
    }

    public int getDelta() {
        return delta;
    }
}
