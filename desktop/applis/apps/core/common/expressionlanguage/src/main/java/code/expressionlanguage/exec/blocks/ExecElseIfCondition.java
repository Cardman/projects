package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.IfBlockStack;
import code.expressionlanguage.files.OffsetsBlock;
import code.util.CustList;

public final class ExecElseIfCondition extends ExecCondition implements StackableBlock {

    private int delta;

    public ExecElseIfCondition(OffsetsBlock _offset, String _condition, int _conditionOffset, CustList<ExecOperationNode> _opCondition, int _delta) {
        super(_offset, _conditionOffset, _opCondition);
        delta = _delta;
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        IfBlockStack if_ = (IfBlockStack) ip_.getLastStack();
        if_.setCurrentVisitedBlock(this);
        if (!if_.isEntered()) {
            ConditionReturn assert_ = evaluateCondition(_cont);
            if (assert_ == ConditionReturn.CALL_EX) {
                return;
            }
            if (assert_ == ConditionReturn.YES) {
                if_.setEntered(true);
                rw_.setBlock(getFirstChild());
                return;
            }
        }
        if (if_.getLastBlock() == this) {
            processBlockAndRemove(_cont);
            return;
        }
        rw_.setBlock(getNextSibling());
    }

    @Override
    public void exitStack(ContextEl _context) {
        AbstractPageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        IfBlockStack if_ = (IfBlockStack) ip_.getLastStack();
        if (if_.getLastBlock() != this) {
            rw_.setBlock(getNextSibling());
        }
    }
}
