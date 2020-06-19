package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.expressionlanguage.files.OffsetsBlock;
import code.util.CustList;

public final class ExecDoWhileCondition extends ExecCondition {
    public ExecDoWhileCondition(OffsetsBlock _offset, String _condition, int _conditionOffset, CustList<ExecOperationNode> _opCondition) {
        super(_offset, _conditionOffset, _opCondition);
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
}
