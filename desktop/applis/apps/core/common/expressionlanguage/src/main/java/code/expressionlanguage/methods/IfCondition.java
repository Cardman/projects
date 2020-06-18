package code.expressionlanguage.methods;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecCondition;
import code.expressionlanguage.exec.blocks.ExecIfCondition;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.exec.coverage.AbstractCoverageResult;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.IfBlockStack;
import code.util.CustList;

public final class IfCondition extends Condition implements BlockCondition {

    private String label;
    private int labelOffset;

    public IfCondition(OffsetStringInfo _condition, OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_condition, _offset);
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public int getRealLabelOffset() {
        return getLabelOffset();
    }
    public int getLabelOffset() {
        return labelOffset;
    }

    private boolean canBeIncrementedCurGroup() {
        Block next_ = getNextSibling();
        return next_ instanceof ElseIfCondition || next_ instanceof ElseCondition;
    }

    @Override
    protected ExecCondition newCondition(String _condition, int _conditionOffset,CustList<ExecOperationNode> _ops) {
        return new ExecIfCondition(getOffset(),_condition,_conditionOffset,label,labelOffset,_ops);
    }

    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        if (canBeIncrementedCurGroup()) {
            return;
        }
        ExecOperationNode op_ = getRoot();
        Argument arg_ = op_.getArgument();
        boolean abr_ = Argument.isTrueValue(arg_);
        if (!abr_) {
            return;
        }
        if (!_anEl.canCompleteNormally(this)) {
            _anEl.completeAbruptGroup(this);
        }
    }
    @Override
    public boolean accessibleCondition() {
        ExecOperationNode op_ = getRoot();
        Argument arg_ = op_.getArgument();
        return Argument.isNotFalseValue(arg_);
    }
    @Override
    public boolean accessibleForNext() {
        ExecOperationNode op_ = getRoot();
        Argument arg_ = op_.getArgument();
        return !Argument.isTrueValue(arg_);
    }

}
