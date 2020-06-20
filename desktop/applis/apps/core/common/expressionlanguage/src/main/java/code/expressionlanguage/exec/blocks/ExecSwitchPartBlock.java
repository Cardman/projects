package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.stacks.SwitchBlockStack;
import code.expressionlanguage.files.OffsetsBlock;

public abstract class ExecSwitchPartBlock extends ExecBracedBlock implements
        StackableBlock {
    ExecSwitchPartBlock(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void exitStack(ContextEl _context) {
        AbstractPageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        SwitchBlockStack if_ = (SwitchBlockStack) ip_.getLastStack();
        if (if_.getExecLastVisitedBlock() == this) {
            rw_.setBlock(if_.getBlock());
        } else {
            rw_.setBlock(getNextSibling());
        }
    }
}
