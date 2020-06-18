package code.expressionlanguage.methods;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecCondition;
import code.expressionlanguage.exec.blocks.ExecWhileCondition;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.exec.coverage.AbstractCoverageResult;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.util.*;

public final class WhileCondition extends Condition implements Loop {

    private String label;
    private int labelOffset;

    public WhileCondition(OffsetStringInfo _condition, OffsetStringInfo _label, OffsetsBlock _offset) {
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

    @Override
    protected ExecCondition newCondition(String _condition, int _conditionOffset,CustList<ExecOperationNode> _ops) {
        return new ExecWhileCondition(getOffset(),_condition,_conditionOffset,label,labelOffset,_ops);
    }


    @Override
    public boolean accessibleCondition() {
        Argument arg_ = getArgument();
        return Argument.isNotFalseValue(arg_);
    }
    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        boolean abr_ = true;
        Argument arg_ = getArgument();
        boolean proc_ = Argument.isTrueValue(arg_);
        if (_anEl.isReachable(this)) {
            if (!proc_) {
                abr_ = false;
            }
        }
        IdMap<BreakBlock, BreakableBlock> breakables_;
        breakables_ = _anEl.getBreakables();
        for (EntryCust<BreakBlock, BreakableBlock> e: breakables_.entryList()) {
            if (e.getValue() == this && _anEl.isReachable(e.getKey())) {
                abr_ = false;
                break;
            }
        }
        if (abr_) {
            _anEl.completeAbruptGroup(this);
        }
    }

}
