package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.util.ReadWrite;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.stacks.SwitchBlockStack;

public abstract class SwitchPartBlock extends BracedStack implements
        StackableBlock, BuildableElMethod {

    protected SwitchPartBlock(OffsetsBlock _offset) {
        super(_offset);
    }


    @Override
    public void exitStack(ContextEl _context) {
        AbstractPageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        SwitchBlockStack if_ = (SwitchBlockStack) ip_.getLastStack();
        if (if_.getLastVisitedBlock() == this) {
            rw_.setBlock(if_.getBlock());
        } else {
            rw_.setBlock(getNextSibling());
        }
    }

}
