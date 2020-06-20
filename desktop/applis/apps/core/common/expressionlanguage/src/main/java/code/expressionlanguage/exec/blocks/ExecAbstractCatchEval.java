package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.stacks.TryBlockStack;
import code.expressionlanguage.files.OffsetsBlock;

public abstract class ExecAbstractCatchEval extends ExecBracedBlock implements StackableBlock {

    ExecAbstractCatchEval(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void processEl(ContextEl _cont) {
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
}
