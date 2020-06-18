package code.expressionlanguage.methods;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecCondition;
import code.expressionlanguage.exec.blocks.ExecElseIfCondition;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.exec.coverage.AbstractCoverageResult;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.IfBlockStack;
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
        return new ExecElseIfCondition(getOffset(),_condition,_conditionOffset,_ops,delta);
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
        CustList<Block> group_ = new CustList<Block>();
        group_.add(this);
        Block p_ = getPreviousSibling();
        while (!(p_ instanceof IfCondition)) {
            group_.add(p_);
            p_ = p_.getPreviousSibling();
        }
        group_.add(p_);
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
    public void checkTree(ContextEl _an, AnalyzingEl _anEl) {
        Block pBlock_ = getPreviousSibling();
        if (!(pBlock_ instanceof IfCondition)) {
            if (!(pBlock_ instanceof ElseIfCondition)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFileName(getFile().getFileName());
                un_.setIndexFile(getOffset().getOffsetTrim());
                un_.buildError(_an.getAnalysisMessages().getUnexpectedCatchElseFinally(),
                        _an.getKeyWords().getKeyWordElseif(),
                        StringList.join(
                                new StringList(
                                        _an.getKeyWords().getKeyWordIf(),
                                        _an.getKeyWords().getKeyWordElseif()
                                ),
                                "|"));
                //key word len
                _an.addError(un_);
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
    public void reach(ContextEl _an, AnalyzingEl _anEl) {
        Block p_ = getPreviousSibling();
        if (_anEl.isReachable(p_) && p_.accessibleForNext()) {
            _anEl.reach(this);
        } else {
            _anEl.unreach(this);
        }
    }
}
