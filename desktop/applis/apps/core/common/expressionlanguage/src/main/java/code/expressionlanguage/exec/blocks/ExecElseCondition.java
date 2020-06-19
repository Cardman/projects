package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.stacks.IfBlockStack;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.methods.StackableBlock;

public final class ExecElseCondition extends ExecBracedBlock implements StackableBlock {

    public ExecElseCondition(OffsetsBlock _offset) {
        super(_offset);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        IfBlockStack if_ = (IfBlockStack) ip_.getLastStack();
        if_.setCurrentVisitedBlock(this);
        if (!if_.isEntered()) {
            if_.setEntered(true);
            ip_.getReadWrite().setBlock(getFirstChild());
            return;
        }
        processBlockAndRemove(_cont);
    }
}
